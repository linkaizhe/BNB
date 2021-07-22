package com.show;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.element.ElementObj;
import com.manager.ElementManager;
import com.manager.GameElement;

/**
 * @说明 游戏的主要面板
 * @author linkaizhe
 * @功能说明 主要进行元素的显示，同时进行界面的刷新(多线程)
 * 
 * @多线程刷新 1.本类实现线程接口
 * 			 2.本类中定义一个内部类来实现
 */
public class GameMainJPanel extends JPanel implements Runnable{

	private ElementManager em;
	public GameMainJPanel() {
		init();
	}

	public void init() {
		em = ElementManager.getManager();
		
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Map<GameElement, List<ElementObj>> all = em.getGameElements();
		for(GameElement ge:GameElement.values()) {
			List<ElementObj> list = all.get(ge);
			for(int i=0;i<list.size();i++) {
				ElementObj obj=list.get(i);
				obj.showElement(g);
			}
		}
	}
	@Override
	public void run() { 
		while(true) {
			this.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
	}
	
}