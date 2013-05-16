package post.shared;


public class GameTree extends GameStatic{

	private int radius = 100;

	public GameTree(int id, float x,float y, float z) {
		super(id,x,y,z);
	}

	private int cut(int force){
		radius -= force;
		return radius;
	}
}
