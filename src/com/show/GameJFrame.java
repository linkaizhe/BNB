package com.show;

import java.awt.CardLayout;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @说明 游戏窗体 主要实现的功能：关闭、显示、最大最小化
 * @author linkaizhe
 * @功能说明：
 * 		需要嵌入面板，启动主线程等等
 * @窗体说明 swing awt 窗体大小(记录用户上次使用软件的窗体样式)
 * 
 * @分析 1.面板绑定到窗体
 * 		2.监听绑定
 * 		3.游戏主线程启动
 * 		4.显示窗体
 */
public class GameJFrame extends JFrame{
	public static int GameX = 1200;
	public static int GameY = 900;
	private JPanel mainJPanel; //显示的界面
	private BeginJPanel beginJPanel;//游戏开始前的界面
	private GameMainJPanel gameJPanel;//游戏主要界面
	private EndJPanel endJPanel; //游戏结束界面
	private IntroJPanel introJPanel; //游戏说明界面
	private KeyListener  keyListener;//键盘监听
	private Thread thead = null;  //游戏主线程
	private CardLayout layout;//布局
	
	public GameJFrame() {
		init();
	}
//	初始化
	public void init() {
		this.setTitle("泡泡堂");
		this.setSize(GameX, GameY); //设置窗体大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置退出并且关闭进程
		this.setResizable(false);//设置窗体大小不可改变(暂定)
		this.setLocationRelativeTo(null);//设置窗体屏幕居中显示
		
		this.mainJPanel = new JPanel();
		this.setmainJPanel(mainJPanel);
		
		this.layout = new CardLayout();
		this.mainJPanel.setLayout(layout);
		
		this.beginJPanel = new BeginJPanel();
		this.mainJPanel.add("begin", beginJPanel);
		
		this.endJPanel = new EndJPanel();
		this.mainJPanel.add("end", endJPanel);
		
		this.introJPanel = new IntroJPanel();
		this.mainJPanel.add("intro", introJPanel);
		
		this.layout.show(mainJPanel, "begin");
		this.setVisible(true);//显示界面
	}
	/**
	 * 改变当前界面
	 */
	public void changePanel(String name) {
		layout.show(mainJPanel, name);
	}
	/**
	 * 游戏的启动方法
	 */
	public void startGame() {
		gameJPanel = new GameMainJPanel();//新建界面
		mainJPanel.add("game", gameJPanel);
		if(mainJPanel!=null) {
			this.add(mainJPanel);
		}
		if(keyListener !=null) {
			this.addKeyListener(keyListener);
		}
		if(thead !=null) {
			thead.start();//启动线程
		}
		if(this.gameJPanel instanceof Runnable) {
			new Thread((Runnable)this.gameJPanel).start();
		}
		
	}
	public void setmainJPanel(JPanel jPanel) {
		this.mainJPanel = jPanel;
	}
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
	public void setThead(Thread thead) {
		this.thead = thead;
	}
	public void setBeginJPanel(BeginJPanel beginJPanel) {
		this.beginJPanel = beginJPanel;
	}
	public void setEndJPanel(EndJPanel endJPanel) {
		this.endJPanel = endJPanel;
	}
	public void setIntroJPanel(IntroJPanel introJPanel) {
		this.introJPanel = introJPanel;
	}
	public void stopThead() {
		this.thead.stop();
	}
}