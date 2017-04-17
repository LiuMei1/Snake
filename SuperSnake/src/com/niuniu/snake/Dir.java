package com.niuniu.snake;

public enum Dir {

	L(1),U(2),R(-1),D(-2);
	
	
	private int value;

	private Dir(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
		
	
}
