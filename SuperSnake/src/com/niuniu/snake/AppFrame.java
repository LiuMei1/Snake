package com.niuniu.snake;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;

public class AppFrame extends JFrame implements ActionListener {

	private JPanel imagePanel;

	private JButton exit;

	private ImageIcon background;

	private JMenuItem primary, middle, top;
	private JMenuItem about;

	private int speed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame frame = new AppFrame();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	public JMenuItem getPrimary() {
		return primary;
	}

	public void setPrimary(JMenuItem primary) {
		this.primary = primary;
	}

	public JMenuItem getMiddle() {
		return middle;
	}

	public void setMiddle(JMenuItem middle) {
		this.middle = middle;
	}

	public JMenuItem getTop() {
		return top;
	}

	public void setTop(JMenuItem top) {
		this.top = top;
	}

	public JMenuItem getAbout() {
		return about;
	}

	public void setAbout(JMenuItem about) {
		this.about = about;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Create the frame.
	 */
	public AppFrame() {
		initUI();
		setVisible(true);
	}

	private void initUI() {

		// 背景图片
		background = new ImageIcon("snake3.jpg");
		// 窗口
		setTitle("蛇蛇");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		setLocationRelativeTo(null);
		setResizable(false);

		// 设置背景
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		// 面板
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
//		 imagePanel = new JPanel();

		imagePanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(imagePanel);
		imagePanel.setLayout(null);

		// 结束按钮
		exit = new JButton("退出");
		exit.setBackground(Color.GREEN);
		exit.setBounds(341, 335, 63, 27);
		exit.addActionListener(this);
		imagePanel.add(exit);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GREEN);
		menuBar.setBounds(14, 335, 63, 27);
		imagePanel.add(menuBar);

		JMenu start = new JMenu("开始");
		start.setBackground(new Color(0, 255, 0));
		menuBar.add(start);

		primary = new JMenuItem("常规");
		start.add(primary);
		primary.addActionListener(this);

		middle = new JMenuItem("中级");
		start.add(middle);
		middle.addActionListener(this);

		top = new JMenuItem("高级");
		start.add(top);
		top.addActionListener(this);
		
		about = new JMenuItem("关于");
		start.add(about);
		about.addActionListener(this);

		this.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object st = e.getSource();
		if (st == exit) {
			System.exit(0);
		}
		if (st == primary) {
			speed = 100;
			SnakeFrame sf = new SnakeFrame(speed);
			sf.setVisible(true);
		}
		if (st == middle) {
			speed = 70;
			SnakeFrame sf = new SnakeFrame(speed);
			sf.setVisible(true);
		}
		if (st == top) {
			speed = 50;
			SnakeFrame sf = new SnakeFrame(speed);
			sf.setVisible(true);
		}
		
		if(st == about){
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
			JOptionPane.showMessageDialog(AppFrame.this,text);
		}
	}
}
