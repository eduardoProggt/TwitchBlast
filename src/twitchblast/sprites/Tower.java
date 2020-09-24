package twitchblast.sprites;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import twitchblast.opengraphicslibrary.Renderer;

public abstract class Tower {
	public static final int STRAIGHT = 0,LEFT = 1, RIGHT = 2;
	
	protected Point location;
	protected int state;
	protected float livePoints = 100;
	protected int frames = 0;
	
//	protected Collider collider = new Collider();//TODO Sowas in die collidable - oberklasse
//	
//	protected Rectangle rectangleRed;
//	protected Rectangle rectangleGreen;
	
	public Tower(Point location, int state) {
		setState(state);
		setLocation(location);
//		rectangleRed = new Rectangle(30, 4, new Color(255, 0, 0));
//		rectangleGreen = new Rectangle(30, 4, new Color(0, 255, 0));
	}
	
	public abstract void update(Renderer renderer/*,List<Projectile> projectiles*/, final Rocket rocket);
	
	public abstract void renderBarrel(Renderer renderer);
	
	public void renderHealthBar(Renderer renderer) {
		if(isDestroyed())
			return;
//		renderer.renderRect((int)getLocation().getX()+1,(int)getLocation().getY(),getRectangleRed());
//		renderer.renderRect((int)getLocation().getX()+1,(int)getLocation().getY(),getRectangleGreen());
//		
	}
	public void dealDamage(float damage) {
		livePoints -= damage;
	}
	
	public boolean isDestroyed() {
		return livePoints <= 0;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

//	public Rectangle getRectangleRed() {
//		return rectangleRed;
//	}
//
//	public Rectangle getRectangleGreen() {
//		rectangleGreen.setWidth((int)((livePoints/100f)*30));
//		return rectangleGreen;
//	}

	
}
