package project;
import java.util.*;

public class Deck {
	private List<Card> cards = new ArrayList<Card>();
	
	// In the constructor, when a new Deck is instantiated, 
	// the Cards field should be populated with the standard 52 cards.
	public Deck() {
		// Outer for loop used to set the suits of the card
		// Inner for loop used to set the value of the card
		for(int suit = 0; suit < 4; suit++) {
			for(int rank = 2; rank < 15; rank++) {
				Card c = new Card();
				if(suit == 0) {
					c.setValue(rank);
					c.setName("Spades");
				}else if(suit == 1) {
					c.setValue(rank);
					c.setName("Hearts");			
				}else if(suit == 2) {
					c.setValue(rank);
					c.setName("Diamonds");
				}else {
					c.setValue(rank);
					c.setName("Clubs");
				}
				
				cards.add(c);
				
			}
		}
	}
	
	//Randomize the order of cards
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	//Removes and returns the top card of the Cards field
	public Card draw() {
		return cards.remove(0);
	}
}
