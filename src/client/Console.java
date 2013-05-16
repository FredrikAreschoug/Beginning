package post.client;

import java.util.ArrayList;
import java.util.List;

public class Console {
	private boolean active;
	private List<String> rows;
	private int id;

	public int getId() {
		return id;
	}


	public Console() {
		rows = new ArrayList<String>();
		active = true;
	}
	
	public void addRow(String message) {
		rows.add(message);
	}
	
	public void setActive(boolean b) {
		active = b;
	}
	
	public boolean isActive() {
		return active;
	}
}
