package com.niuniu.snake;

import javax.swing.JDialog;

import com.niuniu.util.Global;

public class SnakeFrame extends JDialog {

	public static int speed;

	private SPanel panel;

	public SnakeFrame() {

	}

	public SnakeFrame(int speed) {
		this.speed = speed;
		initUi();
	}

	public void initUi() {
		this.setTitle("");
		this.setSize(Global.COLS * Global.BLOCK_SIZE + 6, Global.ROWS * Global.BLOCK_SIZE + 33);
		this.setModal(true);
		this.setAlwaysOnTop(true);
		setFocusableWindowState(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.setLocationRelativeTo(null);// 居中
		// 尺寸不可变
		this.setResizable(false);

		panel = new SPanel();
		panel.display(Global.ROWS, Global.COLS, Global.BLOCK_SIZE);
		add(panel);

	}

}
