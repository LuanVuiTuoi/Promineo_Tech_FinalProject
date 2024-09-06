package project;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "The Logger Works!";
		AsteriskLogger aL = new AsteriskLogger();
		SpacedLogger sL = new SpacedLogger();
		
		System.out.println("\nAsterisk Logger\n");
		aL.log(text);
		aL.error(text);
		System.out.println("\nSpace Logger\n");
		sL.log(text);
		sL.error(text);
	}

}
