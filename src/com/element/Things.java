package com.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Things extends ElementObj{

	protected int x=0;//道具类型
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(),
				 this.getW(), this.getH(), null);
	}

	@Override
	public ElementObj createElement(String str) {
		String [] split=str.split(",");
		String op = null;
		x=Integer.parseInt(split[2]);
			switch (Integer.parseInt(split[2])) {
			case 3: //消除反向行走效应
				op="image/Characters/解药.png";
				break;
			case 4: //玩家产生反向行走效应
				op="image/Characters/鬼头.png";
				break;	
			default: //无东西;
				break;
			}
		ImageIcon icon=new ImageIcon(op);
		this.setIcon(icon);
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		this.setW(40);
		this.setH(40);
		return this;
	}
	public int thingtype(){
		return x;
	}
	}


