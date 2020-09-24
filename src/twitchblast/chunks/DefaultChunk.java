package twitchblast.chunks;

public class DefaultChunk extends Chunk{

	public DefaultChunk() {
		int pflanze= 0, riffel=8, stein=1, glatt=9;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 16; x++) {
				double random = Math.random();
				if(x==0 || x ==15)
						setTile(stein,x,y ); 
				if(x==1 || x ==14)
					if(random < 0.2f)
						setTile(stein,x,y );
					else if(random < 0.4f)
						setTile(pflanze,x,y ); 
					else if(random < 0.7f)
						setTile(riffel,x,y ); 
					else
						setTile(glatt,x,y ); 
				if(x>1 && x <14)
					if(random < 0.03f)
						setTile(stein,x,y );
					else if(random < 0.1f)
						setTile(pflanze,x,y ); 
					else if(random < 0.5f)
						setTile(riffel,x,y ); 
					else
						setTile(glatt,x,y ); 
					
			}
	
		}
	}

}
