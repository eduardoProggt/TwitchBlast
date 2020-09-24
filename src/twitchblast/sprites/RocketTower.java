package twitchblast.sprites;

import java.awt.Point;
import java.util.List;

import twitchblast.NIY.sprites.Projectile;
import twitchblast.NIY.sprites.TowerRocket;
import twitchblast.opengraphicslibrary.Renderer;

public class RocketTower extends Tower{
	

	public RocketTower(Point location, int state) {
		super(location, state);
	}
	
	@Override
	public void update(Renderer renderer, final Rocket rocket) {
		final Point rocketLocation = rocket.getLocation();
		final int shootFrequency = 8000;
		if(frames > Math.random()*shootFrequency 
				&& rocketLocation.y > getLocation().getY() 
				&& !isDestroyed()) {
			frames = 0;
			int xOffset = 8;
			if(state == LEFT)
				xOffset = -4;
			if(state == RIGHT)
				xOffset = 20;
//			projectiles.add(new TowerRocket(getLocation().x+xOffset,getLocation().y+14,rocketLocation));
		}
		frames++;
	}
	public void renderBarrel(Renderer renderer) {
		if(isDestroyed())
			return;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
//				renderer.renderSpriteTile((int)(getLocation().getX())+j*16,(int)(16*i+getLocation().getY()),2*getState()+j+2,i+4);
			}
		}
	}

}
