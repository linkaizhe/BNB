package com.controller;

import java.util.List;
import java.util.Map;

import com.element.ElementObj;
import com.manager.ElementManager;
import com.manager.GameElement;
import com.manager.GameLoad;
/**
 * @说明 游戏的主线程：用于控制游戏加载、游戏关卡、游戏运行时自动化、
 * 	   游戏判定等等；游戏地图切换、资源释放、重新读取等等
 * @author linkaizhe
 */
public class GameThread extends Thread{
	private ElementManager em;
	public GameThread() {
		em = ElementManager.getManager();
	}
	@Override
	public void run() { 
		while(true) { 
//		游戏开始前  读进度条，加载游戏资源(或场景资源)
			gameLoad();
//		游戏进行时  游戏过程中
			gameRun();
//		游戏当前场景结束  游戏资源回收(场景资源)
			gameOver();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 游戏的加载
	 */
	private void gameLoad() {
		GameLoad.loadImg();
		GameLoad.MapLoad(1); 
//		加载主角
		GameLoad.loadPlay(); 
//		加载敌人NPC等
		GameLoad.loadNpc();
//		全部加载完成 游戏启动
//		load();
	}
	/**
	 * @说明 游戏进行时
	 * @任务说明 游戏过程中需要做的事情：1.自动化玩家的移动、碰撞、死亡等等
	 *                           2.新元素的增加(NPC死亡后出现的道具)
	 *                           3.游戏暂停等等
	 */
	private void gameRun() {
		long gameTime = 0L; 
		while(true) {
			Map<GameElement, List<ElementObj>> all = em.getGameElements();
			List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
			List<ElementObj> maps = em.getElementsByKey(GameElement.MAPS);
			List<ElementObj> npc = em.getElementsByKey(GameElement.NPC);
			List<ElementObj> bubbledie = em.getElementsByKey(GameElement.DIE);
			List<ElementObj> bubbles = em.getElementsByKey(GameElement.BUBBLE);
			List<ElementObj> things = em.getElementsByKey(GameElement.THINGS);
			for(int i=0;i<maps.size();i++) {
				ElementObj wall = maps.get(i);
				for(int j=0;j<bubbledie.size();j++) {
					ElementObj Bubdie = bubbledie.get(j);
					if(wall.pk(Bubdie)&&(wall.isdestroy())) {
						Bubdie.setLive(false);
						wall.setLive(false);
						break;
					}
				}
			}
			
			for(int i=0;i<bubbles.size();i++) {
				ElementObj bub = bubbles.get(i);
				for(int j=0;j<bubbledie.size();j++) {
					ElementObj Bubdie = bubbledie.get(j);
					if(bub.pk(Bubdie)&&(bub.isdestroy())) {
						Bubdie.setLive(false);
						bub.setLive(false);
						break;
					}
				}
			}
			
			getthings(play,things);
			getthings(npc,things);
			
			playdie(bubbledie,play);
			playdie(bubbledie,npc);
			
			moveAndUpdate(all, gameTime); //游戏元素自动化方法
			if(ElementPK(play, maps) || ElementPK(play, npc)) {
				play.get(0).moveback();
			}
			if(ElementPK(npc, maps)) {
				npc.get(0).moveback();
			}
			
			gameTime++;//唯一的时间控制
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getthings(List<ElementObj> play, List<ElementObj> things)
	{
	for(int i=0;i<play.size();i++) {
		ElementObj player = play.get(i);
		for(int j=0;j<things.size();j++) {
			ElementObj thing = things.get(j);
			if(player.pk(thing)) {
				thing.setLive(false);
				switch(thing.thingtype()){
				case 4:player.changeDirection(5);break;
				case 3:player.deletechange();break;
				default:break;
				}
				break;
			}
		}
	}
	}
	
	public void playdie(List<ElementObj> bubbledie, List<ElementObj> play){
	for(int i=0;i<bubbledie.size();i++) {
		ElementObj die = bubbledie.get(i);
		for(int j=0;j<play.size();j++) {
			ElementObj playe = play.get(j);
			if(die.pk(playe)) {
				playe.setLive(false);
				die.setLive(false);
				break;
			}
		}
	}
	}
	
	public boolean ElementPK(List<ElementObj> listA, List<ElementObj> listB) {
		for(int i=0;i<listA.size();i++) {
			ElementObj obj1 = listA.get(i);
			for(int j=0;j<listB.size();j++) {
				ElementObj obj2 = listB.get(j);
				if(obj1.pk(obj2)) {
					obj1.moveback();
					return true;
				}
			}
		}
		return false;
	}
	
	public void moveAndUpdate(Map<GameElement, List<ElementObj>> all, long gameTime) {
		for(GameElement ge:GameElement.values()) { 
			List<ElementObj> list = all.get(ge);
			for(int i=list.size()-1;i>=0;i--) {
				ElementObj obj = list.get(i);
				if(!obj.isLive()) { 
					obj.die(); 
					list.remove(i);
					continue;
				}
				obj.model(gameTime);
			}	
		}
	}
	/**
	 * 游戏切换关卡
	 */
	private void gameOver() {
	}

}

