package it.uni.provaserver;

import java.io.Serializable;
import java.util.Vector;

public class Question implements Serializable{
	
	private String testo;
	//private Vector<String> codici;
	
	public Question(String text /*Vector<String> codes*/){
		testo = text;
		//codici = codes;
	}
	
	public String getTesto(){
		return testo;
	}
	
	//public Vector<String> getCodici(){
	//	return codici;
	//}

}
