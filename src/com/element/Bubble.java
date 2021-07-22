package com.element;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

import com.manager.ElementManager;
import com.manager.GameElement;

public class Bubble extends ElementObj{

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
				int xx=Integer.parseInt(split2[1])-15;
				if(xx%40!=0) {
					if(xx%40>=20) {
						xx/=40;
						xx*=40;
						this.setX(xx+75);
					}
					else {
						xx/=40;
						xx*=40;
						this.setX(xx+35);
					}
				}
				this.setX(xx+35);
				break;
			case "y": 
				int yy=Integer.parseInt(split2[1])-15;
				if(yy%40!=0) {
					if(yy%40>=20) {
						yy/=40;
						yy*=40;
						this.setY(yy+75);
					}
					else {
						yy/=40;
						yy*=40;
						this.setY(yy+35);
					}
				}
				this.setY(yy+35);
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
		ElementObj element = new BubDie().createElement("center,"+this.getX()+","+this.getY());
		//装入到集合中
		ElementObj element1 = new BubDie().createElement("left,"+(this.getX()-40)+","+this.getY());
		ElementObj element2 = new BubDie().createElement("right,"+(this.getX()+40)+","+this.getY());
		ElementObj element3 = new BubDie().createElement("up,"+this.getX()+","+(this.getY()-40));
		ElementObj element4 = new BubDie().createElement("down,"+this.getX()+","+(this.getY()+40));
		ElementManager.getManager().addElement(element, GameElement.DIE);
		ElementManager.getManager().addElement(element1, GameElement.DIE);
		ElementManager.getManager().addElement(element2, GameElement.DIE);
		ElementManager.getManager().addElement(element3, GameElement.DIE);
		ElementManager.getManager().addElement(element4, GameElement.DIE);
		
		List<ElementObj> maps = ElementManager.getManager().getElementsByKey(GameElement.MAPS);
		List<ElementObj> bubbledie = ElementManager.getManager().getElementsByKey(GameElement.DIE);
		for(int i=0;i<maps.size();i++) {
			ElementObj wall = maps.get(i);
			for(int j=1;j<bubbledie.size();j++) {
				ElementObj Bubdie = bubbledie.get(j);
				if(wall.pk(Bubdie) && !wall.isdestroy()) {
					wall.setLive(true);
					bubbledie.remove(j);
				}
			}
		}
	}
	
}
