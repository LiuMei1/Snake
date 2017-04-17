package com.niuniu.entities;


import java.awt.Color;
import java.awt.Graphics;

import com.niuniu.util.Global;

public class Hinder {
	private static boolean[][] rocks = new boolean[Global.ROWS][Global.COLS];
	private Color color;

	/**
	 * 障碍
	 */
	public void generateRocks1() {
		for (int x = 0; x < Global.ROWS; x++)
			rocks[x][0] = rocks[x][Global.COLS - 1] = true;
		for (int y = 0; y < Global.COLS; y++)
			rocks[0][y] = rocks[Global.ROWS - 1][y] = true;
	}

	public void generateRocks2() {

		for (int y = 0; y < 6; y++) {
			rocks[0][y] = true;
			rocks[Global.ROWS - 1][y] = true;
			rocks[0][Global.COLS - 1 - y] = true;
			rocks[Global.ROWS - 1][Global.COLS - 1 - y] = true;
		}
		for (int y = 6; y < Global.COLS - 6; y++) {
			rocks[6][y] = true;
			rocks[Global.ROWS - 7][y] = true;
		}
	}

	public void generateRocks3() {
		for (int x = 4; x < 14; x++)
			rocks[x][5] = true;
		for (int j = 5; j < 15; j++)
			rocks[21][j] = true;
		for (int y = 13; y < 20; y++)
			rocks[14][y] = true;
		for (int x = 2; x < 10; x++)
			rocks[x][17] = true;
		for (int i = 10; i < Global.ROWS; i++)
			rocks[i][Global.COLS - 3] = true;
	}

	public void hinderDraw(Graphics g) {
		//可以选
		generateRocks2();
		
		g.setColor(color.WHITE);

		for (int x = 0; x < Global.ROWS; x++) {
			for (int y = 0; y < Global.COLS; y++) {
				if (rocks[x][y] == true) {
					g.fill3DRect(x * Global.BLOCK_SIZE, y
							* Global.BLOCK_SIZE, Global.BLOCK_SIZE,
							Global.BLOCK_SIZE, true);
				}
			}
		}
	}
}

