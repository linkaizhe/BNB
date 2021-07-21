package com.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.manager.GameLoad;

public class NonPlayer extends ElementObj{
	
	private boolean nleft = false;
	private boolean nup = false;
	private boolean nright = false;
	private boolean ndown = false;
	private String nfx = "ndown";

	public NonPlayer() {}
	public NonPlayer(int x, int y, int w, int h, ImageIcon icon) {
		super(x, y, w, h, icon);
	}
	
	@Override
	public ElementObj createElement(String str) {
		String [] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(new Integer(split[1]));
		ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
		this.setW(35);
		this.setH(40);
		this.setIcon(icon2);
		return this;
	}
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}
	
	@Override
	protected void updateImage(long time) {
		this.setIcon(GameLoad.imgMap.get(nfx));
	}
	
	@Override
	public String toString() {
		int x = this.getX();
		int y = this.getY();
		switch(this.nfx) {
		case "nup" : break;
		case "nleft" : break;
		case "nright" : break;
		case "ndown" : break;
		}
		return "x:"+x+",y:"+y+",f:"+this.nfx;
	}
}
