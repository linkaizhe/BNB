package com.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.element.ElementObj;
import com.manager.ElementManager;
import com.manager.GameElement;
/**
 * @说明 监听类:用于监听用户的操作 KeyListener
 * @author linkaizhe
 */
public class GameListener implements KeyListener{
	private ElementManager em = ElementManager.getManager();
	private static boolean gamePlaying = false; //游戏运行
	private static boolean twoPlayer; //双人模式
	/*
	 * 能否通过一个集合来记录所有按下的键，如果重复触发，就直接结束
	 * 同时，第一次按下，记录到集合中，第二次判定集合中是否有
	 * 		松开就直接删除集合中的记录
	 * set集合
	 * Set<>中不能使用Int，必须使用对象，Java中的隐式转化可以将Integer转换成Int
	 */
	private Set<Integer> set = new HashSet<Integer>(); 
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	/**
	 * 按下   左37 上38 右39 下40
	 * 实现主角的移动
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// 拿到玩家集合
//		System.out.println("按下"+e.getKeyCode()); //寻找各个按键的对应值
		int key = e.getKeyCode();
		if(set.contains(key)) { //判定集合中是否已经存在，包含的这个对象
			return; // 如果包含则直接结束这个方法
		}
		set.add(key);
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
		for(ElementObj obj:play) {
			obj.keyClick(true, e.getKeyCode());
		}
		List<ElementObj> npc = em.getElementsByKey(GameElement.NPC);
		for(ElementObj obj1:npc) {
			obj1.keyClick(true, e.getKeyCode());
		}
	}
	/**
	 * 松开
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(!set.contains(e.getKeyCode())) { // 如果这个不存在，就停止
			return;
		} // 存在(已经按过这个按键)
		set.remove(e.getKeyCode()); //移除数据
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
		for(ElementObj obj:play) {
			obj.keyClick(false, e.getKeyCode());
		}
		List<ElementObj> npc = em.getElementsByKey(GameElement.NPC);
		for(ElementObj obj1:npc) {
			obj1.keyClick(false, e.getKeyCode());
		}
	}
	public static boolean isGamePlaying() {
		return gamePlaying;
	}
	public static void setGamePlaying(boolean gamePlaying) {
		GameListener.gamePlaying = gamePlaying;
	}
	public static boolean isTwoPlayer() {
		return twoPlayer;
	}
	public static void setTwoPlayer(boolean twoPlayer) {
		GameListener.twoPlayer = twoPlayer;
	}

}