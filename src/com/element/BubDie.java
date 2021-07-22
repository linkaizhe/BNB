package com.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class BubDie extends ElementObj{

	private long beginTime=0;
	private long currentTime;
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(),
				 this.getW(), this.getH(), null);
	}

	@Override
	public ElementObj createElement(String str) {
		String [] split=str.split(",");
		String op="image/Characters/boom_"+split[0]+"_end.png";
		  ImageIcon icon=new ImageIcon(op);
		  this.setIcon(icon);
		  this.setX(Integer.parseInt(split[1]));
		  this.setY(Integer.parseInt(split[2]));
		this.setW(40);
		this.setH(40);
		return this;
	}
	
	@Override
	protected void updateImage(long gameTime) {
		if(beginTime==0)
			beginTime=gameTime;
		currentTime=gameTime;
		if(currentTime-beginTime>=10) {
			this.setLive(false);
		}
	}
}
