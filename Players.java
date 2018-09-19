package it.uni.provaserver;

import java.util.Collections;
import java.util.Vector;

public class Players{

	private Vector<Player> giocatori;
	
	public Players() {
		giocatori=new Vector<Player>();
	}
	 
	public String toString() {

		getRanking();
		StringBuffer ranking=new StringBuffer();
		for(int i=0; i<giocatori.size(); i++) {
			ranking.append(giocatori.get(i).toString()+"\n");
		}
		
		return ranking.toString();
		
	}

	public Vector<Player> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(Vector<Player> giocatori) {
		this.giocatori = giocatori;
	}
	
	public Vector<Player> getRanking() {
		Collections.sort(giocatori);
		return giocatori;
	}
}
