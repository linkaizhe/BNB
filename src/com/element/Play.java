package com.element;

import java.awt.Graphics;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import com.element.ElementObj;
import com.game.GameStart;
import com.manager.ElementManager;
import com.manager.GameElement;
import com.manager.GameLoad;

public class Play extends ElementObj{

	public final static int ASPEED = 5;
	private boolean left=false; //左
	private boolean up=false;//上
	private boolean right=false;//右
	private boolean down=false;//下
	protected int speed=ASPEED;//移动速度
	protected int changeDirectionCount=0;
	protected boolean Ischange=false;
		
		
//	图片集合 使用map进行存储
	private Map<String,ImageIcon> imgMap;
//	变量专门用来记录当前主角面向的方向，默认为up
	private boolean pkType= false;//放置泡泡状态true false不放置
	private String fx="down";
	
	private int lastX;
	private int lastY;
	private String refuseMove;
	
	public Play(){}
	public Play(int x, int y, int w, int h, ImageIcon icon) {
		super(x, y, w, h, icon);	
	}

	@Override
		public ElementObj createElement(String str) {
			String [] split=str.split(",");
			this.setX(Integer.parseInt(split[0]));
			this.setY(Integer.parseInt(split[1]));
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
//	修改图片
	g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}
	public void keyClick(boolean bl,int key){//只有按下或者松开才会调用此方法
	if(bl){
		switch(key){
			case 32:this.pkType=true; break;
			case 65:this.right=false;this.down=false;this.up=false;
				this.left=true;this.fx="left";break;
			case 87:this.down=false;this.right=false;this.left=false;
				this.up=true;this.fx="up";break;
			case 68:this.left=false;this.up=false;this.down=false;
				this.right=true;this.fx="right";break;
			case 83:this.up=false;this.right=false;this.left=false;
				this.down=true;this.fx="down";break;
			}
		}else{
			switch(key){
			case 32:this.pkType=false;break;
			case 65:this.left=false;break;
			case 87:this.up=false;break;
			case 68:this.right=false;break;
			case 83:this.down=false;break;
			}
		}
		if(refuseMove==fx) {
			this.left=false;
			this.up=false;
			this.right=false;
			this.down=false;
		}else {
			refuseMove="";
		}
	}
	@Override
	public void move(){
		lastX=this.getX();
		lastY=this.getY();
		if(this.left && this.getX()>0){
			this.setX(this.getX()-speed);
		}
		if(this.up&&this.getY()>0){
			this.setY(this.getY()-speed);
		}
		if(this.right&&this.getX()<1200-this.getW()-50){
			this.setX(this.getX()+speed);
		}
		if(this.down&&this.getY()<900-this.getH()-70){
			this.setY(this.getY()+speed);
		}
	}
	@Override
	protected void updateImage(long time){
	  this.setIcon(GameLoad.imgMap.get(fx));
	}

//	private long filetime=0;//filetime和传入时间gameTime进行比较，赋值等操作运算，控制子弹间隔
	@Override
	protected void add(long gameTime) {//有了时间就可以进行控制
		if(!this.pkType) {//如果是不发射状态 就直接return
			return;
		}
		this.pkType=false;//按一次，发射一个子弹。拼手速(也可以增加变量来控制)
		//当构造一个类时需要做比较多的工作 可以选择一种方法：使用小工厂
		//将构造对象的多个步骤进行封装成为一个方法
		//传递一个固定格式 {x:3,y:5}
		ElementObj element = new Bubble(gameTime).createElement(this.toString());//返回对象实体 初始化数据
		//装入到集合中
		ElementManager.getManager().addElement(element, GameElement.BUBBLE);
		//如果要控制子弹速度等等。。。还需要代码编写
	}
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
	
	@Override
	public void moveback() {
		this.setX(lastX);
		this.setY(lastY);
		refuseMove=fx;
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
