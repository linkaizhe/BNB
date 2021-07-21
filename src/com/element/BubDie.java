package com.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class BubDie extends ElementObj{

	private long beginTime=0;
	private long currentTime;
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(new ImageIcon("image/Characters/boom_center.png").getImage(), this.getX(), this.getY(),
				 this.getW(), this.getH(), null);
		g.drawImage(new ImageIcon("image/Characters/boom_left_end.png").getImage(), this.getX()-40, this.getY(),
				 this.getW(), this.getH(), null);
		g.drawImage(new ImageIcon("image/Characters/boom_right_end.png").getImage(), this.getX()+40, this.getY(),
				 this.getW(), this.getH(), null);
		g.drawImage(new ImageIcon("image/Characters/boom_up_end.png").getImage(), this.getX(), this.getY()-40,
				 this.getW(), this.getH(), null);
		g.drawImage(new ImageIcon("image/Characters/boom_down_end.png").getImage(), this.getX(), this.getY()+40,
				 this.getW(), this.getH(), null);
	}

	@Override
	public ElementObj createElement(String str) {
		String [] split=str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
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
