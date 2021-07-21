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
 * @继承 使用继承的方式实现多线程(一般建议使用接口实现)
 */
public class GameThread extends Thread{
	private ElementManager em;
	public GameThread() {
		em = ElementManager.getManager();
	}
	@Override
	public void run() { // 游戏的run方法  主线程
		while(true) { // 扩展，可以将true变为一个变量用于控制结束
//		游戏开始前  读进度条，加载游戏资源(或场景资源)
			gameLoad();
//		游戏进行时  游戏过程中
			gameRun();
//		游戏当前场景结束  游戏资源回收(场景资源)
			gameOver();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 游戏的加载
	 */
	private void gameLoad() {
		GameLoad.loadImg();
		GameLoad.MapLoad(1); // 加载地图 可以为变量 随机地图
//		加载主角
		GameLoad.loadPlay(); //可以带参数 单人或者多人
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
	 * 首先实现主角的移动
	 */
	private void gameRun() {
		long gameTime = 0L; //int类型也可以
		while(true) { // 预留扩展 true可以变为变量，用于控制关卡结束等
			Map<GameElement, List<ElementObj>> all = em.getGameElements();
			List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
			List<ElementObj> maps = em.getElementsByKey(GameElement.MAPS);
			List<ElementObj> npc = em.getElementsByKey(GameElement.NPC);
			moveAndUpdate(all, gameTime); //游戏元素自动化方法
			ElementPK(play, npc);
			ElementPK(maps, play);
			
			gameTime++;//唯一的时间控制
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void ElementPK(List<ElementObj> listA, List<ElementObj> listB) {
//		使用双循环进行一对一判定，如果为真，就设置2个对象的死亡状态
		for(int i=0;i<listA.size();i++) {
			ElementObj obj1 = listA.get(i);
			for(int j=0;j<listB.size();j++) {
				ElementObj obj2 = listB.get(j);
				if(obj1.pk(obj2)) {
//					问题：BOSS无法一枪一个
//					将setLive(false)变为一个受攻击方法，还可以传入另外一个对象的攻击力
//					当受攻击方法执行时，如果血量减为0再进行设置生存为false
//					扩展  需要完成
//					obj1.setLive(false);
//					obj2.setLive(false);
					break;
				}
			}
		}
	}
	
//	游戏元素自动化方法
	public void moveAndUpdate(Map<GameElement, List<ElementObj>> all, long gameTime) {
//		GameElement.values(); //隐藏方法 返回值是一个数组，数组的顺序就是定义枚举的顺序
		for(GameElement ge:GameElement.values()) { //迭代器  适用于数据不改变的情况
			List<ElementObj> list = all.get(ge);
//			编写这样直接操作集合数据的代码建议不要使用迭代器。
			for(int i=list.size()-1;i>=0;i--) {
				ElementObj obj = list.get(i);//读取为基类
				if(!obj.isLive()) { //如果死亡
//					启动一个死亡方法(方法中可以做很多事情 例如：死亡动画、装备掉落等等)
					obj.die(); //需要补充
					list.remove(i);
					continue;
				}
				obj.model(gameTime); // 调用模板方法
			}	
		}
	}
	/**
	 * 游戏切换关卡
	 */
	private void gameOver() {
	}

}

