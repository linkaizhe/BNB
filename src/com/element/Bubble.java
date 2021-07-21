package com.element;

import java.awt.Graphics;
import java.util.Date;

import javax.swing.ImageIcon;

import com.manager.ElementManager;
import com.manager.GameElement;

public class Bubble extends ElementObj{

	private int power=1;//威力
	private int sum=1;//数量
	private ImageIcon icon;
	private static int Width=40;
	private static int Height=40;
	private long beginTime;
	private long currentTime;
	
	public Bubble(long gameTime) {
		ImageIcon icon = new ImageIcon("image/Characters/pop.png");
		this.setIcon(icon);
		this.setH(Height);
		this.setW(Width);
		beginTime = gameTime;
	};
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(new ImageIcon("image/Characters/box_03.png").getImage(), this.getX(), this.getY(),
				 this.getW(), this.getH(), null);
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(),
				 this.getW(), this.getH(), null);
	}
	
	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		for(String str1:split) {//x:3
			String[] split2 = str1.split(":");//0下标是x,y,f 1下标是值
			switch(split2[0]) {
			case "x": 
				int xx=Integer.parseInt(split2[1])-35;
				if(xx%40!=0) {
					xx/=40;
					xx*=40;
					if(xx%40>=20)
						this.setX(xx+75);
					else
						this.setX(xx+35);
				}
				this.setX(xx+35);
				break;
			case "y": 
				int yy=Integer.parseInt(split2[1])-35;
				if(yy%40!=0) {
					yy/=40;
					yy*=40;
					if(yy%40>=20)
						this.setY(yy+75);
					else
						this.setY(yy+35);
				}
				this.setY(yy+75);
				break;
			}
		}
		return this;
		
	}

	@Override
	protected void updateImage(long gameTime) {
		currentTime=gameTime;
		if(currentTime-beginTime>=30) {
			this.setLive(false);
		}
	}
	
	@Override
	public void die() {
		ElementObj element = new BubDie().createElement(this.getX()+","+this.getY());//返回对象实体 初始化数据
		//装入到集合中
		ElementManager.getManager().addElement(element, GameElement.DIE);
	}
	
}
