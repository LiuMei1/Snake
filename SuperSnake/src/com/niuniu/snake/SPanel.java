package com.niuniu.snake;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SPanel extends JPanel {

	PaintThread paintThread = new PaintThread();
	private boolean gameOver = false;
	private boolean pause = false;

	private int ROWS;
	private int COLS;
	private int BLOCK_SIZE;

	private int score = 0;
	private int speed;

	private BufferedImage image;
	// 得到一个播放音频的实例
	private AudioClip ac;

	Snake snake = new Snake(this);
	Egg e = new Egg();

	Image offScreenImage = null;
	// 音乐是否停止
	private boolean musicOver = true;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public void changePause() {
		pause = !pause;
		musicOver = !musicOver;
		backgroundMusic();
	}

	public void stop() {
		gameOver = true;
	}

	public SPanel() {

		setFocusable(true);
		addKeyListener(new PanelLinster());

		try {
			image = ImageIO.read(new File("snake4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		new Thread(paintThread).start();
		backgroundMusic();

	}

	public void display(int rows, int cols, int blockSize) {

		this.ROWS = rows;
		this.COLS = cols;
		this.BLOCK_SIZE = blockSize;
	}

	private void clearDraw(Graphics g) {

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setFont(new Font("Verdana", Font.BOLD, 20));
		g.setColor(Color.YELLOW);
		g.drawString("score:" + score, 5, 20);
	}

	public void paint(Graphics g) {
		// System.out.printf("w = %d,h=%d\n",getWidth() ,getHeight());

		clearDraw(g);
		if (snake != null && e != null) {
			snake.eat(e);
			e.draw(g);
			snake.draw(g);
		}
		if (gameOver) {
			recover(g);
			musicOver = false;
			backgroundMusic();
			paintThread.gameOver();
		}

	}

	/**
	 * 重写update方法，减少闪烁
	 */
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(COLS * BLOCK_SIZE + 6, ROWS * BLOCK_SIZE + 33);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	private void recover(Graphics g) {
		clearDraw(g);
		g.setFont(new Font("宋体", Font.BOLD, 50));
		g.drawString("GAME OVER!", 160, 300);

	}

	/**
	 * 背景音乐
	 * 
	 * @param file
	 */
	public void backgroundMusic() {
		try {
			if (musicOver) {
				File fileMusic = new File("超级玛丽 - 超级玛丽.wav");
				URI uri = fileMusic.toURI();
				URL url;
				url = uri.toURL();
				ac = Applet.newAudioClip(url);
				// 循坏播放
				ac.loop();
			} else {
				ac.stop();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * 线程
	 * 
	 * @author LiuMei
	 *
	 */
	private class PaintThread implements Runnable {

		private boolean running = true;

		@Override
		public void run() {

			while (running) {
				if (!pause) {
					repaint();
				}
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

	/**
	 * 键盘监听
	 * 
	 * @author LiuMei
	 *
	 */
	private class PanelLinster extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_P) {
				changePause();
			} else {
				snake.keyPress(e);
			}
		}
	}

}
