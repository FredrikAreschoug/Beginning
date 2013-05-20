package post.server;

import post.shared.*;

/*
	Controlls players and outher entitys
	right now really simple just to see everything is working 
*/

public class ServerLoop implements Runnable{

	public static boolean stillCheck = true;


	public ServerLoop() {
	}

	@Override
	public void run() {
		long time, currentTime, timeDif;
		while (stillCheck){
			time = System.currentTimeMillis();


			//all moving and stuffs in here
			players();
			npc();

			//



			currentTime = System.currentTimeMillis();
			timeDif = time+16 -currentTime;

			try{
				Thread.sleep(timeDif);
			}catch (Exception e){
				e.printStackTrace();
			}
		}

	}


	private void players(){

		for(int i = 0; i < Server.server.clientList.size(); i++){

			switch (Server.server.clientList.get(i).gamePlayer.getDirection()){
				case 0:

					break;
				case 1:
					Server.server.clientList.get(i).gamePlayer.move(0,-1);
					break;
				case 2:
					Server.server.clientList.get(i).gamePlayer.move(1,0);
					break;
				case 3:
					Server.server.clientList.get(i).gamePlayer.move(0,1);
					break;
				case 4:
					Server.server.clientList.get(i).gamePlayer.move(-1,0);
					break;

			}

		}
	}

	private void npc(){
		for(int i = 0; i < Server.server.NPCList.size(); i++){
			switch (Server.server.NPCList.get(i).getTodo()){
				case 1:
					cutWood(Server.server.NPCList.get(i));
					break;
			}
		}
	}

	private void cutWood(GameNPC npc){


	}

	private GameStatic findRes(int res, GameNPC npc){

	}
}
