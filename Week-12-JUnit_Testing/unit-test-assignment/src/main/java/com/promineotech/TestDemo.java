package com.promineotech;

import java.util.Random;

public class TestDemo {
	public int addPositive(int a, int b) {
		if(a <=0 || b <= 0) {
			throw new IllegalArgumentException("Both parameters must be positive!");
		}
		
		return a+b;
	}
	
	public int areaOfSquare(int a, int b) {
		if(a <= 0 || b <= 0 || a!=b) {
			throw new IllegalArgumentException("Both numbers needs to be the same and larger than 0"
);		}
		return a*b;
	}
	
	public int randomNumberSquared() {
		int randNum = getRandomInt();
		return randNum*randNum;
	}

	int getRandomInt() {
		Random random = new Random();
	    return random.nextInt(10) + 1;
	}
}
