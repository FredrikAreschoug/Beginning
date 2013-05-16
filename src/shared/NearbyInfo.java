package post.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class NearbyInfo implements Serializable{

	private static final long serialVersionUID = 42L;
	public ArrayList<GameActor> ga;
	public ArrayList<GameStatic> gs;

	public NearbyInfo() {
		ga = new ArrayList<GameActor>();
		gs = new ArrayList<GameStatic>();
	}

	public void check() {}
}
