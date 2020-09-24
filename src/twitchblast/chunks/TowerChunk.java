package twitchblast.chunks;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import twitchblast.opengraphicslibrary.Renderer;
import twitchblast.sprites.Rocket;
import twitchblast.sprites.StandardTower;
import twitchblast.sprites.Tower;

public abstract class TowerChunk extends Chunk {

	protected List<Tower> towers = new ArrayList<>();
//	private List<Projectile> projectiles;
	
	public TowerChunk(int yPos, /*List<Projectile> flyingBullets,*/ int anzTowers) {

		setWall();
		generateRandomTowerPositions(anzTowers);
//		projectiles = flyingBullets;
		for (Tower tower : towers) {
			setTower(tower, false); 
		}
			
	}

	public void setWall() {
		for (int y = 2; y < 4; y++) {
			for (int x = 0; x < 16; x++) {
				if(y%2 == 0)
					setTile(17,x,y );
				else
					setTile(25,x,y );
				
			}
		}
	}
	
	@Override
	public void update(Renderer renderer,final Rocket rocket) {
		final Point rocketLocation = rocket.getLocation();
		for (Tower standardTower : towers) {
			
			if(rocketLocation.x < standardTower.getLocation().getX()-16)
				standardTower.setState(StandardTower.LEFT);
			else if(rocketLocation.x > standardTower.getLocation().getX()+16)
				standardTower.setState(StandardTower.RIGHT);
			else 
				standardTower.setState(StandardTower.STRAIGHT);
			standardTower.update(renderer,rocket);
			standardTower.renderBarrel(renderer);
			standardTower.renderHealthBar(renderer);
		}

	}
	
	private void generateRandomTowerPositions(int anz) {
		List<Integer> nrs= new ArrayList<>();
		for (int i = 0; i <= 15; i++)
			nrs.add(i);
		for(int i = 0 ;i < anz; i++) {
			if(nrs.size()-1 <=0)
				break;//Alle Plaetze sind mit Türmen bedeckt
			int randPos = ((int)(Math.random()*128))%(nrs.size()-1);
			Integer locationX = nrs.get(randPos);
			addTower(locationX);
			
			nrs.removeIf(n -> Objects.equals(n, locationX-1));
			nrs.removeIf(n -> Objects.equals(n, locationX));
			nrs.removeIf(n -> Objects.equals(n, locationX+1));
		}
	}

	protected abstract void addTower(Integer locationX);
	protected abstract void setTower(Tower tower, boolean destroyed);

	public void dealDamage(int xCoord, float damage) {
		for (Tower standardTower : towers) {
			if(standardTower.getLocation().getX()/16 == xCoord ||standardTower.getLocation().getX()/16 +1 == xCoord)
				{standardTower.dealDamage(damage);
					if(standardTower.isDestroyed())
						setTower(standardTower, true);
						
				}
		}
		
	}
}
