package twitchblast.controller;

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
		renderer = new Renderer(win);
		controller = new InputController(win);
		world = new World();
	}

	public void calcNextFrame() {
		
		world.scroll(cam);
		renderer.setCam(cam);
		
		long currentTimeMillis = System.currentTimeMillis();
		renderer.render(world);
		if(System.currentTimeMillis() - currentTimeMillis > 0)
			System.out.println("Render World: " + Long.toString(System.currentTimeMillis() - currentTimeMillis));
		renderer.render("ABCDabcd",0);
		world.update();
		
		
		
		
		computeInput();

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
//			world.
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
}
