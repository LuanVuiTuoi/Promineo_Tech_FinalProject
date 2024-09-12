package project;

public class App {

	public static void main(String[] args) {
		
		// Instantiate a Deck and two Players, call the shuffle method on the deck.
		Deck deck = new Deck();
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		
		deck.shuffle();
		
		// Using a traditional for loop, 
		// Iterate 52 times calling the Draw method on the other player,
		// each iteration using the Deck you instantiated. 
		for(int i = 0; i < 52; i++) {
			if(i % 2 != 0) {
				player1.draw(deck); // Player 1 draw on odd numbers.
			}else {
				player2.draw(deck);
			}
		}
		
		// Using a traditional for loop, iterate 26 times and call the flip method for each player.
	    // Compare the value of each card returned by the two player’s flip methods. 
		// Call the incrementScore method on the player whose card has the higher value.  
		// Print a message to say which player received a point.
	    // Note:  If the values are equal (it is a tie), 
		// print a message saying that no point was awarded.

		for(int r = 1; r <= 26; r++) {
			Card p1Card = player1.flip();
			Card p2Card = player2.flip();
			
			System.out.println("Round " + r);
			System.out.println("------");
			System.out.print(player1.name + " Card: ");
			p1Card.describe();
			System.out.print(player2.name + " Card: ");
			p2Card.describe();
			
			if(p1Card.getValue() == p2Card.getValue()) {
				System.out.println("Tie: No point awarded.");
			}else if(p1Card.getValue() > p2Card.getValue()) {
				System.out.println(player1.name + " Wins.");
				player1.incrementScore();
			}else {
				System.out.println(player2.name + " Wins.");
				player2.incrementScore();
			}
			
			System.out.printf("Current Score: %s: %d - %s %d %n",
					player1.name,player1.score,player2.name,player2.score);
			System.out.println("------");
			
		}
		
		System.out.printf("Final Score: %s: %d - %s: %d%n",
				player1.name,player1.score,player2.name,player2.score);
		
		if(player1.score > player2.score) {
			System.out.println("Winner: " + player1.name);
		}else if(player1.score < player2.score) {
			System.out.println("Winner: " + player2.name);
		}else {
			System.out.println("Draw");
		}


	}

}
