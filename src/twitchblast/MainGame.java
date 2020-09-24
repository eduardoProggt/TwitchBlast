package twitchblast;

public class MainGame implements Runnable{

	private MainProgramm program;

	public MainGame(boolean isOnTwitch) {
		Thread t = new Thread (this, "MainLoop");
        t.start();
	}

	public static void main(String[] args) {
		new MainGame(false);
	}

	public void handleTwitchMessage(String message) {
		String nameOfPurchaser = message.split(" ")[0];
		String order = message.split(" ")[2];
		
		program.handleNewTwitchOrder(order,nameOfPurchaser);
	}

	@Override
	public void run() {
		program = new MainProgramm();
		program.run(1600, 900, "TwitchBlaster");
		
	}
}