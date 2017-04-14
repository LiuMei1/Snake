package com.niuniu.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	int row, col;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	int w = SnakeFrame.BLOCK_SIZE;
	int h = SnakeFrame.BLOCK_SIZE;
	private static Random r = new Random();
	private Color color = Color.GREEN;

	public Egg(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	public Egg() {

		this(r.nextInt(SnakeFrame.ROWS), r.nextInt(SnakeFrame.COLS));
	}

	public void reApper() {
		this.row = r.nextInt(SnakeFrame.ROWS);
		this.col = r.nextInt(SnakeFrame.COLS);
	}

	/**
	 * 
	 * @return
	 */
	public Rectangle getRect() {
		return new Rectangle(SnakeFrame.BLOCK_SIZE * col, SnakeFrame.BLOCK_SIZE * row, w, h);
	}

	public void draw(Graphics g) {

		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(SnakeFrame.BLOCK_SIZE * col, SnakeFrame.BLOCK_SIZE * row, w, h);
		g.setColor(c);
		if (color == Color.GREEN) {
			color = Color.red;
		} else {
			color = Color.GREEN;
		}

	}
}
