package twitchblast.chunks;

import java.awt.Point;

import twitchblast.sprites.StandardTower;
import twitchblast.sprites.Tower;

public class BulletTowerChunk extends TowerChunk {

	public BulletTowerChunk(int yPos, /*List<Projectile> flyingBullets,*/ int anzTowers) {
		super(yPos, anzTowers);
	}

	@Override
	protected void setTower(Tower tower, boolean destroyed) {
				int towerPosition = tower.getLocation().x /16;
				int texOffset= 0;
				if(destroyed) 
					texOffset = 2;
				setTile(3+texOffset,towerPosition+1,0 ); 
				setTile(2+texOffset,towerPosition+0,0 ); 
				setTile(11+texOffset,towerPosition+1,1 ); 
				setTile(10+texOffset,towerPosition+0,1 ); 
				
				setTile(19+texOffset,towerPosition+1,2 ); 
				setTile(18+texOffset,towerPosition+0,2 ); 
				setTile(27+texOffset,towerPosition+1,3 ); 
				setTile(26+texOffset,towerPosition+0,3 );	
	}

	@Override
	protected void addTower(Integer locationX) {
		towers.add(new StandardTower(new Point(locationX*16,0), 0));
	}
}
