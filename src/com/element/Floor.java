package com.element;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Floor extends ElementObj{
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(),
				 this.getW(), this.getH(), null);
	}
	
	@Override
	public ElementObj createElement(String str) {
		String []arr=str.split(",");
		ImageIcon icon=null;
		if(arr[0].equalsIgnoreCase("BIG")){
			icon=new ImageIcon("image/Characters/BigBorder.png");
			Destroy=false;
			int x=Integer.parseInt(arr[1]);
			int y=Integer.parseInt(arr[2]);
			int w=icon.getIconWidth();
			int h=icon.getIconHeight();
			this.setH(h);
			this.setW(w);
			this.setX(x);
			this.setY(y);
			this.setIcon(icon);
			return this;
		}
		icon=new ImageIcon("image/Characters/box_03.png");
		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		int w=icon.getIconWidth();
		int h=icon.getIconHeight();
		
		int width=w*40/32;
        int height=h*40/32;
        Image img=icon.getImage().getScaledInstance(width, height, Image.SCALE_FAST);
        ImageIcon icon2=new ImageIcon(img);
		x=x*40/32+35;
		y=y*40/32+35;
		h=icon2.getIconHeight();
		w=icon2.getIconWidth();
		this.setH(h);
		this.setW(w);
		this.setX(x);
		this.setY(y);
		this.setIcon(icon2);
		return this;
	}
}
