package twitchblast.sprites;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2d;

import twitchblast.Tile;

import twitchblast.opengraphicslibrary.Texture;

public class Rocket extends Drawable /*implements PhysicalObject */{
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int RIGHT = 2;
	public static final int LEFT = 3;

	private static Tile tile;
//	private Collider collider = new Collider();
	private Point location;
	private Vector2d momentum = new Vector2d();
	private int speed, lifePoints;
//	private List<LaserAttack> attacks = new ArrayList<>();
	
	public Rocket() {
		
		setLocation(new Point(32,32));
		Texture texture = new Texture("src\\twitchblast\\gfx\\Spaceship.png");
		tile = new Tile(texture, getLocation().x, getLocation().y, 32, 32);
		setSpeed(4);
		lifePoints = 100;
	}


	public Tile getTile(int frame) {
		return tile;
	}


	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}


	public void increaseMomentum(int direction) {
		double force = 0.2;
		if(direction == LEFT )
			momentum.x-=force;
		if(direction == RIGHT)
			momentum.x+=force;
		if(direction == UP )
			momentum.y-=force;
		if(direction == DOWN )
			momentum.y+=force;
	}
	
	public void move() {
		getLocation().x += Math.round(getMomentum().x);
		getLocation().y += Math.round(getMomentum().y);
		momentum.x = momentum.x*(29d/30d);
		momentum.y = momentum.y*(29d/30d);
		tile.setLocation(getLocation().x, -getLocation().y);
	}

	public void update() {
		
		move();
		collideWithMapBorders();

		
//		LaserAttack toDelete= null;
//		for (LaserAttack laserAttack : getAttacks()) {
//			if(laserAttack.exists())
//				laserAttack.update(world);
//			else {
//				toDelete = laserAttack;
//			}
//		}
//		getAttacks().remove(toDelete);

	}




	public void collideWithMapBorders() {
		if(getLocation().x<0)
			getLocation().x = 0;
//		if(getLocation().y<0) //TODO abh�ngig von kameraposition
//			getLocation().y = 0;
		if(getLocation().x>224)
			getLocation().x = 224;
//		if(getLocation().y>288)
//			getLocation().y = 288;
	}

//	@Override
//	public float collide(int tile ,int xCoord,int yCoord) {
//
//		if(tile==1 || tile==17 || tile==25) {
//
//			int collision = collider.intersects(xCoord+1,yCoord+1,16,16, getLocation().x,getLocation().y,32+2,32+2);
//			if(collision == Collider.NOT)
//				return 0;
//			if(collision == Collider.EAST) {
//				getLocation().x = xCoord+15;
//				if(getMomentum().x<0)
//					getMomentum().x=0;
//			}
//			if(collision == Collider.WEST) {
//				getLocation().x = xCoord-31;
//				if(getMomentum().x>0)
//					getMomentum().x=0;
//			}
//			if(collision == Collider.SOUTH) {
//				getLocation().y = yCoord+15;
//				if(getMomentum().y<0)
//					getMomentum().y=0;
//			}
//			if(collision == Collider.NORTH) {
//				getLocation().y = yCoord-31;
//				if(getMomentum().y>0)
//					getMomentum().y=0;
//			}
//		}
//		return 0;
//	}
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Vector2d getMomentum() {
		return momentum;
	}



//	public List<LaserAttack> getAttacks() {
//		return attacks;
//	}
//
//	public void setAttacks(List<LaserAttack> attacks) {
//		this.attacks = attacks;
//	}

	public void dealDamage(int damage) {
		lifePoints-=damage;
		
	}

//	@Override
//	public int getHeight() {
//		return 32;
//	}
//
//
//	@Override
//	public int getWidth() {
//		return 32;
//	}
//
//	@Override
//	public boolean exists() {
//		return true;
//	}
	
}
