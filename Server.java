package it.uni.provaserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.Vector;


public class Server {

	private static final double POINTS_RIGHT_ANSWER = 100;
	private static final double POINTS_WRONG_ANSWER = 0;

	public static void main(String[] args) throws ClassNotFoundException {
	
		int port = 9999;
		
		Player giocatore= new Player("bug");
		Integer accesa=null;
		String scossa;
		
		try(	
				ServerSocket server = new ServerSocket(port);
				Socket client = server.accept();
				ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
				ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
		){
		
			Vector<Option> optionA = new Vector<>();
			optionA.add(new Option("A",1));
			optionA.add(new Option("B",2));
			optionA.add(new Option("C",3));
			optionA.add(new Option("D",4));
			Choice a = new Choice(new Question("domanda"),optionA, 2);
			out.writeObject(a); 
			System.out.println("mandato");
			accesa=(Integer)in.readObject();
			System.out.println("accesa");
			if(accesa==a.getCorrectAnswer()) {
				scossa= "va beeneeeee";
				giocatore.modScore(POINTS_RIGHT_ANSWER);
			}
			else {
				scossa="BZZZZZZZ";
				giocatore.modScore(POINTS_WRONG_ANSWER);
			}
			out.writeObject(scossa);
			
			
		} catch (IOException e){
			e.printStackTrace();
		}
	
	}
	
}