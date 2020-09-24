package twitchblast.chunks;

import java.awt.Point;
import java.util.List;

import twitchblast.sprites.RocketTower;
import twitchblast.sprites.Tower;

public class RocketTowerChunk extends TowerChunk{

	public RocketTowerChunk(int yPos, int anzTowers) {
		super(yPos, anzTowers);
	}

	@Override
	protected void setTower(Tower tower, boolean destroyed) {
		int towerPosition = tower.getLocation().x /16;
		int texOffset= 0;
		if(destroyed) 
			texOffset = 2;
		setTile(4+3+texOffset,towerPosition+1,0 ); 
		setTile(4+2+texOffset,towerPosition+0,0 ); 
		setTile(4+11+texOffset,towerPosition+1,1 ); 
		setTile(4+10+texOffset,towerPosition+0,1 ); 
		
		setTile(4+19+texOffset,towerPosition+1,2 ); 
		setTile(4+18+texOffset,towerPosition+0,2 ); 
		setTile(4+27+texOffset,towerPosition+1,3 ); 
		setTile(4+26+texOffset,towerPosition+0,3 );
	}

	@Override
	protected void addTower(Integer locationX) {
		towers.add(new RocketTower(new Point(locationX*16,0), 0));
	}

}
