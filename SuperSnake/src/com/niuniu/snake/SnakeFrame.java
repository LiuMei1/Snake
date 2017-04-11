package com.niuniu.snake;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SnakeFrame extends Frame{

	public static final int ROWS = 50;
	public static final int COLS = 50;
	public static final int BLOCK_SIZE = 10;
	
	
	
	 private SPanel panel;
	Image offScreenImage = null;
	
	public SnakeFrame() {
		launch();
	}
	public void launch(){
		this.setTitle("超级蛇");
		this.setLocation(300, 300);
		this.setSize(COLS*BLOCK_SIZE+6, ROWS*BLOCK_SIZE+33);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.setLocationRelativeTo(null);//居中
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//大小不能变
		this.setResizable(false);
		this.setVisible(true);
		panel = new SPanel(ROWS,COLS,BLOCK_SIZE);
		add(panel);
		
		new Thread(new paintThread()).start();
	}
	
	
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null){
			offScreenImage = this.createImage(COLS*BLOCK_SIZE+6, ROWS*BLOCK_SIZE+33);
			
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	private class paintThread implements Runnable{

		@Override
		public void run() {

			while(true){
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private class KeyMoniter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
		}
	}
	
}
