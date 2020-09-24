package twitchblast.controller;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import twitchblast.World;
import twitchblast.glfw.InputController;
import twitchblast.glfw.Window;
import twitchblast.opengraphicslibrary.Renderer;
import twitchblast.sprites.Rocket;

public class GameController {

	private InputController controller;
	private World world;
	private Renderer renderer;		
//	private Rocket rocket = new Rocket(renderer);
	private boolean paused;
	private Camera cam = new Camera(128,320);
//	private List<PhysicalObject> worldObjects = new ArrayList<>();

	public GameController(Window win) {
//		worldObjects.add(rocket);
		renderer = new Renderer(win);
		controller = new InputController(win);
		world = new World();
		//renderer.setCam(cam);
	}

	public void calcNextFrame() {
		
		
		
		
		world.scroll(cam);
		renderer.setCam(cam);
		
		
		
		long currentTimeMillis = System.currentTimeMillis();
		renderer.render(world);
		if(System.currentTimeMillis() - currentTimeMillis > 0)
			System.out.println("Render World: " + Long.toString(System.currentTimeMillis() - currentTimeMillis));
		
		
		currentTimeMillis = System.currentTimeMillis();
		renderer.render("ABCDabcd",0);
		renderer.render("Mimbelwimbel hat sich",1);
		renderer.render("was gewünscht",2);
		renderer.render("Jetzt kommt die Oma",3);
		if(System.currentTimeMillis() - currentTimeMillis > 0)
			System.out.println("Render Text: " + Long.toString(System.currentTimeMillis() - currentTimeMillis));
		
		computeInput();
		//TODO dies in world machen in einem Array
		Rocket rocket = world.getRocket();
		rocket.update();
		//renderer.render(hud);
////		world.renderNextFrame(rocket);//TODO diese abhängigkeit auflösen
//		world.updateChunks(cam.getY());
////		computeInput();
//		collideWithChunk();
//		renderer.bindTextures();
////		updatePhysicalObjects();
//		renderer.renderArtwork();
////		renderer.renderText();

	}
	
	private void computeInput() {
		Rocket rocket = world.getRocket();
		if(controller.upPressed())
			rocket.increaseMomentum(Rocket.UP);
		if(controller.downPressed())
			rocket.increaseMomentum(Rocket.DOWN);
		if(controller.rightPressed())
			rocket.increaseMomentum(Rocket.RIGHT);
		if(controller.leftPressed())
			rocket.increaseMomentum(Rocket.LEFT);
//		if(controller.spaceTipped())
//			worldObjects.add(rocket.shoot());
	}
	
	public void close() {
		renderer.close();
		
	}
//	private void updatePhysicalObjects() {
//		List<PhysicalObject> deletedObjects = new ArrayList<PhysicalObject>();
//		for(PhysicalObject eObj : worldObjects) {
//			if(eObj.exists())
//				eObj.update();
//			else
//				deletedObjects.add(eObj);
//		}
//		worldObjects.removeAll(deletedObjects);
//	}
//	private void collideWithChunk(){
//		for(PhysicalObject eObj : worldObjects) {
//			for (Chunk chunk : world.getChunks()) {
//				if(chunk.y < eObj.getLocation().getY()+16*4 && chunk.y > eObj.getLocation().getY()-16*4) {
//					collide(eObj,chunk);
//				}
//			}
//		}
//	}
//			
//		
//		private void collide(PhysicalObject eCol, Chunk chunk) {
//			final int MAX_X = eCol.getLocation().x/16+3 > 16 ? 16 : eCol.getLocation().x/16+3;
//			final int MIN_X = eCol.getLocation().x/16 < 0 ? 0 : eCol.getLocation().x/16;
//			float intensity = 0;
//			for (int ey = 0; ey < 4; ey++) {
//				for (int ex = MIN_X ; ex < MAX_X; ex++) {
////					intensity = eCol.collide(chunk.getTile(ex,ey),ex*16,chunk.y+ ey*16);
//					if(chunk.getTile(ex,ey) ==17 && intensity >= 8 ) {
//						chunk.setTile(16,ex,ey);
//						chunk.setTile(24,ex,ey+1);
//					}
//					if(chunk instanceof TowerChunk) {
//						TowerChunk towerChunk = (TowerChunk)chunk;
//						towerChunk.dealDamage(ex,intensity);
//					}
//			}			
//		}
//	}
	


	public void executeOrder(String order) {
//		switch (order) {
//		case "string":
//			break;
//			
	}


	public boolean isPaused() {
		return paused;
	}
	public void setPaused(boolean psed) {
		paused = psed;
		
	}

	public void pollEvents() {
		glfwPollEvents();
		
	}
}
