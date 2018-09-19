package it.uni.provaserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Server extends Thread {
	
	private static final int PORT = 9999;
	private static final int NO_PLAYERS = 0;
	private static final int MAX_PLAYERS = 4;
	
	
	private ServerSocket server;
	private int playersNumber;
	private Players players;
	
	public static void main(String[] args) throws Exception {
		new Server();
	}
	
	public Server() throws Exception {
		players= new Players();
		playersNumber=NO_PLAYERS;
		server = new ServerSocket(PORT);
		this.start();
	}
	
	
	public void run() {
		
		int connectedClients=0;
		while (true && connectedClients<MAX_PLAYERS) {
			try {
				Socket client = server.accept();
				System.out.println(client.getInetAddress().toString());
				if (client!=null) {
					connectedClients ++;
					Connect c = new Connect(client,this,players);
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	public int getPlayersNumber() {
		return playersNumber;
	}

	public void addPlayer() {
		playersNumber++;
	}
	
}