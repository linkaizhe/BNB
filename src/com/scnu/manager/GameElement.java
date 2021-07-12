package com.scnu.manager;

public enum GameElement {
	/**
	 * PLAY 玩家
	 * MAPS 地图
	 * ENEMY 敌人
	 * BOSS boss
	 * PLAYFILE 子弹 
	 * 等等
	 */
	MAPS,PLAY,ENEMY,BOSS,PLAYFILE,DIE;  //枚举类型的顺序是 声明的顺序
//	我们定义的枚举类型，在编译的时候，虚拟机会自动帮助生成class文件，并且会
//	加载很多的代码和方法
//	private GameElement() {
//		
//	}
//	private GameElement(int id) {
//		
//	}
	
}