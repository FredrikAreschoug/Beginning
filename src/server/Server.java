package post.server;

import post.shared.GameNPC;
import post.shared.GameObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/*
	Starts the server and creates new conections to clients
*/

public class Server {

	protected static Server server;
	public final int PORT;
	public ArrayList<ClientThread> clientList;
	public static boolean keepGoing = true;
	public ServerLoop serverLoop;
	public ArrayList<GameObject> allGameObject;
	private int id = 0;
	private Brodcasting broadcast;
	public ArrayList<GameNPC> NPCList = new ArrayList<GameNPC>();

	private Server(int PORT){
		this.PORT = PORT;
		clientList = new ArrayList<ClientThread>();
		allGameObject= new ArrayList<GameObject>();


	}

	public void start(){
		serverLoop = new ServerLoop();
		Thread st = new Thread(serverLoop);
		st.start();

		broadcast = new Brodcasting();
		Thread tb = new Thread(broadcast);
		tb.start();
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);

			while (keepGoing){
				System.out.println("Waiting for client on port " + PORT + ".");
				Socket socket = serverSocket.accept();

				if(!keepGoing) break;

				ClientThread ct = new ClientThread(socket);
				clientList.add(ct);
				Thread t = new Thread(ct);
				t.start();
				id++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public int getId() {
		id ++;
		return id;
	}

	public static void main(String[] args){

		if(true){
			server = new Server(50430);
			server.start();
		}
		else{
			server = new Server(Integer.parseInt(args[0]));
			server.start();
		}
	}
}
