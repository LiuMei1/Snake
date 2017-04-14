package com.niuniu.snake;

import javax.swing.JDialog;

public class SnakeFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int ROWS = 30; // 行
	public static final int COLS = 30; // 列
	public static final int BLOCK_SIZE = 20;
	public static int speed ;

	private SPanel panel;

	public SnakeFrame() {

	}

	public SnakeFrame(int speed) {
		this.speed = speed;
		initUi();
	}

	public void initUi() {
		this.setTitle("超级蛇");
		this.setSize(COLS * BLOCK_SIZE + 6, ROWS * BLOCK_SIZE + 33);
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.setLocationRelativeTo(null);// 居中
		// 尺寸不可变
		this.setResizable(false);
		

		panel = new SPanel(ROWS, COLS, BLOCK_SIZE);
		add(panel);

	}

}
