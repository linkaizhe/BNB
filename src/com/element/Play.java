package com.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.element.ElementObj;
import com.manager.GameLoad;

public class Play extends ElementObj{

	private boolean left=false; //左
	private boolean up=false;//上
	private boolean right=false;//右
	private boolean down=false;//下
			
//	图片集合 使用map进行存储
//	private Map<String,ImageIcon> imgMap;
//	变量专门用来记录当前主角面向的方向，默认为down
	private String fx="down";
	public Play(){}
	public Play(int x, int y, int w, int h, ImageIcon icon) {
		super(x, y, w, h, icon);	
	}

	@Override
	public ElementObj createElement(String str) {
		String [] split=str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(new Integer(split[1]));
		ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
		this.setW(40);
		this.setH(40);
		this.setIcon(icon2);
		return this;
	}
		
	/**
	* 面向对象中第一个思想：对象自己的事情自己做
	*/
	@Override
	public void showElement(Graphics g) {
//		修改图片
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}
	public void keyClick(boolean bl,int key){//只有按下或者松开才会调用此方法
		if(bl){
			switch(key){
				case 37:this.right=false;this.down=false;this.up=false;
					this.left=true;this.fx="left";break;
				case 38:this.down=false;this.right=false;this.left=false;
					this.up=true;this.fx="up";break;
				case 39:this.left=false;this.up=false;this.down=false;
					this.right=true;this.fx="right";break;
				case 40:this.up=false;this.right=false;this.left=false;
					this.down=true;this.fx="down";break;
			  	}
		}else{
			switch(key){
				case 37:this.left=false;break;
				case 38:this.up=false;break;
				case 39:this.right=false;break;
				case 40:this.down=false;break;
			}
		}
	}
	@Override
	public void move(){
		if(this.left && this.getX()>65){
			this.setX(this.getX()-5);
		}
		if(this.up&&this.getY()>40){
			this.setY(this.getY()-5);
		}
		if(this.right&&this.getX()<1200-this.getW()-50){
			this.setX(this.getX()+5);
		}
		if(this.down&&this.getY()<900-this.getH()-20){
			this.setY(this.getY()+5);
		}
	}
	@Override
	protected void updateImage(long time){
		this.setIcon(GameLoad.imgMap.get(fx));
	}

	//  private long filetime=0;//filetime和传入时间gameTime进行比较，赋值等操作运算，控制子弹间隔
//	@Override//添加子弹
	//	protected void add(long gameTime) {//有了时间就可以进行控制
		//	if(!this.pkType){//如果不发射，就直接return
		//		return;
			//}
			//this.pkType=false;//按一次发射一个子弹
		  //new PlayFile(); 构造一个类需要做比较多的工作 可以选择一种方式，使用小工厂
		  //将构造对象的多个步骤进行封装成为一个方法，返回值直接是这个对象
		  //传递一个固定格式{X:3,y:5,f:up} json格式
		//	ElementObj obj=GameLoad.getObj("file");
	//		ElementObj element = obj.createElement(this.toString());//以后框架学习会碰到，会帮助你返回对象的实体，并初始化数据
		  //装入到集合中
	//	  ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
		  //如果要控制子弹速度等等，还需要代码编写
	//  }
	@Override
	public String toString() {
		int x=this.getX();
		int y=this.getY();
		switch(this.fx){
		case "up":break;
		case "left":break;
		case "right":break;
		case "down":break;
		}  
		return "x:"+x+",y:"+y+",f:"+this.fx;
	}
	   
}
