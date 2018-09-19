package it.uni.provaserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Collections;
import java.util.Vector;

public class Connect extends Thread {
	
	private static final double POINTS_RIGHT_ANSWER = 100;
	private static final double POINTS_WRONG_ANSWER = 0;
	

	private Server server;
	private Socket client;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private Player player;
	private Players players;
	
	
	public Connect(Socket _client, Server _server, Players _players) {
		server=_server;
		client = _client;
		players=_players;
		try {
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
		
		} catch (IOException e){
			try {
				client.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			  }
		  }
		this.start();
	}
	
	public void run() {
		
		Choice a = choiceCreator();
		
		try {

			sendRanking();
			
			addNewPlayer();
			
			sendRanking();
			
			/*do{
				out.writeObject(server.getPlayersNumber());
		    }while(server.getPlayersNumber()<4);*/
			

			out.writeObject("START");
			
			out.writeObject(a);
			
			boolean responso = answerCheck(a);
		
			out.writeObject(responso);
			
			out.flush();
			out.close();
			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void sendRanking() throws IOException {
		out.writeObject(players.toString());
	}

	private void addNewPlayer() throws IOException, ClassNotFoundException {
		String playerName=(String)in.readObject();
		player= new Player(playerName);
		players.getGiocatori().add(player);
		server.addPlayer();
	}

	private Choice choiceCreator(){
		Vector<Option> optionA = new Vector<>();
		optionA.add(new Option("A",1));
		optionA.add(new Option("B",2));
		optionA.add(new Option("C",3));
		optionA.add(new Option("D",4));
		Choice a = new Choice(new Question("domanda"),optionA, 2);
		return a;
	}
	
	private boolean answerCheck(Choice a) throws ClassNotFoundException, IOException{
		
		Integer accesa=null;
		boolean scossa;
		
		
		do{
			accesa=(Integer)in.readObject();
		}while(accesa==null);
	
		if(accesa.equals(a.getCorrectAnswer())==true) {
			scossa= true;
			player.modScore(POINTS_RIGHT_ANSWER);
		}
		else {
			scossa=false;
			player.modScore(POINTS_WRONG_ANSWER);
		}
		
		return scossa;
	}
}