package twitchblast;

import java.util.Deque;
import java.util.LinkedList;

import twitchblast.chunks.BulletTowerChunk;
import twitchblast.chunks.Chunk;
import twitchblast.chunks.DefaultChunk;
import twitchblast.chunks.RocketTowerChunk;
import twitchblast.textureatlas.WorldTileAtlas;

public class Level {

	private Deque<Chunk> chunks = new LinkedList<>();
	private Tile tile;
	private WorldTileAtlas tileAtlas;
	final String PATH_TO_WORLD_TEXTURES_FILE = "src\\twitchblast\\gfx\\Texturesheet.png";
	
	public Level() {
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
	public void collide(Collision collision) {
		//TODO wenn intensity groesser als 10 oder so ist, werden auch angrenzende tiles affektiert
		if(collision.getIntensity() > 0) {
			Chunk chunkWithCollision = collision.getChunk();
			chunkWithCollision.destroyTile(collision.getX(), collision.getY());
			int chunkNrInTextureWorld = chunkWithCollision.getNumber() - getChunks().getFirst().getNumber();
			tile.setTexture(tileAtlas.revalidateChunk(chunkWithCollision, chunkNrInTextureWorld));//TODO nicht den ganzen Chunk sondern nur die geaenderten Tiles updaten
		}
	}
	
}
