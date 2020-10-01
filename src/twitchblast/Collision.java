package twitchblast;

import twitchblast.chunks.Chunk;

public class Collision {
	private int x;
	private int y;
	private float intensity;
	private Chunk chunk;
	
	public Collision(int x, int y, Chunk chunk) {
		super();
		this.x = x;
		this.y = y;
		this.chunk = chunk;
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}

	public Chunk getChunk() {
		return chunk;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}
