package twitchblast.chunks;

import twitchblast.opengraphicslibrary.Renderer;
import twitchblast.sprites.Rocket;

public abstract class Chunk {
	private int[][] tileGrid = new int[16][4];
	
	int number; //Der wievielte chunk es von oben gesehen in der Textur ist.

	public Chunk() {
		number = 0;
	}

	public void setTile(int tile, int x,int y) {
		tileGrid[x][y] = tile;
	}

	public int getTile(int x, int y) {
		return tileGrid[x][y];
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int newNumber) {
		number = newNumber;
	}

}
