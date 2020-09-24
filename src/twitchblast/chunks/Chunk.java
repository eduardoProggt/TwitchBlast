package twitchblast.chunks;

import twitchblast.opengraphicslibrary.Renderer;
import twitchblast.sprites.Rocket;

public abstract class Chunk {
	private int[][] tileGrid = new int[16][4];
	


	public Chunk() {
		
	}
	public void setTile(int tile, int x,int y) {
		tileGrid[x][y] = tile;
	}

	public int getTile(int x, int y) {
		return tileGrid[x][y];
	}

	public void update(Renderer renderer, Rocket rocket) {
		// Bestenfalls hier nichts zu tun
	}


}
