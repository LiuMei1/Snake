package com.niuniu.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.niuniu.util.Global;

public class Snake {

	private Node head = null;
	private Node tail = null;
	private int size = 0;
	private BufferedImage imageU;
	private BufferedImage imageD;
	private BufferedImage imageL;
	private BufferedImage imageR;
	private boolean isColor = true;

	private Node n = new Node(20, 30, Dir.L);
	private SPanel panel;

	public Snake(SPanel panel) {
		
		try {
			imageU = ImageIO.read(new File("emojiU.png"));
			imageD = ImageIO.read(new File("emojiD.png"));
			imageL = ImageIO.read(new File("emojiL.png"));
			imageR = ImageIO.read(new File("emojiR.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		head = n;
		tail = n;
		size = 1;
		this.panel = panel;
		for(int i = 1;i < 3;i++){
			addToTail();
		}
		
	}
	
	
	//改变方向
		public void changeDirection(Dir dir) {
			if(!(head.dir.getValue()+dir.getValue()==0)){
				
				head.dir = dir;		
			}
		}
	

	/**
	 * 添加蛇尾
	 */
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
		//蛇尾后面跟着新的node
		tail.next = node;
		//node的前面是tail
		node.prev = tail;
		//新的node变成新的尾巴
		tail = node;
		size++;
	}

	/**
	 * 添加蛇头
	 */
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
	
	/**
	 * 蛇的移动
	 */
	private void move() {

		// 把后面截出放在脑袋上，如果头需要不一样，就把放在第二个。
		addToHead();
		deleteFromTail();
		checkDead();
	}
	
	
	
	/**
	 * 删除蛇尾
	 */
	private void deleteFromTail() {

		if (size == 0)
			return;
		tail = tail.prev;
		tail.next = null;

	}
	
	/**
	 * 蛇头的范围
	 * @return
	 */
	private Rectangle getRect() {
		return new Rectangle(Global.BLOCK_SIZE * head.col, Global.BLOCK_SIZE * head.row, head.w, head.h);
	}
	
	/**
	 * 吃食物
	 * @param e
	 */
	public void eat(Egg e) {

		if (this.getRect().intersects(e.getRect())) {
			e.reApper();
			this.addToHead();
			panel.setScore(panel.getScore() + 5);
		}
	}

	
	/**
	 * 检查是否死亡
	 */
	private void checkDead() {

		if (head.row < 0 || head.col < 0 || head.row > Global.ROWS || head.col > Global.COLS) {
			panel.stop();
		}

		for (Node n = head.next; n != null; n = n.next) {
			if (head.row == n.row && head.col == n.col) {
				panel.stop();
			}
		}
	}


	/**
	 * 画蛇身
	 * @param g
	 */
	public void draw(Graphics g) {
		if (size < 0)
			return;

		move();
		
		g.setColor(Color.YELLOW);
		for (Node n = head; n != null; n = n.next) {
			n.draw(g);
			if(isColor){
				g.setColor(new Color(244, 172, 4));
			}else{
				g.setColor(Color.YELLOW);
			}
			isColor = !isColor;
		}

		drawHead(g);
	}


	private void drawHead(Graphics g) {
		
		
		switch (head.dir) {
		case L:
			g.drawImage(imageL, Global.BLOCK_SIZE *head.col, 
					Global.BLOCK_SIZE * head.row,Global.BLOCK_SIZE, Global.BLOCK_SIZE, null);
			break;
		case U:
			g.drawImage(imageU, Global.BLOCK_SIZE *head.col, 
					Global.BLOCK_SIZE * head.row,Global.BLOCK_SIZE, Global.BLOCK_SIZE, null);
			break;
		case R:
			g.drawImage(imageR, Global.BLOCK_SIZE *head.col, 
					Global.BLOCK_SIZE * head.row,Global.BLOCK_SIZE, Global.BLOCK_SIZE, null);
			break;
		case D:
			g.drawImage(imageD, Global.BLOCK_SIZE *head.col, 
					Global.BLOCK_SIZE * head.row,Global.BLOCK_SIZE, Global.BLOCK_SIZE, null);
			break;
		}
		
	}


	

	/**
	 * 通过键盘改变蛇的方向
	 * @param e
	 */
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


	/**
	 * 蛇的节点
	 * @author LiuMei
	 *
	 */
	private class Node {
		int w = Global.BLOCK_SIZE;
		int h = Global.BLOCK_SIZE;
		int row, col;
		//方向
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
			
			g.fillOval(Global.BLOCK_SIZE * col, Global.BLOCK_SIZE * row, w, h);

		}

	}

}
