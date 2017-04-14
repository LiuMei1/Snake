package com.niuniu.snake;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Test {

	private JFrame frame = new JFrame("背景图片测试");

	private JPanel imagePanel;

	private ImageIcon background;

	public static void main(String[] args) {

		new Test();
	}

	public Test() {
		background = new ImageIcon("snake.jpg");

		JLabel label = new JLabel(background);
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		imagePanel = (JPanel) frame.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());
		imagePanel.add(new JButton("测试按钮"));
		frame.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(background.getIconWidth(), background.getIconHeight());
		frame.setResizable(false);
		frame.setVisible(true);

	}

}
