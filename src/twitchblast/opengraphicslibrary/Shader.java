package twitchblast.opengraphicslibrary;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix3f;
import org.lwjgl.BufferUtils;

public class Shader {

	public int shaderProgram;
	private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(3*3);
	// constructor reads and builds the shader
	public Shader(String vertexPath, String fragmentPath) {
		String vertexShaderSource = readShaderFile(vertexPath);
		String fragmentShaderSource = readShaderFile(fragmentPath);
	
	
		int vertexShader = glCreateShader(GL_VERTEX_SHADER);
		int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
	    glShaderSource(vertexShader,vertexShaderSource);
	    glCompileShader(vertexShader);
	    glShaderSource(fragmentShader,fragmentShaderSource);
	    glCompileShader(fragmentShader);

	    logShaderInfo(vertexShader);
	    logShaderInfo(fragmentShader);
	    
	    shaderProgram = glCreateProgram();
	    
	    glAttachShader(shaderProgram, vertexShader);
	    glAttachShader(shaderProgram, fragmentShader);
	    glLinkProgram(shaderProgram);
	    
	    logProgramInfo(shaderProgram);
	    
	    glDeleteShader(vertexShader);
	    glDeleteShader(fragmentShader);
	}

	
	
	// use/activate the shader
	public void use() {
		glUseProgram(shaderProgram);
	}
	// utility uniform functions BEISPIELE für Matrix später
	public void setBool(String name, boolean value) {
		glUniform1i(glGetUniformLocation(shaderProgram,name),value ? 1 : 0);
	}
	public void setInt(String name, int value) {
		glUniform1i(glGetUniformLocation(shaderProgram,name),value);
	}
	public void setFloat(String name, float value) {
		glUniform1f(glGetUniformLocation(shaderProgram,name),value);
	}
	public void setMatrix(String name, Matrix3f value) {
		
		value.get(matrixBuffer);
		glUniformMatrix3fv( glGetUniformLocation(shaderProgram, name),false, matrixBuffer);
	}
	
	private String readShaderFile(String path) {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(new File(path)));
			String line;
			while((line = reader.readLine())!=null) {
				stringBuilder.append(line);
				stringBuilder.append("\n");
			}
			reader.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	private void logShaderInfo(int shaderId) {
		String glGetShaderInfoLog = glGetShaderInfoLog(shaderId, 512);
	    System.out.println(glGetShaderInfoLog);
	}
	private void logProgramInfo(int shaderId) {
		String glGetShaderInfoLog = glGetProgramInfoLog(shaderId, 512);
	    System.out.println(glGetShaderInfoLog);
	}
}