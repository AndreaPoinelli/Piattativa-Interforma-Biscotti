package it.uni.provaserver;

import java.io.Serializable;
import java.util.Vector;

public class Option implements Serializable{

	//private Vector<String> codici;
	private String testo;
	private int iD;
	
	public Option(String text, int iD){
		testo = text;
		this.iD = iD;
		
	}
	
	public String getTesto(){
		return testo;
	}
	
	public int getID(){
		return iD;
	}
}
