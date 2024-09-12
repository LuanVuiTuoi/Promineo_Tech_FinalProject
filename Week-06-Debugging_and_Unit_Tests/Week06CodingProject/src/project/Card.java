package project;


public class Card {
	private int value; // contains a value from 2-14 representing cards 2-Ace)
	private String name; // (e.g. Ace of Diamonds, or Two of Hearts) 
	
	public Card() {
		this.value = 0;
		this.name = "";
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void describe() {
		switch(this.getValue()) {
		case 2:
			System.out.println("Two of "+this.getName());
			break;
		case 3:
			System.out.println("Three of "+this.getName());
			break;
		case 4:
			System.out.println("Four of "+this.getName());
			break;
		case 5:
			System.out.println("Five of "+this.getName());
			break;
		case 6:
			System.out.println("Six of "+this.getName());
			break;
		case 7:
			System.out.println("Seven of "+this.getName());
			break;
		case 8:
			System.out.println("Eight of "+this.getName());
			break;
		case 9:
			System.out.println("Nine of "+this.getName());
			break;
		case 10:
			System.out.println("Ten of "+this.getName());
			break;
		case 11:
			System.out.println("Jack of "+this.getName());
			break;
		case 12:
			System.out.println("Queen of "+this.getName());
			break;
		case 13:
			System.out.println("King of "+this.getName());
			break;
		case 14:
			System.out.println("Ace of "+this.getName());
			break;
		default:
			System.err.println("Card Value Does Not Exists.");
		}
	}
}
