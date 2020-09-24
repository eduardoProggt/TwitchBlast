package twitchblast;

import twitchblast.opengraphicslibrary.Texture;

public class Tile {
	
	private Texture texture;
	private float width,height;
	private float x,y;
	
	public Tile(Texture texture, float x, float y, float width, float height) {	
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setTexture(texture);
	}
	/** Nach diesem Konstruktor unverzüglich die Textur setzen!**/
	public Tile( float x, float y, float width, float height) {	
		this(null,x,y,width,height);
	}
	public void move(float xDir, float yDir) {
		x += xDir;
		y += yDir;
	}

	public void setLocation(float xIn, float yIn) {
		x = xIn;
		y = yIn;
	}
	
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		if(texture != null)
			texture.update((int)getWidth(),(int)getHeight());
		this.texture = texture;
  
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	protected void setX(float newX) {
		x = newX;
	}
	protected void setY(float newY) {
		y = newY;
	}
}
