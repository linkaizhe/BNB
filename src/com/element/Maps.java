package com.element;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Maps extends ElementObj{
	
	private boolean BreakWall;
	private String name;
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(),
				 this.getW(), this.getH(), null);
	}

	@Override
	public ElementObj createElement(String str) {
		String []arr=str.split(",");
		ImageIcon icon=null;
		switch(arr[0]) {
		case "ORANGE": icon=new ImageIcon("image/Characters/box_01.png"); break;
		case "RED": icon=new ImageIcon("image/Characters/box_02.png"); break;
		case "GREEN": icon=new ImageIcon("image/Characters/box_03.png"); break;
		case "BOX": icon=new ImageIcon("image/Characters/box_04.png"); break;
		case "DARKBLUE": icon=new ImageIcon("image/Characters/box_05.png"); break;
		case "BLUE": icon=new ImageIcon("image/Characters/box_06.png"); break;
		case "XUEGAOTONG": icon=new ImageIcon("image/Characters/box_07.png"); break;
		case "JINGGAO": icon=new ImageIcon("image/Characters/box_08.png"); break;
		case "GREY": icon=new ImageIcon("image/Characters/box_09.png"); break;
		case "BOXTWO": icon=new ImageIcon("image/Characters/box_10.png"); break;
		case "FLOOR": icon=new ImageIcon("image/Characters/floor.png"); break;
		case "TREE": icon=new ImageIcon("image/Characters/tree.png"); break;
		case "YELLOWHOUSE": icon=new ImageIcon("image/Characters/yellowhouse.png"); break;
		case "BORDER": icon=new ImageIcon("image/Characters/border.png"); break;
		case "BLUEHOUSE": icon=new ImageIcon("image/Characters/bluehouse.png"); break;
		}
		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		int w=icon.getIconWidth();
		int h=icon.getIconHeight();
		
		int width=w*40/32;
        int height=h*40/32;
        Image img=icon.getImage().getScaledInstance(width, height, Image.SCALE_FAST);//第三个值可以去查api是图片转化的方式
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
