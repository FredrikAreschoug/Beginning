package post.server;


import post.shared.GameObject;
import post.shared.GamePlayer;

/*
	Brodcast data to clients
*/

public class Brodcasting implements Runnable {

	public Brodcasting() {

	}
/*
	Checks if player is close enough to get update 
*/
	public void update(float x,float y, GamePlayer outGoing){
		for(int i = 0; i < Server.server.clientList.size(); i++){
			if (outGoing.getId() != Server.server.clientList.get(i).gamePlayer.getId()){
				float vX, vY;
				GameObject go = Server.server.clientList.get(i).gamePlayer;
				vX = x - go.getX();
				vY = y - go.getY();



				float c = (float)Math.pow(vX,2)+ (float)Math.pow(vY,2);


				float distance = (float)Math.sqrt((double)c);
				if(distance < 100){
					Server.server.clientList.get(i).brodcast(outGoing);
				}
			}

		}
	}

/*
	Loops to send out data to all nearby clients.
*/
	@Override
	public void run() {

		long time, currentTime, timeDif;
		while (true){
			time = System.currentTimeMillis();

			if (Server.server.clientList != null){
				for(int i = 0; i < Server.server.clientList.size(); i++){
					for (int j = 0; j < Server.server.allGameObject.size(); j++){
						if(Server.server.clientList.get(i).gamePlayer.getId() != Server.server.allGameObject.get(j).getId()){
							float vX, vY;

							vX = Server.server.clientList.get(i).gamePlayer.getX() - Server.server.allGameObject.get(j).getX();
							vY = Server.server.clientList.get(i).gamePlayer.getY() - Server.server.allGameObject.get(j).getX();


							float c = (float)Math.pow(vX,2)+ (float)Math.pow(vY,2);


							float distance = (float)Math.sqrt((double)c);
							if(distance < 100){
								Server.server.clientList.get(i).brodcast(Server.server.allGameObject.get(j));
							}

						}

					}
				}
			}

			//



			currentTime = System.currentTimeMillis();
			timeDif = time+1000 -currentTime;

			try{
				Thread.sleep(timeDif);
			}catch (Exception e){
				e.printStackTrace();
			}
		}


	}
}
