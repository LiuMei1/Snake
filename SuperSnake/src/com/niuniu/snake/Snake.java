package com.niuniu.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {

	private Node head = null;
	private Node tail = null;
	private int size = 0;

	private Node n = new Node(20, 30, Dir.L);
	private SPanel panel;

	public Snake(SPanel panel) {

		head = n;
		tail = n;
		size = 1;
		this.panel = panel;
	}

	public void addToTail() {
		Node node = null;
		switch (tail.dir) {
		case L:
			node = new Node(tail.row, tail.col + 1, tail.dir);
			break;
		case U:
			node = new Node(tail.row + 1, tail.col, tail.dir);
			break;
		case R:
			node = new Node(tail.row, tail.col - 1, tail.dir);
			break;
		case D:
			node = new Node(tail.row - 1, tail.col, tail.dir);
			break;
		}
		tail.next = node;
		node.prev = tail;
		tail = node;
		size++;
	}

	public void addToHead() {
		Node node = null;
		switch (head.dir) {
		case L:
			node = new Node(head.row, head.col - 1, head.dir);
			break;
		case U:
			node = new Node(head.row - 1, head.col, head.dir);
			break;
		case R:
			node = new Node(head.row, head.col + 1, head.dir);
			break;
		case D:
			node = new Node(head.row + 1, head.col, head.dir);
			break;
		}
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}

	public void draw(Graphics g) {
		if (size < 0)
			return;

		move();
		for (Node n = head; n != null; n = n.next) {
			n.draw(g);
		}

	}

	private void move() {

		// 把后面截出放在脑袋上，如果头需要不一样，就把放在第二个。
		addToHead();
		deleteFromTail();
		checkDead();
	}

	private void checkDead() {

		if (head.row < 0 || head.col < 0 || head.row > SnakeFrame.ROWS || head.col > SnakeFrame.COLS) {
			panel.stop();
		}

		for (Node n = head.next; n != null; n = n.next) {
			if (head.row == n.row && head.col == n.col) {
				panel.stop();
			}
		}
	}

	private void deleteFromTail() {

		if (size == 0)
			return;
		tail = tail.prev;
		tail.next = null;

	}

	public void eat(Egg e) {

		if (this.getRect().intersects(e.getRect())) {
			e.reApper();
			this.addToHead();
			panel.setScore(panel.getScore() + 5);
		}
	}

	private Rectangle getRect() {
		return new Rectangle(SnakeFrame.BLOCK_SIZE * head.col, SnakeFrame.BLOCK_SIZE * head.row, head.w, head.h);
	}

	public void keyPress(KeyEvent e) {

		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			if (head.dir != Dir.R)
				head.dir = Dir.L;
			break;
		case KeyEvent.VK_UP:
			if (head.dir != Dir.D)
				head.dir = Dir.U;
			break;
		case KeyEvent.VK_RIGHT:
			if (head.dir != Dir.L)
				head.dir = Dir.R;
			break;
		case KeyEvent.VK_DOWN:
			if (head.dir != Dir.U)
				head.dir = Dir.D;
			break;

		}
	}

	private class Node {
		int w = SnakeFrame.BLOCK_SIZE;
		int h = SnakeFrame.BLOCK_SIZE;
		int row, col;
		Dir dir = Dir.L;
		Node next = null;
		Node prev = null;

		public Node(int row, int col, Dir dir) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
		}

		public void draw(Graphics g) {

			Color c = g.getColor();
			g.setColor(new Color(253, 52, 47));
			g.fillOval(SnakeFrame.BLOCK_SIZE * col, SnakeFrame.BLOCK_SIZE * row, w, h);
			g.setColor(c);
		}

	}

}
