package twitchblast;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import twitchblast.chunks.Chunk;
import twitchblast.controller.Camera;
import twitchblast.sprites.Rocket;

public class World {
	
	final int CHUNK_HEIGHT = 4*16;
	
	private Collider collider = new Collider();//TODO Sowas in die collidable - oberklasse
	private List<Tile> textlists = new ArrayList<>();
	private List<Rocket> uptadableObjects= new ArrayList<>();//TODO, Vernünfitiger Name, interface
	private Rocket rocket = new Rocket();
	
	private Landscape landscape;
	
//	private List<Projectile> flyingBullets= new ArrayList<>();

	public World() {
		landscape = new Landscape();
		textlists.add(landscape.getTile());
		textlists.add(rocket);
		uptadableObjects.add(rocket);
	}
//	public void renderNextFrame(Rocket rocket) {
//		getRenderer().bindTextures();
//		for (Chunk chunk : getChunks()) {
//			
//			for (int ey = 0; ey < 4; ey++) {
//				for (int ex = 0; ex < 16; ex++) {
//					getRenderer().renderWorldTile(ex*16,chunk.y+ey*16, chunk.getTile(ex,ey),chunk.getTile(ex,ey));
//
//				}
//			}
//			chunk.update(getRenderer(),rocket);
//			
//		}

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

//	public int getHighestChunkY() {
//		return getChunks().getLast().y;
//	}
//	public void addChunk() {
//		getChunks().add(generateChunk(getHighestChunkY()-4*16));
//	}
//	public Renderer getRenderer() {
//		return null;// renderer;
//	}
//	public Deque<Chunk> getChunks() {
//		return chunks;
//	}

//	public void updateChunks(float camY) {
//			if(getHighestChunkY()>camY) {
//				addChunk();
//			}
//			if(getChunks().size()>8) {
//				getChunks().poll();
//			}
//		
//	}
	public List<Tile> getTiles() {

		return textlists;
		
	}
	public void update(){
		//Statt Rocket kommt das interface
		for (Rocket tile : uptadableObjects) {
			tile.update();
		}
		collideWithChunk();
	}
	public void updateChunks() {
		landscape.stepForward();
	}

	public void scroll(Camera cam) {
		cam.scrollY(1f);
		Tile tile = landscape.getTile();

		if(cam.getY() > -tile.getY()) {
			updateChunks();
			tile.move(0,-16*4f);
		}
		
	}
	private void collideWithChunk(){
		for(Rocket eObj : uptadableObjects) {

			System.out.println(eObj.getY());
			for (Chunk chunk : landscape.getChunks()) {
			//TODO Optimieren (Aber so dass es NICHT Bugged)
//				if(chunk.y < eObj.getLocation().getY()+16*4 && chunk.y > eObj.getLocation().getY()-16*4) {

					collide(eObj,chunk);
//				}
			}
		}
	}
		
	private void collide(Rocket rocket, Chunk chunk) {
		//TODO Optimieren (Aber so dass es NICHT Bugged)
		final float MIN_X = 0;//rocket.getX()/16 < 0 ? 0 : rocket.getX()/16;
		final float MAX_X = 16;//rocket.getX()/16+3 > 16 ? 16 : rocket.getX()/16+3;

		for (int ey = 0; ey < 4; ey++) {
			for (int ex = (int)MIN_X ; ex < (int)MAX_X; ex++) {
				int tile = chunk.getTile(ex, ey);
				int xCoord = ex*16;
				int yCoord = ey*16 + chunk.getNumber()*CHUNK_HEIGHT;

				if(tile==1 || tile==17 || tile==25) {
		
					int collision = collider.intersects(xCoord,yCoord,16,16,(int) rocket.getX(),(int)rocket.getY(),32,32);
					if(collision == Collider.NOT)
						continue;
					rocket.collide(collision);
				}
			
			}
		}
	}
	public Rocket getRocket() {
		// TODO Auto-generated method stub
		return rocket;
	}
	/**debug
	 * @return ***/
	public Deque<Chunk> getChunks() {
		return landscape.getChunks();
		
	}
	
}
