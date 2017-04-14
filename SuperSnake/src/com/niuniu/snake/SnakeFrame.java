package com.niuniu.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int ROWS = 30; // 行
	public static final int COLS = 30; // 列
	public static final int BLOCK_SIZE = 20;

	private SPanel panel;
	
	
	private MenuBar mb;
	private Menu m,subMenu ;
	private MenuItem closeItem,subItem,openItem,savaItem,exitTtem;


	public SnakeFrame() {
		initUi();
		this.setVisible(true);
	}

	public void initUi() {
		this.setTitle("超级蛇");
		this.setSize(COLS * BLOCK_SIZE + 6, ROWS * BLOCK_SIZE + 58);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setLocationRelativeTo(null);// 居中
		// 尺寸不可变
		this.setResizable(false);

		mb = new MenuBar();
		mb.setFont(new Font("宋体", Font.BOLD, 20));
		
		
		
		m = new Menu("Menu");
		
		subMenu = new Menu("Setting");
		
		subItem = new MenuItem("Start");
		openItem = new MenuItem("Primary");
		savaItem = new MenuItem("Middle");
		closeItem = new MenuItem("Top");
		exitTtem = new MenuItem("Exit");
				
		m.add(subItem);
		m.add(subMenu);
		subMenu.add(openItem);
		subMenu.add(savaItem);
		subMenu.add(closeItem);
		m.add(exitTtem);
		mb.add(m);
		
		setMenuBar(mb);
		
//		subItem.addActionListener();
		
				
				
		
		
		panel = new SPanel(ROWS, COLS, BLOCK_SIZE);
		add(panel);

	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(new Color(232, 121, 68));
		g.fillRect(0, 0, 6,58);
	}

	
}
