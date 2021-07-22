package com.element;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import com.manager.ElementManager;
import com.manager.GameElement;

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
		case "ORANGE": icon=new ImageIcon("image/Characters/box_01.png");Destroy=true; break;
		case "RED": icon=new ImageIcon("image/Characters/box_02.png");Destroy=true; break;
//		case "GREEN": icon=new ImageIcon("image/Characters/box_03.png"); break;
		case "BOX": icon=new ImageIcon("image/Characters/box_04.png");Destroy=true; break;
		case "DARKBLUE": icon=new ImageIcon("image/Characters/box_05.png");Destroy=true; break;
		case "BLUE": icon=new ImageIcon("image/Characters/box_06.png"); Destroy=true;break;
		case "XUEGAOTONG": icon=new ImageIcon("image/Characters/box_07.png"); Destroy=true;break;
		case "JINGGAO": icon=new ImageIcon("image/Characters/box_08.png");Destroy=true; break;
		case "GREY": icon=new ImageIcon("image/Characters/box_09.png");Destroy=true; break;
		case "BOXTWO": icon=new ImageIcon("image/Characters/box_10.png");Destroy=true; break;
//		case "FLOOR": 
//			ElementObj element = new Floor().createElement(this.getX()+","+this.getY());
//			ElementManager.getManager().addElement(element, GameElement.FLOOR);
//			break;
		case "TREE": icon=new ImageIcon("image/Characters/tree.png");Destroy=false; break;
		case "YELLOWHOUSE": icon=new ImageIcon("image/Characters/yellowhouse.png");Destroy=false; break;
		case "BORDER": icon=new ImageIcon("image/Characters/border.png"); Destroy=false;break;
		case "BLUEHOUSE": icon=new ImageIcon("image/Characters/bluehouse.png");Destroy=false; break;
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
	@Override
	public void die() {
		if(this.isdestroy())
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			Random ran=new Random();
			int x=ran.nextInt(10);
			ElementObj element = new Things().createElement(this.getX()+","+this.getY()+","+x);//返回对象实体 初始化数据
		//装入到集合中

		    ElementManager.getManager().addElement(element, GameElement.THINGS);
		}
	}
}
