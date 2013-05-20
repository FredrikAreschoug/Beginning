package post.server;




import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import post.shared.*;

/*
	The tread for eatch client which is connected
*/

public class ClientThread implements Runnable{

	public Socket socket;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public GamePlayer gamePlayer;
	public Brodcasting brodcasting = new Brodcasting();

	public ClientThread(Socket socket) {
		this.socket = socket;

		gamePlayer = new GamePlayer(Server.server.getId(),100f,100f,0);
		Server.server.allGameObject.add(gamePlayer);


		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Faild in/output stream");
		}
	}

	public void run(){

		while (true){

			Command command;

			try{
				command = (Command) input.readObject();
			}catch (IOException e){
				System.out.println("IOException when receiving:\n" + e);
				return;

			}catch (ClassNotFoundException e){
				System.out.println("ClassNotFoundException:\n" + e ) ;
				return;

			}

			gamePlayer.setDirection(command.getDirectrion());
			brodcasting.update(gamePlayer.getX(), gamePlayer.getY(), gamePlayer);
			System.out.println("Direction " + command.getDirectrion() + " x:" + command.getX() + " y:" + command.getY());




		}

	}

/*
	Brodcast data to the cleint.
*/
	public boolean brodcast(GameObject outGoing){
		if(!socket.isConnected()){
			return false;
		}else{
			try{
				output.writeObject(outGoing);
				output.reset();
				output.flush();
			}catch (IOException e){
				System.out.println("IOException when brodcasting:\n" + e);
			}
		}
		return true;
	}

}
