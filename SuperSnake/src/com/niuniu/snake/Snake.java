package com.niuniu.snake;

import java.awt.Color;
import java.awt.Graphics;

public class Snake {

	private Node head = null;
	private Node tail = null;
	private int size = 0;
	
	private Node n = new Node(20, 30, Dir.L);

	public Snake() {

		head = n;
		tail = n;
		size = 1;
	}

	public void addToTail() {
		Node node = null;
		switch (tail.dir) {
		case L:
			node = new Node(tail.row, tail.col+1, tail.dir);
			break;
		case U:
			node = new Node(tail.row+1, tail.col, tail.dir);
			break;
		case R:
			node = new Node(tail.row, tail.col-1, tail.dir);
			break;
		case D:
			node = new Node(tail.row-1, tail.col, tail.dir);
			break;
		}
		tail.next = node;
		tail = node;
		size++;
	}
	
	public  void  drow(Graphics g){
		for(Node n = head; n!=null; n=n.next){
			n.draw(g);
		}
	}

	private class Node {
		int w = SnakeFrame.BLOCK_SIZE;
		int h = SnakeFrame.BLOCK_SIZE;
		int row, col;
		Dir dir = Dir.L;
		Node next = null;

		public Node(int row, int col, Dir dir) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
		}

		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(SnakeFrame.BLOCK_SIZE * row, SnakeFrame.BLOCK_SIZE * col, w, h);
			g.setColor(c);
		}

	}

}
