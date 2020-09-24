package twitchblast;

import java.util.ArrayList;
import java.util.List;

import twitchblast.controller.Camera;
import twitchblast.sprites.Rocket;

public class World {
//	private TileRenderer renderer;
	
//	private Collider collider = new Collider();//TODO Sowas in die collidable - oberklasse
	private List<Tile> textlists = new ArrayList<>();
	Rocket rocket = new Rocket();
	
	private Landscape landscape;
	
//	private List<Projectile> flyingBullets= new ArrayList<>();

	public World() {
		landscape = new Landscape();
		textlists.add(landscape.getTile());
		textlists.add(rocket);
//		renderer = _renderer;

		
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

	public void updateChunks() {
		landscape.stepForward();
	}

	public void scroll(Camera cam) {
		cam.scrollY(1f);
		Tile tile = landscape.getTile();
		if(cam.getY() > tile.getY()) {
			updateChunks();
			tile.move(0,16*4f);
		}
		
	}

	public Rocket getRocket() {
		// TODO Auto-generated method stub
		return rocket;
	}
	
}
