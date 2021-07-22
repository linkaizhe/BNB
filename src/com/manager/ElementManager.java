package com.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.element.ElementObj;
/**
 * @说明 本类是元素管理器，专门存储所有的元素，同时，提供方法给予视图和控制获取数据
 * @author linkaizhe
 */
public class ElementManager {

	private Map<GameElement,List<ElementObj>> gameElements;	
	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}
	public void addElement(ElementObj obj,GameElement ge) {
		if(ge.toString()=="PLAY" && !gameElements.get(ge).isEmpty()) return;
		if(ge.toString()=="NPC" && !gameElements.get(ge).isEmpty()) return;
		gameElements.get(ge).add(obj);
	}
	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}	
	
	private static ElementManager EM=null;

	public static synchronized ElementManager getManager() {
		if(EM == null) {// 空值判定
			EM = new ElementManager(); 
		}
		return EM;
	}
	private ElementManager() {
		init(); 
	}
	
	public void init() {
		gameElements = new HashMap<GameElement, List<ElementObj>>();
		for(GameElement ge:GameElement.values()) { 
			gameElements.put(ge, new ArrayList<ElementObj>());
		}
	}
	
}
