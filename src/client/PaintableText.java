package post.client;

import static org.lwjgl.opengl.GL11.*;
//import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;

public class PaintableText extends PaintableObject {
	@Override
	public void paint() {
		glPushMatrix();
		//glTranslate3f(2f, 2f);
		glBegin(GL_QUADS);
			// Draw
		glEnd();
		glPopMatrix();
	}
}
