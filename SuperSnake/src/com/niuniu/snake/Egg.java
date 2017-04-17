package com.niuniu.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.niuniu.util.Global;

public class Egg {
	int row, col;

	private BufferedImage image;

	int w = Global.BLOCK_SIZE;
	int h = Global.BLOCK_SIZE;
	private static Random r = new Random();
	private Color color = Color.GREEN;

	public Egg(int row, int col) {

	
		try {
			image = ImageIO.read(new File("apple2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.row = row;
		this.col = col;
	}

	public Egg() {

		this(r.nextInt(Global.ROWS), r.nextInt(Global.COLS));
	}

	public void reApper() {
		this.row = r.nextInt(Global.ROWS);
		this.col = r.nextInt(Global.COLS);
	}

	/**
	 * 
	 * @return
	 */
	public Rectangle getRect() {
		return new Rectangle(Global.BLOCK_SIZE * col, Global.BLOCK_SIZE * row, w, h);
	}

	public void draw(Graphics g) {

		g.drawImage(image, Global.BLOCK_SIZE * col, Global.BLOCK_SIZE * row, w, h, null);

	}

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
}
