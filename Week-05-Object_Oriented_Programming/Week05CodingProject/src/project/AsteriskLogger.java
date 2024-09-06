package project;

public class AsteriskLogger implements Logger {

	@Override
	public void log(String text) {
		// TODO Auto-generated method stub
		System.out.printf("***%s***%n", text);
	}

	@Override
	public void error(String text) {
		// TODO Auto-generated method stub
		String padding = "";
		for(int i = 0; i < text.length() + 13; i++) {
			padding += "*";
		}
		
		System.out.println(padding);
		System.out.printf("***Error: %s***%n", text);
		System.out.println(padding);
		
	}
	

}
