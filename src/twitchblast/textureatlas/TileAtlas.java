package twitchblast.textureatlas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import twitchblast.opengraphicslibrary.Texture;

public class TileAtlas {
	
	private int width = 0, height = 0;
	protected List<int[][]> tiles;
	protected int[] pixels_raw;
	
	public TileAtlas(String source) {
		pixels_raw = loadRawPixelsFromImage(source);
		
	}
	private int[] loadRawPixelsFromImage(String fileString) {
		
		BufferedImage bufferedImage = null;

		try {
			File input = new File(fileString);
			bufferedImage = ImageIO.read(input);
		
			width = bufferedImage.getWidth();
			height = bufferedImage.getHeight();
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bufferedImage.getRGB(0, 0, width, height, null, 0, width);
	}
	public List<int[][]> extractTilesFromTextureAtlas(int tileWidth, int tileHeight) {
		List<int[][]> tileArray = new ArrayList<>();
		int anzTilesHorizontal = width/tileWidth;
		int anzTilesVertical = height/tileHeight;
		for (int iTiles = 0; iTiles < anzTilesHorizontal*anzTilesVertical; iTiles++) {
			int[][] tile = new int[tileWidth][tileHeight];
			int tileX = iTiles%anzTilesHorizontal;
			int tileY = iTiles/anzTilesHorizontal;
			
			for (int y = 0; y < tileHeight; y++) {
				for (int x = 0; x < tileWidth; x++) {
					tile[x][y] = pixels_raw[x+y*width  +  tileX*tileWidth  +  width*tileY*tileHeight];
				}
			}
			tileArray.add(tile);
		}
		return tileArray;
	}
	
	public Texture writeTextureIntoBuffer(int[][] resTex) {
		int width = resTex.length;
		int height = resTex[0].length;

		ByteBuffer pixelBuffer = BufferUtils.createByteBuffer(width*height*4);
		for (int y=0; y<height; y++){
			for (int x=0; x<width; x++){
				int pixel = resTex[x][y];
				
				pixelBuffer.put((byte) ((pixel >> 16) & 0xFF));//RED
				pixelBuffer.put((byte) ((pixel >>  8) & 0xFF));//GREEN
				pixelBuffer.put((byte) ((pixel      ) & 0xFF));//BLUE
				pixelBuffer.put((byte) ((pixel >> 24) & 0xFF));//ALPHA
			}
		}
		pixelBuffer.flip();
		return new Texture(pixelBuffer);
	}
	
}
