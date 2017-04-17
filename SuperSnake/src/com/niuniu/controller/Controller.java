package com.niuniu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;

import com.niuniu.entities.Hinder;
import com.niuniu.snake.AppFrame;
import com.niuniu.snake.Dir;
import com.niuniu.snake.Egg;
import com.niuniu.snake.SPanel;
import com.niuniu.snake.Snake;

public class Controller extends KeyAdapter {

	private AppFrame app;
	private SPanel panel;
	private Snake snake;
	private Egg egg;
	private Hinder hinder;
	private Dir dir;

	public Controller(AppFrame app, SPanel panel, Snake snake, Egg egg, Hinder hinder) {

		this.app = app;
		this.panel = panel;
		this.snake = snake;
		this.egg = egg;
		this.hinder = hinder;

		init();
	}

	private void init() {

		// 速度
		app.getPrimary().addActionListener(new spItem1Handler());
		app.getMiddle().addActionListener(new spItem2Handler());
		app.getTop().addActionListener(new spItem3Handler());
		
		//about
		app.getAbout().addActionListener(new aboutItemHandler() );
		
		//退出
		app.getExit().addActionListener(new endHandler() );

	}
	
	/**
	 * 通过键盘改变蛇的方向
	 * @param e
	 */
	public void keyPress(KeyEvent e) {

		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			dir = Dir.L;
			snake.changeDirection(dir);
			break;
		case KeyEvent.VK_UP:
			dir = Dir.U;
			snake.changeDirection(dir);
			break;
		case KeyEvent.VK_RIGHT:
			dir = Dir.R;
			snake.changeDirection(dir);
			break;
		case KeyEvent.VK_DOWN:
			dir = Dir.D;
			snake.changeDirection(dir);
			break;
		case KeyEvent.VK_W:
			
			break;
		case KeyEvent.VK_S:
			
			break;
		case  KeyEvent.VK_P:
			panel.changePause();
			break;

		}
	}
	
	
	

	class spItem1Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			app.setSpeed(100);
		}
	}

	class spItem2Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			app.setSpeed(75);
		}
	}
	
	class spItem3Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			app.setSpeed(50);
		}
	}
	
	class aboutItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			File file = new File("about.txt") ;
			StringBuilder sb = new StringBuilder();
			String text ;
			try(BufferedReader in = new BufferedReader(new FileReader(file)))  {
		
				while(null != (text = in.readLine())){
					System.out.println(text);
					sb.append(text);
					sb.append("\r\n");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			text = sb.toString();
			System.out.println(text);
			JOptionPane.showMessageDialog(app,text);
		}
	}
	
	class endHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}
	

}
