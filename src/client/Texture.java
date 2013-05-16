package post.client;

public class Texture {
	private String name;
	private int id;
	private int width;
	private int height;
	
	/* Constructors */
	
	public Texture() {}
	
	public Texture(int id, String name, int width, int height) {
		setId(id);
		setName(name);
		setWidth(width);
		setHeight(height);
	}
	
	/* Getters and setters */
	
	public Texture setId(int id) {
		this.id = id;
		return this;
	}
	
	public int getId() {
		return id;
	}
	
	public Texture setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public Texture setWidth(int width) {
		this.width = width;
		return this;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Texture setHeight(int height) {
		this.height = height;
		return this;
	}
	
	public int getHeight() {
		return height;
	}
}
