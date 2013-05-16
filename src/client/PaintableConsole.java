package post.client;

import static org.lwjgl.opengl.GL11.*;

public class PaintableConsole extends PaintableObject {
	private Console console;
	
	/* Constructors */
	public PaintableConsole() {}
	public PaintableConsole(Console console) {
		this.console = console;
	}
	
	/* Methods */
	@Override
	public void paint() {
		if(console.isActive())
			return;
		
		glDisable(GL_DEPTH_TEST);
		glColor3f(0f, 0f, 0f);
		glBegin(GL_QUADS);
			glVertex3f(0, 0, 1);
			glVertex3f(800, 0, 1);
			glVertex3f(800, 300, 1);
			glVertex3f(0, 300, 1);
		glEnd();
		glEnable(GL_DEPTH_TEST);
	}
}
