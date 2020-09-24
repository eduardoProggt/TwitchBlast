package twitchblast.controller;

import org.joml.Matrix3f;

public class Camera {

	private int height, width;
	private Matrix3f mat = new Matrix3f();
	
	public Camera(int width, int height) {
		this.height = height;
		this.width  = width;
	}

	public void scrollY(float pixels) {
		translate(0, pixels);
	}
	private void translate(float x, float y) {
		getMat().m20 += x;
		getMat().m21 += y;
		
	}

	public float getY() {
		return getMat().m21;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Matrix3f getMat() {
		return mat;
	}


}
