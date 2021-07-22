package com.element;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * @说明 所有元素的基类。
 * @author linkaizhe
 *
 */
public abstract class ElementObj {
	private int x;
	private int y;
	private int w;
	private int h;
	private ImageIcon icon;
	private boolean live = true; 
	protected boolean Destroy=false;
	public ElementObj() {
	}
	/**
	 * @说明 带参数的构造方法，可以由子类传输数据到父类
	 * @param x  左上角x坐标
	 * @param y  左上角y坐标
	 * @param w     w宽度
	 * @param h     h高度
	 * @param icon  图片
	 */
	public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.icon = icon;
	}
	
	/**
	 * @说明 抽象方法，显示元素
	 * @param g 画笔 用于进行绘画
	 */
	
	public abstract void showElement(Graphics g);
	
	public void keyClick(boolean bl,int key) { 
		
	}

	protected void move() {}

	public final void model(long gameTime) {
//		先换装
		updateImage(gameTime);
//		再移动
		move();
//		再发射子弹
		add(gameTime);
	}
	protected void updateImage(long time) {}
	protected void add(long gameTime) {}
//	死亡方法
	public void die() { 
		
	}
	public ElementObj createElement(String str) {
		return null;
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, w, h);
	}
	/**
	 * @说明 碰撞方法
	 * 一个是this对象  一个是传入值obj
	 * @param obj
	 * @return boolean 返回true说明有碰撞  返回false说明没有碰撞
	 */
	public boolean pk(ElementObj obj) {
		return this.getRectangle().intersects(obj.getRectangle());
	}
	/**
	 * @说明 碰撞后返回方法
	 */
	public void moveback() {}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean isdestroy(){
		return Destroy;
	}
	public int thingtype() {
		return 0;
	}
	public void changeDirection(int lasttime) {
		
	}
	public void deletechange() {
		
	}
	
}
