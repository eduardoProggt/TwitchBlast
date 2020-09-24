package twitchblast;

import java.awt.Rectangle;

public class Collider {
	private Rectangle aRectangle = new Rectangle();
	private Rectangle bRectangle = new Rectangle();

	public static final int NOT = 0,NORTH = 1,EAST = 2,SOUTH = 3,WEST = 4;
	
	public int intersects(int ax,int ay,int aw,int ah,int bx,int by,int bw,int bh) {
		aRectangle.setBounds(ax+1,ay+1,aw-1,ah-1);
		bRectangle.setBounds(bx+1,by+1,bw-1,bh-1);
		if(!aRectangle.intersects(bRectangle))
			return NOT;

		int diffX = (int)(bRectangle.getCenterX() - aRectangle.getCenterX());
		int diffY = (int)(bRectangle.getCenterY() - aRectangle.getCenterY());
		if(Math.abs(diffX)>Math.abs(diffY)) 
			return diffX > 0 ? WEST : EAST;
		else 
			return diffY > 0 ? NORTH : SOUTH;
		
	}
}
