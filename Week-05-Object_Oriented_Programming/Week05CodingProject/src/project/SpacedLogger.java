package project;

public class SpacedLogger implements Logger {

	@Override
	public void log(String text) {
		// TODO Auto-generated method stub
		System.out.printf("%s%n", text.replace("", " ").trim());
	}

	@Override
	public void error(String text) {
		// TODO Auto-generated method stub
		System.out.printf("Error: %s%n", text.replace("", " ").trim());
	}

}
