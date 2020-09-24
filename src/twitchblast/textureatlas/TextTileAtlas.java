package twitchblast.textureatlas;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;

import twitchblast.Tile;
import twitchblast.opengraphicslibrary.Texture;

public class TextTileAtlas extends TileAtlas {

	private Map<String,Tile> map = new HashMap<>();
	
	public TextTileAtlas(String source) {
		super(source);
		tiles = extractTilesFromTextureAtlas(22,35);
	}

	public Tile getTextAsTile(String text) {
		if(map.containsKey(text))
			return map.get(text);
		
		int[][] currentTex = writeTilesIntoTexture(text);
			
		Texture texture = writeTextureIntoBuffer(currentTex);
		Tile tile = new Tile(texture,0,0,text.length()*22,35);
		map.put(text, tile);
		return tile;
	}
	private int[][] writeTilesIntoTexture(String text) {
		int[][] resTex = new int[text.length()*22][35];
		int i=0;
		for (char letter : text.toCharArray()) {

			int a = (int)letter;
			if(a>= 48 && a<=57)
				a +=8; //Die 0 ist in der Textur bei 56
			else if(a>= 65 && a<=90)
				a -= 65; //Das A ist in der Textur bei 0
			else if(a>= 97 && a<=122)
				a -= 69; //Das a ist in der Textur bei 28
			else 
				a =27; 
			//TODO Rest machen
			//Pixel - weise
			for (int y = 0; y < 35; y++) {
				for (int x = 0; x < 22; x++) {
		
					resTex[i*22+x][y] = tiles.get(a)[x][y];
			
				}
			}
			i++;
		}
		return resTex;
	}
}
