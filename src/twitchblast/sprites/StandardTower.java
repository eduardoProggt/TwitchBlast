package twitchblast.sprites;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import twitchblast.NIY.Rectangle;
import twitchblast.NIY.sprites.Projectile;
import twitchblast.NIY.sprites.TowerBullet;
import twitchblast.opengraphicslibrary.Renderer;

public class StandardTower extends Tower{
	
	public StandardTower(Point location, int state) {
		super(location, state);
	}
	
	@Override
	public void update(Renderer renderer/*, List<Projectile> projectiles*/, Rocket rocket) {

		final Point rocketLocation = rocket.getLocation();
		if(frames > Math.random()*3000 
				&& rocketLocation.y > getLocation().getY() 
				&& !isDestroyed()) {
			frames = 0;
			int xOffset = 8;
			if(state == LEFT)
				xOffset = -4;
			if(state == RIGHT)
				xOffset = 20;
//			projectiles.add(new TowerBullet(getLocation().x+xOffset,getLocation().y+14,rocketLocation));
		}
		
		frames++;
	}

	public void renderBarrel(Renderer renderer) {
		if(isDestroyed())
			return;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
//				renderer.renderSpriteTile((int)(getLocation().getX())+j*16,(int)(16*i+getLocation().getY()),2*getState()+j,i);
			}
		}
	}


}
