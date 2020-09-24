package twitchblast.glfw;


import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWFramebufferSizeCallbackI;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;

public class Window {
	private long windowID;
	private float height, width;
	
	public Window(String title,int width,int height) {

		GLFWErrorCallback.createPrint(System.err).set();
		
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");
		setSize(width,height);
		setHints(); 
		
		
		windowID = glfwCreateWindow((int)getWidth(), (int)getHeight(), title, 0, 0);
		
		if ( windowID == 0 ) {
			glfwTerminate();
			throw new RuntimeException("Failed to create the GLFW window");
		}
	
		glfwMakeContextCurrent(windowID);

	    glfwSetFramebufferSizeCallback(windowID, getResizeCallback());
		
		glfwSetKeyCallback(windowID, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});
		
		glfwShowWindow(windowID);
		
	}
	public GLFWFramebufferSizeCallbackI getResizeCallback() {
		GLFWFramebufferSizeCallbackI framebuffer_size_callback = new GLFWFramebufferSizeCallbackI() {
			
			@Override
			public void invoke(long window, int width, int height) {
				//Knallt momentan
				//GL11.glViewport(0, 0, width, height);
				System.out.println(String.format("Window resized to %dx%d", width, height));
			}
		};
		return framebuffer_size_callback;
	}
	public void setHints() {
		glfwDefaultWindowHints(); 
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE); 
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_SAMPLES, 4);
	}
	public boolean shouldClose() {
		return glfwWindowShouldClose(windowID);
	}
	

	private void setSize(int _width, int _height) {
		width = _width;
		height = _height;
	}

	public void destroy() {
		glfwFreeCallbacks(windowID);
		glfwDestroyWindow(windowID);
	}

	public void swapBuffers() {
		glfwSwapBuffers(windowID);
	}
	public void setCallbacks() {
		glfwSetErrorCallback(new GLFWErrorCallbackI() {
			
			@Override
			public void invoke(int errorCode, long description) {

				throw new IllegalStateException(GLFWErrorCallback.getDescription(description));
			}
		});
	}


	public boolean orderedToClose() {
		return glfwGetKey(getWinId(),GLFW_KEY_ESCAPE) == 1;
	}
	public void setShouldClose() {
		glfwSetWindowShouldClose(getWinId(), true);
		
	}
	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}
	private long getWinId() {
		return windowID;
	}
	public boolean isKeyPresed(int glfwKey) {
		return  glfwGetKey(glfwGetCurrentContext(),glfwKey) == 1;
	}
	public void pollEvents() {
		glfwPollEvents();
		
	}
	public void close() {
		glfwTerminate();  
		
	}
}


