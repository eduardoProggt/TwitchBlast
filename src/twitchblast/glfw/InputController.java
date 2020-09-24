package twitchblast.glfw;

import static org.lwjgl.glfw.GLFW.*;

public class InputController {

	int space,keyV;
	Window window;
	
	public InputController(Window win) {
		window = win;
	}
	public boolean spaceTipped() {
		boolean retval=false;
		
		if(space == 0 && glfwGetMouseButton(glfwGetCurrentContext(),GLFW_MOUSE_BUTTON_1) == 1)
			retval = true;
		space = glfwGetMouseButton(glfwGetCurrentContext(),GLFW_MOUSE_BUTTON_1);
		return retval;
	}
	public boolean keyVTipped() {
		boolean retval=false;
		if(keyV == 0 && glfwGetKey(glfwGetCurrentContext(),GLFW_KEY_V) == 1)
			retval = true;
		keyV = glfwGetKey(glfwGetCurrentContext(),GLFW_KEY_V);
		return retval;
	}
	public boolean downPressed() {
		return window.isKeyPresed(GLFW_KEY_S);
	}
	public boolean leftPressed() {
		return window.isKeyPresed(GLFW_KEY_A);
	}	
	public boolean upPressed() {
		return window.isKeyPresed(GLFW_KEY_W);
	}
	public boolean rightPressed() {
		return window.isKeyPresed(GLFW_KEY_D);
	}
  
}
