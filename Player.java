package it.uni.provaserver;

public class Player {

	private final static double INITIAL_SCORE=0;
	private String name;
	private double score;
	
	public Player(String nome) {
		name=nome;
		score=INITIAL_SCORE;
	}
	
	public void modScore(double value) {
		score=score+value;
	}
	
	public double getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
}
