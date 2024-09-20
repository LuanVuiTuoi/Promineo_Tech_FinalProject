package project;
import java.util.*;

public class Player {
	private List<Card> hand = new ArrayList<Card>();
	private int score;
	private String name;
	
	public Player() {
		this.setScore(0);
		this.setName("");
	}
	
	public Player(String name) {
		this.setScore(0);
		this.setName(name);
	}
	
	// prints out information about the player 
	// and calls the describe method for each card in the Hand List
	public void describe() {
		System.out.println(this.getName());
		for(Card card : hand) {
			card.describe();
		}
	}
	
	// removes and returns the top card of the Hand
	public Card flip() {
		return hand.remove(0);
	}
	
	// takes a Deck as an argument and calls the draw method on the deck, 
	// adding the returned Card to the hand field
	public void draw(Deck deck) {
		hand.add(deck.draw());
	}
	
	// adds 1 to the Player’s score field
	public void incrementScore() {
		this.setScore(this.getScore() + 1);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
