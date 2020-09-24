package twitchblast;

import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.LinkedList;

import twitchblast.chunks.BulletTowerChunk;
import twitchblast.chunks.Chunk;
import twitchblast.chunks.DefaultChunk;
import twitchblast.chunks.RocketTowerChunk;
import twitchblast.opengraphicslibrary.Texture;
import twitchblast.textureatlas.WorldTileAtlas;

public class Landscape {

	private Deque<Chunk> chunks = new LinkedList<>();
	private Tile tile;
	private WorldTileAtlas tileAtlas;
	final String PATH_TO_WORLD_TEXTURES_FILE = "src\\twitchblast\\gfx\\Texturesheet.png";
	
	public Landscape() {
		for (int i = 0; i <= 8; i++) {
			Chunk newChunk = generateChunk();
			newChunk.setNumber(i);
			getChunks().offer(newChunk);
		}
		
		tileAtlas = new WorldTileAtlas(PATH_TO_WORLD_TEXTURES_FILE);
		tileAtlas.createTexture(getChunks());
		tile = new Tile(tileAtlas.getResTex(), -0.63f, 0, 16*16, 16*4*9);
	}

	
	private Chunk generateChunk(){
		Chunk chunk;
		double random = Math.random();
		if( random>0.90)
			chunk = new BulletTowerChunk(0,(int)(random*3)+1);
		else if(random>0.85) 
			chunk = new RocketTowerChunk(0,(int)(random*2)+1);
		else
			chunk = new DefaultChunk();
		return chunk;
	}

	public void stepForward() {
		Chunk newChunk = generateChunk();
		newChunk.setNumber(getChunks().getFirst().getNumber()-1);
		getChunks().offerFirst(newChunk);
		getChunks().pollLast();
		tile.setTexture(tileAtlas.redrawTexture(newChunk));
	}

	public Tile getTile() {
		return tile;
	}


	public Deque<Chunk> getChunks() {
		return chunks;
	}
	
}
