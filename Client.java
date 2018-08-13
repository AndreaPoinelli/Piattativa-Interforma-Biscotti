package it.uni.provaclient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;

import it.uni.provaserver.Choice;

public class Client {
	
	private static final int WIDTH=450, HEIGHT=300;
	public String risposta;

	public static void main(String[] args){
		
		final int port = 9999;
		final String hostName = "localhost";
	try (
					Socket client = new Socket(hostName,port);
				    ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
		
			){
		        Choice a = (Choice)in.readObject();
				Graphic g= new Graphic();
				Vector<JButton> answers= g.buttons;
				for(int i=0;i<answers.size();i++) {
					answers.get(i).setText(a.getOptions().get(i).getTesto());
				}
				g.textArea.setText(a.getQuestion().getTesto());
				g.getMainPanel().repaint(); 
			    out.writeObject(gerryScottie(g));
			    String scossa=(String)in.readObject();
			    g.textArea.setText(scossa);
			    	    		
			} catch(IOException e){}
	          catch(ClassNotFoundException e) {}
		} 
	
	private static Integer gerryScottie(Graphic g) {
		Integer d;
		do {
			d=g.getLaccendiamo();
			} while(g.getLaccendiamo()==null);
		g.laSpegnamo();
		return d;
	}
}


