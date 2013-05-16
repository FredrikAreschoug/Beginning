package post.client;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;

public class TextField {
	private static Texture asciiTable = null;
	private String message;
	private float size;
	private float scale;
	
	/* Constructors */
	
	public TextField() {
		initialize("", 1f);
	}
	
	public TextField(String message) {
		initialize(message, 1f);
	}
	
	public TextField(String message, float scale) {
		initialize(message, scale);
	}
	
	private void initialize(String message, float scale) {
		setSize(32); 
		setMessage(message);
		setScale(scale);
	}
	
	/* Getters and setters */
	
	public static void setAsciiTable(Texture t) {
		asciiTable = t;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	private void setSize(int size) {
		this.size = size;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	/* Draw */
	
	public void draw(float x, float y) {
		int i = 0; 
		int t = 0;
		int z = 10;
		int texIndent = 0;
		
		// This is just for giggles really
		// Alignment shouldn't be handled here
		int tindent = (int)((message.length()/2)*size*scale - size*scale)+2;
		
		glBindTexture(GL_TEXTURE_RECTANGLE_ARB, asciiTable.getId());
		glColor4f(1f, 1f, 1f, 1f);
		glDisable(GL_DEPTH_TEST);
		
		glBegin(GL_QUADS);
		for(char ch : message.toCharArray()) {
			t = Character.getNumericValue(ch);
			
			// -10 because ASCI 'A' starts at 10
			texIndent = t - 10;

			glTexCoord2f((texIndent*size), 0);
			glVertex3f((i*size*scale) + x - tindent, y - 12, z);
			
			glTexCoord2f((texIndent*size) + size, 0);
			glVertex3f((i*size*scale) + x - tindent + (size*scale), y - 12, z);
			
			glTexCoord2f((texIndent*size) + size, size);
			glVertex3f((i*size*scale) + x - tindent + (size*scale), y + (size*scale) - 12, z);
		
			glTexCoord2f((texIndent*size), size);
			glVertex3f((i*size*scale) + x - tindent, y + (size*scale) - 12, z);
			
			++i;
		}
		glEnd();
		
		glEnable(GL_DEPTH_TEST);
		glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);
	}
}
