package com.niuniu.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class SPanel extends JPanel {

	paintThread paintThread = new paintThread();
	private boolean gameOver = false;

	private int ROWS;
	private int COLS;
	private int BLOCK_SIZE;

	private int score = 0;

	Snake snake = new Snake(this);
	Egg e = new Egg();

	Image offScreenImage = null;

	public SPanel() {

	}

	public SPanel(int rows, int cols, int blockSize) {

		this();

		this.ROWS = rows;
		this.COLS = cols;
		this.BLOCK_SIZE = blockSize;
		
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				snake.keyPress(e);
			}
		});

		new Thread(paintThread).start();
	}

	

	public void paint(Graphics g) {
		super.paint(g);
//		 System.out.printf("w = %d,h=%d\n",getWidth() ,getHeight());
//		 g.drawLine(0, 0, 600, 600);
		Color c = g.getColor();
		g.setColor(new Color(232, 121, 68));
		g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
	
		g.setFont(new Font("Verdana", Font.BOLD, 20));
		g.setColor(Color.YELLOW);
		g.drawString("score:" + score, 5, 20);

		
		if (gameOver) {
			g.setFont(new Font("宋体", Font.BOLD, 50));
			g.drawString("GAME OVER!", 140, 200);
			paintThread.gameOver();
		}

//		g.setColor(c);

		snake.eat(e);
		e.draw(g);
		snake.draw(g);

	}

	public void stop() {
		gameOver = true;
	}

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(COLS * BLOCK_SIZE + 6, ROWS * BLOCK_SIZE + 33);

		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private class paintThread implements Runnable {

		private boolean running = true;
		private int speed = 100;

		@Override
		public void run() {

			while (running) {
				repaint();
				try {
					Thread.sleep(SnakeFrame.speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		public void gameOver() {
			running = false;
		}
		
	}

//	public void keyPress(KeyEvent e) {
//		
//		
//		snake.keyPress(e);
//		
//	}

}
