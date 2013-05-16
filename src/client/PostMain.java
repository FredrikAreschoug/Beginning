package post.client;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;
import static org.lwjgl.opengl.GL11.*;

import post.client.util.TextureManager;
import post.shared.GameActor;
import post.shared.GameObject;

/**
 * @brief Startpoint of the app
 * 
 * This should be considered as a placeholder fold what's to come.
 * 
 * @TODO Clean all up, should be lightweight and non-static
 * @TODO Should change all this not to be all static. Maybe a singleton object?
 */
public class PostMain {
	public static GameActor player = null;
    private static Connection connection;
    private static boolean connected = false;
    public static List<PaintableObject> paintables = new ArrayList<PaintableObject>();
    private static Console console = new Console();
    public static ArrayList<GameObject> buffereredObjects = new ArrayList<GameObject>();

	public static void main(String[] args) {
		initDisplay();
		loadFont();
		spawnObjects();
		gameLoop();
		cleanUp();
		System.exit(0);
	}
	
	protected static void spawnObjects() {
		GameActor ga = null;
		ga = new GameActor(9999);
		ga.setRGB(0f, 0f, 0f);
		ga.setX(350f);
		ga.setY(300f);
		ga.setName("Aniston");
		player = ga;
		buffereredObjects.add(ga);
		
		paintables.add((PaintableObject) new PaintableActor(ga));
		paintables.add((PaintableObject) new PaintableConsole(console));
	}
	
	public static void loadFont() {
		TextField.setAsciiTable(TextureManager.get("ascii"));
	}

	public static void addObject(GameObject gameObject){
		boolean in = false;
		int goId;
		for(int i = 0; i < buffereredObjects.size(); i++){
			if(gameObject.getId() == buffereredObjects.get(i).getId()){
				in = true;
				goId = 0;
			}else{
				goId = i;

			}
		}
		if (!in){
			buffereredObjects.add(gameObject);
			paintables.add((PaintableObject) new PaintableActor((GameActor)gameObject));
		}else {

		}
	}

	public static void gameLoop() {
		while(!Display.isCloseRequested()) {
			input();
			movement();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glClearColor(0f, 0.6f, 0f, 1f);
			
			for(PaintableObject p : paintables) {
				p.paint();
			}

			Display.update();
			Display.sync(60);
		}
	}
	
	public static void input() {
        while(Keyboard.next()){
	        if (Keyboard.getEventKey() == Keyboard.KEY_W){
                if (Keyboard.getEventKeyState()){
                    player.setDirection(1);
                    if(connected){
                        connection.sendCommand();
                    }
                }else {
                    player.setDirection(0);
                    if(connected){
                        connection.sendCommand();
                    }
                }

            }

            if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                if (Keyboard.getEventKeyState()){
                    player.setDirection(3);
                    if(connected){
                        connection.sendCommand();
                    }
                }else {
                    player.setDirection(0);
                    if(connected){
                        connection.sendCommand();
                    }
                }
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_A) {
                if (Keyboard.getEventKeyState()){
                    player.setDirection(4);
                    if(connected){
                        connection.sendCommand();
                    }
                }else {
                    player.setDirection(0);
                    if(connected){
                        connection.sendCommand();
                    }
                }
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_D) {
                if (Keyboard.getEventKeyState()){
                    player.setDirection(2);
                    if(connected){
                        connection.sendCommand();
                    }
                }else {
                    player.setDirection(0);
                    if(connected){
                        connection.sendCommand();
                    }
                }
            }
            
            if(Keyboard.getEventKey() == Keyboard.KEY_Q) {
            	if(Keyboard.getEventKeyState()) {
            		if(console.isActive()) {
            			console.setActive(false);
            		} else {
            			console.setActive(true);
            		}
            	}
            }
            
            if (Keyboard.getEventKey() == Keyboard.KEY_P){
                if (Keyboard.getEventKeyState()){
                   System.out.println("x:" + player.getX() + " y:" + player.getY());
                }
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_RETURN){
                if (Keyboard.getEventKeyState()){
                    connect();
                }
            }
        }

	}

	public static void movement(){
	    switch (player.getDirection()){
            case 0:
            break;
            case 1:
                player.move(0f, -1f);
            break;
            case 2:
                player.move(1f, 0f);
            break;
            case 3:
                player.move(0f, 1f);
            break;
            case 4:
                player.move(-1f, 0f);
            break;
        }
	}
	
	public static void cleanUp() {
		Display.destroy();
	}

    public static void connect(){
        if(!connected){
            connection = new Connection("localhost", 50430);
            connection.connect();
            connected = true;
        }

    }

	public static void initDisplay() {
		final int width = 800;
		final int height = 600;
		
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException ex) {
			System.exit(-1);
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, -100, 100);
		//glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glViewport(0, 0, width, height);
		glEnable(GL_TEXTURE_RECTANGLE_ARB);
		glEnable(GL_DEPTH_TEST);		
	}
}