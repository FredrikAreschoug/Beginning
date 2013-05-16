package post.client;

import post.shared.GameObject;

/**
 * @brief PaintableObjects are drawn by the engine.
 * 
 * This is designed to withdraw all client display code from
 * shared classes which should only contain game specific logic.
 * 
 * @TODO A builder would be nice, theoretically.
 * @TODO Move out dependency of GameObject.
 * @TODO Maybe change name to PObject or something shorter.  
 */
public abstract class PaintableObject {
	public GameObject go;
	
	/* Methods */
	abstract public void paint();
}