package twitchblast.opengraphicslibrary;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

import org.joml.Matrix3f;
import org.lwjgl.opengl.GL;

import twitchblast.Tile;
import twitchblast.World;
import twitchblast.controller.Camera;
import twitchblast.glfw.Window;
import twitchblast.textureatlas.TextTileAtlas;

public class Renderer {
	private static final String MATRIX = "matrix";
	private static final int SIZE_OF_FLOAT = 4;
	
	private float vertices[] = {
	        // positions          // colors           // texture coords
			 1.0f, -1.0f, 0.0f,   1.0f, 0.0f, 0.0f,   1.0f, 1.0f, // top right
			 1.0f,  1.0f, 0.0f,   0.0f, 1.0f, 0.0f,   1.0f, 0.0f, // bottom right
			-1.0f,  1.0f, 0.0f,   0.0f, 0.0f, 1.0f,   0.0f, 0.0f, // bottom left
			-1.0f, -1.0f, 0.0f,   1.0f, 1.0f, 0.0f,   0.0f, 1.0f  // top left 
	};
	private int indices[] = {  
		0, 1, 3, // first triangle
		1, 2, 3  // second triangle
	};
	private int VBO, VAO, EBO;
	private TextTileAtlas textAtlas = new TextTileAtlas("src\\twitchblast\\gfx\\TEXTur.png");
	private Shader shader;

	private float winWidth;
	private float winHeight;
	private Camera cam;
	private Matrix3f mat3 = new Matrix3f();
	private Matrix3f translateMat;
	
	public Renderer(Window window) {
		winWidth = window.getWidth()/1.6f;
		winHeight = window.getHeight()/1.6f;
		translateMat = new Matrix3f();

		GL.createCapabilities();// Braucht man, weil createShader sonst keinen context hat
		shader = new Shader("src\\shaders\\shader.vs","src\\shaders\\shader.fs");
		
		VAO = glGenVertexArrays();
		VBO = glGenBuffers();
		EBO = glGenBuffers();
		
		glBindVertexArray(VAO);
		
		glBindBuffer(GL_ARRAY_BUFFER, VBO);
		glBufferData(GL_ARRAY_BUFFER,vertices, GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
		
		// position attribute
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 8 * SIZE_OF_FLOAT, 0);
		glEnableVertexAttribArray(0);
		// color attribute
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 8 * SIZE_OF_FLOAT, (3 * SIZE_OF_FLOAT));
		glEnableVertexAttribArray(1);
		// texture coord attribute
		glVertexAttribPointer(2, 2, GL_FLOAT, false, 8 * SIZE_OF_FLOAT, (6 * SIZE_OF_FLOAT));
		glEnableVertexAttribArray(2);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
	}
	public void updateTexture(Tile tile) {

		glBindTexture(GL_TEXTURE_2D, tile.getTexture().getId());	
		glActiveTexture(GL_TEXTURE0+0);

		glGenerateMipmap(GL_TEXTURE_2D);
		glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
		
		shader.use();
	}
	public void render(World world) {

		glClear(GL_COLOR_BUFFER_BIT);
		
		for (Tile worldTile : world.getTiles()) {
			renderTile(worldTile);
		}
		
        glFlush();
	}
	public void render(String text,int lineNumber) {

		
		
		Tile textAsTile = textAtlas.getTextAsTile(text);
		textAsTile.setLocation(16*16, -lineNumber*35);
		renderTile(textAsTile,false);
		
        glFlush();
	}
	
	private void renderTile(Tile tile, boolean withCam) {
		mat3.identity();
		
		//Entzerren (Quadrate sind wieder quadratisch)
		mat3.scale(tile.getWidth()/winWidth,-tile.getHeight()/winHeight,1f);
			
		//Screen Koordinaten(Translation um 16 in x richtung sind tatsächlich ein tile)
		mat3.scale(2/tile.getWidth(),2/tile.getHeight(),1);
		
		//Screen Koordinaten((0,0) ist oben links)
		mat3.mul(getTranslate(-(winWidth-tile.getWidth())/2,-(winHeight-tile.getHeight())/2));
			
		//Actual translation
		mat3.mul(getTranslate(tile.getX(), -tile.getY()));
		if(withCam)
			mat3 = mat3.mul(cam.getMat());
		//------------------
			
		//Screen Koordinatenzurüch in GL Koordinaten
		mat3.scale(tile.getWidth()/2,-tile.getHeight()/2,1);
			
		
		shader.setMatrix(MATRIX, mat3);
		updateTexture(tile);
		glBindVertexArray(VAO); 
		glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
	}
	public void renderTile(Tile tile) {
		renderTile(tile,true);
	}
	private Matrix3f getTranslate(float x, float y) {
		translateMat.m20 = x;
		translateMat.m21 = y;
		return translateMat;
	}
	public void close() {
		glDeleteVertexArrays(VAO);
		glDeleteBuffers(VBO);
		
	}

	public void setCam(Camera c) {
		cam = c;
	}


}
