package post.shared;

public class GameNPC extends GameActor {

	private int todo;

	public GameNPC(int id, int todo) {
		super(id);
		this.todo = todo;
	}

	public GameNPC(int id) {
		super(id);
	}

	public void changeTodo(int todo){
		this.todo = todo;
	}

	public int getTodo() {
		return todo;
	}
}