package post.shared;

public class GameActor extends GameObject {
	private String name = "default";

	private int direction;

	public GameActor() {
	}

	public GameActor(int id) {
		super(id);
	}

	public void move(float x, float y) {
		super.x += x;
		super.y += y;
	}


	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
