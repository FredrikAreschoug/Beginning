package post.client;
import static org.lwjgl.opengl.GL11.*;


public class Entity{
	public TextField name;
	private float x;
	private float y;
	private float z;
	private float d;
	private float r;
	private float g;
	private float b;
	private float size;

    public final int NONE, UP, LEFT, DOWN, RIGHT;

    private int direction;

	public Entity(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		size = 10;
		
		name = null;

        this.NONE = 0;
        this.UP = 1;
        this.LEFT = 2;
        this.DOWN = 3;
        this.RIGHT = 4;
	}
	
	public void setName(String name) {
		this.name = new TextField(name, 0.25f);
		
	}

	public void setColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void move(float h, float v) {
		x += h;
		y += v;
	}
	
	public void draw() {
		glColor3f(r, g, b);
		glBegin(GL_QUADS);
		{
			glVertex3f(x, y, z);
			glVertex3f(x, y + size, z); 
			glVertex3f(x + size, y + size, z);
			glVertex3f(x + size, y, z);
		}
		glEnd();
		
		if(name != null)
			name.draw((int)x, (int)y);
	}

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
