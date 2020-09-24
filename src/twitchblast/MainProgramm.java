package twitchblast;

import java.util.LinkedList;
import java.util.Queue;

import org.lwjgl.Version;

import twitchblast.controller.GameController;
import twitchblast.glfw.Window;


public class MainProgramm {
	
	private Queue<Object> twitchOrders = new LinkedList<>();
	private Window win;

	public void run(int width, int height, String title) {

		System.out.println("LWJGL " + Version.getVersion());
		win = new Window("TwitchBlast", 1200, 800);
		loop();

	}

	private void loop() {
	
		GameController gameController = new GameController(win);
		
		long sleepTimeInMilliSeconds=0;
		long currentTimeMillis = 0;
		long takentime = 0;
		final long SLEEP_TIME_PER_FRAME = 16;

		while ( !win.shouldClose()) {

			if(win.orderedToClose())
				win.setShouldClose();

				currentTimeMillis = System.currentTimeMillis();
				gameController.calcNextFrame();
				takentime = System.currentTimeMillis() - currentTimeMillis;
				
				sleepTimeInMilliSeconds = SLEEP_TIME_PER_FRAME - takentime;
				if(sleepTimeInMilliSeconds<0)
					sleepTimeInMilliSeconds=0;

				try {
					Thread.sleep(sleepTimeInMilliSeconds,0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
//			else if(glfwGetKey(win.getWinId(),GLFW_KEY_ENTER) == GL_TRUE)
//				gameController.setPaused(false);
		        win.swapBuffers();
		        win.pollEvents();
		}
		gameController.close();
		win.close();
	}
	
	public void handleNewTwitchOrder(String order, String nameOfPurchaser) {
//		if(order.toUpperCase().contains("KAKTUS"))
//			getTwitchOrders().offer(new Cactus(nameOfPurchaser));
//		
//		if(order.toUpperCase().contains("HELI"))
//			getTwitchOrders().offer(new Heli(nameOfPurchaser));
//		
//		if(order.toUpperCase().contains("OMA"))
//			getTwitchOrders().offer(new Granny(nameOfPurchaser));
//	
//		if(order.toUpperCase().contains("SCHNELLER")) 
//			getTwitchOrders().offer("SCHNELLER");
//			
//		if(order.toUpperCase().contains("NEBEL")) 
//			getTwitchOrders().offer("NEBEL");
//			
//		if(order.toUpperCase().contains("HINTERGRUND")) {
//			getTwitchOrders().offer("HINTERGRUND");
//		}
//		if(order.toUpperCase().contains("KUGEL")) {
//			getTwitchOrders().offer("KUGEL");
//		}
	}

	private void computeNewTwitchOrder(GameController gameController) {
		//Das ist hässlich, aber selbst Urwill sagt, man kann das so machen
//		if(getTwitchOrders().peek() instanceof Obstacle)
//			gameController.addObstacle((Obstacle)getTwitchOrders().poll());
//		if(getTwitchOrders().peek() instanceof String)
//			gameController.executeOrder((String)getTwitchOrders().poll());
	}




	public Queue<Object> getTwitchOrders() {
		return twitchOrders;
	}

}
