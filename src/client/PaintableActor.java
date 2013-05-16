package post.client;

import post.shared.GameActor;
import post.shared.GameObject;
import static org.lwjgl.opengl.GL11.*;

public class PaintableActor extends PaintableObject {
	public GameActor ga;
	private TextField name;
	private float size;
	
	/* Constructors */
	public PaintableActor(GameActor ga) {
		this.ga = ga;
		super.go = (GameObject)ga;

		// name = new TextField("Aniston", 0.3f);
		name = new TextField(ga.getName(), 0.3f);
		size = 10;
	}
	
	/* Methods */
	@Override
	public void paint() {
		glColor3f(go.getRed(), go.getGreen(), go.getBlue());
		glBegin(GL_QUADS);
			glVertex3f(go.getX(), go.getY(), go.getZ());
			glVertex3f(go.getX(), go.getY() + size, go.getZ()); 
			glVertex3f(go.getX() + size, go.getY() + size, go.getZ());
			glVertex3f(go.getX() + size, go.getY(), go.getZ());
		glEnd();
		
		name.draw(go.getX(), go.getY());
	}
}