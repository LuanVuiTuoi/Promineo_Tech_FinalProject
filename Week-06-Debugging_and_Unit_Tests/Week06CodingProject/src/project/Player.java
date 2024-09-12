package project;
import java.util.*;

public class Player {
	private List<Card> hand = new ArrayList<Card>();
	public int score;
	public String name;
	
	public Player() {
		this.score = 0;
		this.name = "";
	}
	
	public Player(String name) {
		this.score = 0;
		this.name = name;
	}
	
	// prints out information about the player 
	// and calls the describe method for each card in the Hand List
	public void describe() {
		System.out.println(this.name);
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
		this.score += 1;
	}

}
