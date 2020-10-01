package twitchblast.textureatlas;

import java.nio.ByteBuffer;
import java.util.Deque;

import org.lwjgl.BufferUtils;

import twitchblast.chunks.Chunk;
import twitchblast.opengraphicslibrary.Texture;

public class WorldTileAtlas extends TileAtlas{

	private Texture pixels;
	private int[][] currentTex;
	
	public WorldTileAtlas(String source) {
		super(source);
		tiles = extractTilesFromTextureAtlas(16,16);
	}

	public void createTexture(Deque<Chunk> chunks) {
		currentTex = writeTilesIntoTexture(chunks);
		pixels = writeTextureIntoBuffer(currentTex);	
		
	}
	
	private int[][] writeTilesIntoTexture(Deque<Chunk> chunks) {
		int[][] resTex = new int[16*16][16*4*9];
		
		int chunkId=0;
		for (Chunk chunk : chunks) {
			int a=0;
			//Tile - weise
			for (int j = 0; j < 4; j++) {
				for (int i = 0; i < 16; i++) {
					 a= chunk.getTile(i, j);
					//Pixel - weise
					for (int y = 0; y < 16; y++) {
						for (int x = 0; x < 16; x++) {
				
							resTex[i*16+x][(16*4*chunkId)+j*16+y] = tiles.get(a)[x][y];
					
						}
					}
	
				}
			}
			chunkId++;
		}
		return resTex;
	}

	
	public Texture getResTex(){
		return pixels;
	}

	public Texture redrawTexture(Chunk newChunk) {
		int[][] newTex = movePixelsDown();
		
		setPixelsOfNewChunk(newChunk, newTex);

		currentTex = newTex;
		return writeTextureIntoBuffer(newTex);
	}
	public Texture revalidateChunk(Chunk newChunk, int nr) {
		
		setPixelsOfNewChunk(newChunk, currentTex, nr);

		return writeTextureIntoBuffer(currentTex);
	}

	public void setPixelsOfNewChunk(Chunk newChunk, int[][] newTex) {
		setPixelsOfNewChunk(newChunk, newTex, 0);
	}
	public void setPixelsOfNewChunk(Chunk newChunk, int[][] newTex, int offset) {
		int a=0;
		int yOffset = offset * 16*4;
		//Tile - weise
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 16; i++) {
				 a= newChunk.getTile(i, j);
				//Pixel - weise
				for (int y = 0; y < 16; y++) {
					for (int x = 0; x < 16; x++) {
			
						newTex[i*16+x][j*16+y + yOffset] = tiles.get(a)[x][y];
				
					}
				}

			}
		}
	}
	public int[][] movePixelsDown() {
		int[][] newTex = new int[16*16][16*4*9];

		for (int y = 0; y < 16*4*8; y++) {
			for (int x = 0; x < 16*16; x++) {
	
				newTex[x][y+4*16] = currentTex[x][y];
		
			}
		}
		return newTex;
	}

}
