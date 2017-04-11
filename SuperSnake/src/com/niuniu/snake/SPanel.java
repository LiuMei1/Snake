package com.niuniu.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

public class SPanel extends Panel{
	private int ROWS;
	private int COLS;
	private int BLOCK_SIZE;
	
	Snake snake = new Snake();

	public SPanel(int rows, int cols, int blockSize) {

		this.ROWS = rows;
		this.COLS = cols;
		this.BLOCK_SIZE = blockSize;
	}
	
	public void paint (Graphics g){
		super.paint(g);
//		System.out.printf("w = %d,h=%d\n",getWidth() ,getHeight());
//		g.drawLine(0, 0, 500, 500);
	     Color c  = g.getColor();
	     g.setColor(Color.gray);
	     g.fillRect(0, 0,COLS*BLOCK_SIZE, ROWS*BLOCK_SIZE);
	     g.setColor(Color.BLACK);
//	     画出横线
	     for(int i=1;i<=ROWS;i++){
	    	 g.drawLine(0, BLOCK_SIZE*i, COLS*BLOCK_SIZE, BLOCK_SIZE*i);
	     }
	     for(int i=1;i<=ROWS;i++){
	    	 g.drawLine( BLOCK_SIZE*i,0,  BLOCK_SIZE*i,ROWS*BLOCK_SIZE);
	     }
//	     g.setColor(c);
	     
	     
	     snake.drow(g);
	}
	
	
		
	}

