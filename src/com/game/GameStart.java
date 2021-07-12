package com.game;

import com.controller.GameListener;
import com.controller.GameThread;
import com.show.GameJFrame;
import com.show.GameMainJPanel;

public class GameStart {
	/**
	 * 程序的唯一入口
	 * @param args
	 */
	public static void main(String[] args) {
		GameJFrame gj=new GameJFrame();
		/**
		 * 实例化面板，注入到JFrame中
		 */
		GameMainJPanel jp=new GameMainJPanel();	
//		实例化监听
		GameListener listener=new GameListener();
//		实例化主线程
		GameThread th=new GameThread();
//		注入
		gj.setjPanel(jp);
		gj.setKeyListener(listener);
		gj.setThead(th);
		gj.start();
		
	}

}

/**
 * 1.分析游戏，设计游戏的 配置文件格式，文件读取格式（load格式）
 * 2.设计游戏角色，分析游戏需求(抽象基于基类的继承)
 * 3.开发pojo类(Vo)....
 * 4.需要的方法就在父类中重写(如果父类不支持，可以采用修改父类)
 * 5.检查配置，完成对象的 load和add到Manage.
 * 6.碰撞等等细节代码。
 * 
 *  web网页游戏
 */
