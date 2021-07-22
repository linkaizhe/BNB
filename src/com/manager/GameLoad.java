package com.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;

import com.element.ElementObj;
import com.element.Maps;
import com.element.Floor;

/**
 * @说明 加载器(工具：用户读取配置文件的工具)工具类，大多数提供的是static方法
 * @author linkaizhe
 *
 */
public class GameLoad {

	private static ElementManager em = ElementManager.getManager();
	public static Map<String,ImageIcon> imgMap = new HashMap<>();
	public static Map<String, List<String>> gameBasicMap = new HashMap<>();
	
//	单例模式
	private static GameLoad gameLoad = null;
	public static GameLoad getGameLoad() {
		if(gameLoad == null) {//空值判定
			gameLoad = new GameLoad();
		}
		return gameLoad;
	}

	private static Properties pro = new Properties();
	/**
	 * @说明 传入地图id有加载方法根据文件规则自动产生地图文件名称、加载文件
	 * @param mapId  文件编号 文件id
	 */
	public static void MapLoad(int mapId) {
		String mapName1= "com/text/Map/floor.map";
		ClassLoader classLoader1 = GameLoad.class.getClassLoader();
		InputStream maps1 = classLoader1.getResourceAsStream(mapName1);
		try {
			pro.clear();
			pro.load(maps1);
			Enumeration<?> names1 = pro.propertyNames();
			while(names1.hasMoreElements()) {
				String key = names1.nextElement().toString();
				pro.getProperty(key);
				
				String [] arrs1=pro.getProperty(key).split(";");
				for(int i=0;i<arrs1.length;i++) {
					ElementObj element2 = new Floor().createElement(key+","+arrs1[i]);
					em.addElement(element2, GameElement.FLOOR);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String mapName= "com/text/Map/"+mapId+".map";
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream maps = classLoader.getResourceAsStream(mapName);
		if(maps == null) {
			System.out.println("配置文件读取异常,请重新安装");
			return;
		}
		try {
			pro.clear();
			pro.load(maps);
			Enumeration<?> names = pro.propertyNames();
			while(names.hasMoreElements()) {
				String key = names.nextElement().toString();
				pro.getProperty(key);
				
				String [] arrs=pro.getProperty(key).split(";");
				for(int i=0;i<arrs.length;i++) {
					ElementObj element = new Maps().createElement(key+","+arrs[i]);
					em.addElement(element, GameElement.MAPS);
					
				}
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @说明 加载图片代码
	 */
	public static void loadImg() { 
		String imgurl = "com/text/Pro/GameData.pro"; 
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(imgurl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();
			for(Object o:set) {
				String url = pro.getProperty(o.toString());
				imgMap.put(o.toString(), new ImageIcon(url));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * 加载玩家
	 */
	public static void loadPlay() {
		loadObj();
		String playStr="75,75,up";
		ElementObj obj= getObj("play"); 
		ElementObj play = obj.createElement(playStr);
		em.addElement(play, GameElement.PLAY);
	}
	/** 
	 * 加载NPC
	 */
	public static void loadNpc() {
		loadObj();
		String npcStr = "677,675,down";
		ElementObj obj = getObj("npc");
		ElementObj npc = obj.createElement(npcStr);
		em.addElement(npc, GameElement.NPC);
	}
	public static ElementObj getObj(String str) {
		try {
			Class<?> class1 = objMap.get(str);
			Object newInstance = class1.newInstance();
			if(newInstance instanceof ElementObj) {
				return (ElementObj)newInstance;  
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Map<String,Class<?>> objMap=new HashMap<>();
	public static void loadObj() {
		String texturl= "com/text/Pro/obj.pro";
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();
			for(Object o:set) {
				String classUrl= pro.getProperty(o.toString());
				Class<?> forName = Class.forName(classUrl);
				objMap.put(o.toString(), forName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
}