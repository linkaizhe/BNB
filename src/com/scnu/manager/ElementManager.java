package com.scnu.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scnu.element.ElementObj;
/**
 * @说明 本类是元素管理器，专门存储所有的元素，同时，提供方法
 * 	       给予视图和控制获取数据
 * @author linkaizhe
 * @问题一：存储所有元素数据，怎么存放？list map set 3大集合
 * @问题二：管理器是视图和控制要访问，管理器就必须只有一个，单例模式
 */
public class ElementManager {
	/*
	 * String 作为key 匹配所有的元素  play -> List<Object> listPlay
	 *                             enemy ->List<Object> listEnemy 
	 * 枚举类型，当做map的key用来区分不一样的资源，用于获取资源
	 * List中元素的泛型应该是元素基类
	 * 所有元素都可以存放到 map集合中，显示模块只需要获取到这个map就可以
	 * 显示是有的界面需要显示的元素(调用元素基类的 showElement())
	 */
	private Map<GameElement,List<ElementObj>> gameElements;	
	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}
//	添加元素(多半由加载器调用)
	public void addElement(ElementObj obj,GameElement ge) {
		gameElements.get(ge).add(obj);//添加对象到集合中，按key值就行存储
	}
//	依据key返回 list集合，取出某一类元素
	public List<ElementObj> getElementsByKey(GameElement ge){
//		当一块内存没有任何一个引用指向的时候，会被GC回收。
		return gameElements.get(ge);
	}	
	/**
	 * 单例模式：内存中有且只有一个实例。
	 * 饿汉模式-是启动就自动加载实例
	 * 饱汉模式-是需要使用的时候才加载实例
	 * 
	 * 编写方式：
	 * 1.需要一个静态的属性(定义要给常量) 单例的引用
	 * 2.提供一个静态的方法(返回这个实例) return单例的引用
	 * 3.一般为防止其他人自己使用(类是可以实例化)，所以会私有化构造方法
	 *    ElementManager em = new ElementManager();
	 */
	private static ElementManager EM=null; //引用
//	synchronized线程锁 -> 保证本方法执行中只有一个线程
	public static synchronized ElementManager getManager() {
		if(EM == null) {// 空值判定
			EM = new ElementManager(); 
		}
		return EM;
	}
	private ElementManager() {//私有化构造方法
		init(); //实例化方法
	}
	/**
	 * 本方法是为 将来可能出现的功能扩展，重写init方法准备的。
	 */
	public void init() {//实例化在这里完成
		gameElements = new HashMap<GameElement, List<ElementObj>>();
//		枚举自动生成
		for(GameElement ge:GameElement.values()) { // 通过循环读取添加枚举类型的方式添加集合
			gameElements.put(ge, new ArrayList<ElementObj>());
		}
//		道具、子弹、爆炸效果、死亡效果等等
	}
	
}
