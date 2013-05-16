package post.client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import post.shared.Command;
import post.shared.GameActor;
import post.shared.GameObject;
import post.shared.NearbyInfo;


public class Connection {
	public final String IP;
	public final int PORT;

	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket socket;


	public Connection(String IP, int PORT) {
		this.IP = IP;
		this.PORT = PORT;
	}

	public void connect(){
		try{
			socket = new Socket(IP, PORT);
		}catch(Exception e){
			return;
		}

		try{
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		}catch(IOException eIO){
			System.out.println("Faild to setup in and out streams: " + eIO);
			return;
		}

		ListenFromServer ls = new ListenFromServer();
		Thread t = new Thread(ls);
		t.start();



	}

	public void sendCommand(){

		Command outGoing = new Command();
		outGoing.setDirectrion(PostMain.player.getDirection());
		outGoing.setX(PostMain.player.getX());
		outGoing.setY(PostMain.player.getY());

		System.out.println("X: " + PostMain.player.getX() + " Y:" +PostMain.player.getY());

		try{
			output.reset();
			output.writeObject(outGoing);
			output.flush();
		}catch (IOException e){
			System.out.println("Couden't send command " + e);
		}
	}

	public class ListenFromServer implements Runnable{
		public void run(){
			while(true){
				GameObject gO;

				try {
					gO = (GameObject)input.readObject();
				} catch (IOException e) {
					e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
					return;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
					return;
				}

				//System.out.println("got info");
				PostMain.addObject(gO);

			}
		}
	}
}
