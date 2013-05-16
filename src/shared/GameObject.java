package post.shared;

import java.io.Serializable;

/**
 * @brief An object that is representet in the game
 *
 * Could be a tree, a player, an NPC or whatever!
 */
public class GameObject implements Serializable {

	private static final long serialVersionUID = 43L;
	protected float x, y, z;
	private float red, green, blue;
	protected int id = 9999;

	public GameObject(){
	}

	public GameObject(int id, float x, float y, float z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public GameObject(int id) {
		this.id = id;
	}

	/* Getters and setters */
	
	public void setRGB(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
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
	
	public float getZ() {
		return z;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public float getRed() {
		return red;
	}
	
	public void setRed(float red) {
		this.red = red;
	}
	
	public float getGreen() {
		return green;
	}
	
	public void setGreen(float green) {
		this.green = green;
	}
	
	public float getBlue() {
		return blue;
	}
	
	public void setBlue(float blue) {
		this.blue = blue;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
