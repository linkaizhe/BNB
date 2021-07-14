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

/**
 * @说明 加载器(工具：用户读取配置文件的工具)工具类，大多数提供的是static方法
 * @author linkaizhe
 *
 */
public class GameLoad {
//	得到资源管理器
	private static ElementManager em = ElementManager.getManager();
	
//	图片集合  使用map来进行存储
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

//	用户读取文件的类
	private static Properties pro = new Properties();
	/**
	 * @说明 传入地图id有加载方法根据文件规则自动产生地图文件名称、加载文件
	 * @param mapId  文件编号 文件id
	 */
	public static void MapLoad(int mapId) {
//		得到了文件路径
		String mapName= "com/tedu/text/"+mapId+".map";
//		使用io流来获取文件对象   得到类加载器
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream maps = classLoader.getResourceAsStream(mapName);
		if(maps == null) {
			System.out.println("配置文件读取异常,请重新安装");
			return;
		}
		try {
			pro.clear();
			pro.load(maps);
//			可以直接动态的获取所有的key，有key就可以获取 value
			Enumeration<?> names = pro.propertyNames();
			while(names.hasMoreElements()) {//获取是无序的
//				这样的迭代都有一个问题：一次迭代一个元素。
				String key=names.nextElement().toString();
				String [] arrs=pro.getProperty(key).split(";");
				for(int i=0;i<arrs.length;i++) {
					ElementObj obj= getObj("map");  
					ElementObj element = obj.createElement(key+","+arrs[i]);
					em.addElement(element, GameElement.MAPS);
				}
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @说明 加载图片代码
	 */
	public static void loadImg() { //可以带参数 因为不同的关卡可能需要不同的图片资源
		String imgurl = "com/tedu/text/Pro/GameData.pro"; //文件的命名可以更加有规律
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(imgurl);
//		imgMap用于存放数据
		pro.clear();
//		try {
//			pro.load(texts); //暂时注释
//			Set<Object> set = pro.keySet();//是一个set集合
//			for(Object o:set) {
//				String url = pro.getProperty(o.toString());
//				imgMap.put(o.toString(), new ImageIcon(url));
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	/**
	 * 加载玩家
	 */
	public static void loadPlay() {
		loadObj();
		String playStr="200,200,up";//没有放到配置文件中
		ElementObj obj= getObj("play");  //因为我们是依靠的字符串来读取和创建对象
//		这个字符串是key  也是 唯一 id 相当于为 每个类起啦一个唯一的id名称
//		这个字符串名称一定要和 obj.pro中的key相同
		ElementObj play = obj.createElement(playStr);
		em.addElement(play, GameElement.PLAY);
	}
	public static ElementObj getObj(String str) {
		try {
			Class<?> class1 = objMap.get(str);
			Object newInstance = class1.newInstance();
			if(newInstance instanceof ElementObj) {
				return (ElementObj)newInstance;   //这个对象就和 new Play()等价
//				如果此时新建立一个叫GamePLay的类 只需要修改配置文件
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 扩展： 使用配置文件，来实例化对象 通过固定的key(字符串来实例化)
	 * @param args
	 */
	private static Map<String,Class<?>> objMap=new HashMap<>();
	public static void loadObj() {
		String texturl= "com/tedu/text/obj.pro";//文件的命名可以更加有规律
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();//是一个set集合
			for(Object o:set) {
				String classUrl= pro.getProperty(o.toString());
//				使用反射的方式直接将类进行获取
				Class<?> forName = Class.forName(classUrl);
				objMap.put(o.toString(), forName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}