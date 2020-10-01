package twitchblast;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import twitchblast.chunks.Chunk;
import twitchblast.controller.Camera;
import twitchblast.sprites.PhysicalObject;
import twitchblast.sprites.Rocket;

public class World {

	final int CHUNK_HEIGHT = 4*16;
	
	private Collider collider = new Collider();//TODO Sowas in die collidable - oberklasse
	private List<Tile> textlists = new ArrayList<>();
	private List<Rocket> uptadableObjects= new ArrayList<>();//TODO, Vernünfitiger Name, interface
	private Rocket rocket = new Rocket();
	
	private Level level;
	
//	private List<Projectile> flyingBullets= new ArrayList<>();

	public World() {
		level = new Level();
		textlists.add(level.getTile());
		textlists.add(rocket);
		uptadableObjects.add(rocket);
	}

//		Projectile bulletWhichHit = null;
//		for (Projectile towerRocket : flyingBullets) {
////			towerRocket.update(renderer);
//			Point rocketLocation = rocket.getLocation();
//			if(collider.intersects((int)towerRocket.getLocation().getX(),
//					(int)towerRocket.getLocation().getY(), 
//					16, 16, 
//					
//					rocketLocation .x+2, 
//					rocketLocation.y+2, 
//					30, 
//					30)!= collider.NOT) {
//				rocket.dealDamage(4);
//				
//				bulletWhichHit = towerRocket;
//			}
//		}
//		flyingBullets.remove(bulletWhichHit);
//
//	}

	public List<Tile> getTiles() {

		return textlists;
		
	}
	public void update(){
		//Statt Rocket kommt das interface
		for (PhysicalObject tile : uptadableObjects) {
			tile.update();
		}
		collideWithChunk();
	}
	public void updateChunks() {
		level.stepForward();
	}

	public void scroll(Camera cam) {
		cam.scrollY(1f);
		Tile tile = level.getTile();

		if(cam.getY() > -tile.getY()) {
			updateChunks();
			tile.move(0,-16*4f);
		}
		
	}
	private void collideWithChunk(){

		float chunkY;
		for(PhysicalObject object : uptadableObjects) {
			for (Chunk chunk : level.getChunks()) {
				chunkY = chunk.getNumber()*16*4;
				if( chunkY < object.getY()+16*4 && chunkY > object.getY()-16*4) {
					collide(object,chunk);
				}
			}
		}
	}
		
	private void collide(PhysicalObject object, Chunk chunk) {
		final float MIN_X = object.getX()/16 < 0 ? 0 : object.getX()/16;
		final float MAX_X = object.getX()/16+3 > 16 ? 16 : object.getX()/16+3;

		for (int ey = 0; ey < 4; ey++) {
			for (int ex = (int)MIN_X ; ex < (int)MAX_X; ex++) {
				int tile = chunk.getTile(ex, ey);
				int xCoord = ex*16;
				int yCoord = ey*16 + chunk.getNumber()*CHUNK_HEIGHT;

				if(tile==1 || tile==17 || tile==25) {
		
					int collisionDir = collider.intersects(xCoord,yCoord,16,16,(int) object.getX(),(int)object.getY(),32,32);
					//TODO Über  nachdenken
					if(collisionDir == Collider.NOT)
						continue;
					Collision collision = new Collision(ex, ey, chunk);
					float intensity = object.collide(collisionDir);
					collision.setIntensity(intensity);
					level.collide(collision);
				}
			
			}
		}
	}
	public Rocket getRocket() {
		return rocket;
	}

	
}
