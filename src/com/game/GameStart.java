package com.game;

import com.controller.BackgroundMusic;
import com.controller.GameListener;
import com.controller.GameThread;
import com.show.GameJFrame;

public class GameStart {
	private static GameJFrame gameFrame;
	/**
	 * 程序的唯一入口
	 * @param args
	 */
	public static void main(String[] args) {
		
		/**
		 * 实例化面板，注入到JFrame中
		 */
//		实例化监听
		GameListener listener = new GameListener();
//		实例化主线程
		GameThread th=new GameThread();

		gameFrame = new GameJFrame();
//		注入
		gameFrame.setKeyListener(listener);
		gameFrame.setVisible(true);
		gameFrame.setThead(th);
		gameFrame.startGame();
		
		//加载BGM
		BackgroundMusic.AutoplayMusic("music/bgm.wav");
		
	}
	/**
	 * 切换界面
	 */
	public static void changePanel(String panelName) {
		if(panelName == "game") {
			GameListener.setGamePlaying(true);
		}
		else {
			GameListener.setGamePlaying(false);
		}
		gameFrame.changePanel(panelName);
		//刷新
		gameFrame.setVisible(false);
		gameFrame.setVisible(true);
	}
	public static void startNewGame() {
		GameListener.setGamePlaying(true);
		gameFrame.stopThead();
		gameFrame.setThead(new GameThread());
		gameFrame.startGame();
		changePanel("game");
	}
	public static void toEndJPanel() { //测试代码 后续删除
		gameFrame.setThead(new GameThread());
		changePanel("end");
	}
	public static void toIntroduce() {
		gameFrame.setThead(new GameThread());
		changePanel("intro");
	}
}
