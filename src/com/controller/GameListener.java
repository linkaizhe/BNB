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
	private static boolean twoPlayer;
	private Set<Integer> set = new HashSet<Integer>(); 
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(set.contains(key)) { 
			return; 
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
		if(!set.contains(e.getKeyCode())) { 
			return;
		} 
		set.remove(e.getKeyCode()); 
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