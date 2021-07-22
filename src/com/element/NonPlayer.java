package com.element;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import com.game.GameStart;
import com.manager.ElementManager;
import com.manager.GameElement;
import com.manager.GameLoad;

public class NonPlayer extends ElementObj{
	
	public final static int ASPEED = 5;
	private boolean nleft = false;
	private boolean nup = false;
	private boolean nright = false;
	private boolean ndown = false;
	private boolean pkType = false;
	private String nfx = "ndown";
	protected int speed=ASPEED;//移动速度
	protected int changeDirectionCount=0;
	protected boolean Ischange=false;
	
	private int lastX;
	private int lastY;
	private String refuseMove;

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
	public void keyClick(boolean bl,int key){//只有按下或者松开才会调用此方法
		if(bl){
			switch(key){
				case 10: this.pkType = true; break;
				case 37: this.nright = false;this.ndown = false;this.nup = false;
					this.nleft = true;this.nfx = "nleft"; break;
				case 38: this.ndown = false;this.nright = false;this.nleft = false;
					this.nup = true;this.nfx = "nup"; break;
				case 39: this.nleft = false;this.nup = false;this.ndown = false;
					this.nright = true;this.nfx = "nright"; break;
				case 40: this.nup = false;this.nright = false;this.nleft = false;
					this.ndown = true;this.nfx = "ndown"; break;
				}
			}else{
				switch(key){
				case 10:this.pkType = false; break;
				case 37:this.nleft = false; break;
				case 38:this.nup = false; break;
				case 39:this.nright = false; break;
				case 40:this.ndown = false; break;
				}
			}
			if(refuseMove == nfx) {
				this.nleft = false;
				this.nup = false;
				this.nright = false;
				this.ndown = false;
			}else {
				refuseMove="";
			}
		}
		@Override
		public void move(){
			lastX=this.getX();
			lastY=this.getY();
			if(this.nleft && this.getX()>0){
				this.setX(this.getX()-speed);
			}
			if(this.nup&&this.getY()>0){
				this.setY(this.getY()-speed);
			}
			if(this.nright&&this.getX()<1200-this.getW()-50){
				this.setX(this.getX()+speed);
			}
			if(this.ndown&&this.getY()<900-this.getH()-70){
				this.setY(this.getY()+speed);
			}
		}
	@Override
	protected void updateImage(long time) {
		this.setIcon(GameLoad.imgMap.get(nfx));
	}
	@Override
	protected void add(long gameTime) {
		if(!this.pkType) {
			return;
		}
		this.pkType=false;
		ElementObj element = new Bubble(gameTime).createElement(this.toString());
		ElementManager.getManager().addElement(element, GameElement.BUBBLE);
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
	@Override
	public void moveback() {
		this.setX(lastX);
		this.setY(lastY);
		refuseMove=nfx;
	}
	
	@Override
	public void changeDirection(int lastTime) {
		Ischange=true;
		speed=-speed;
		Timer timer = new Timer(true);
		changeDirectionCount++;
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				changeDirectionCount--;
				if(changeDirectionCount==0) {
					speed = ASPEED;	
					Ischange=false;
				}
			}
		};
		timer.schedule(task,lastTime*1000);
	}
	
	@Override
	public void deletechange(){
		if(Ischange==true)
		{
			speed=ASPEED;
			Ischange=false;
		}
	}
	
	
	@Override
	public void die() {
		GameStart.toEndJPanel();
	}
}
