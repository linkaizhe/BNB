package com.show;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.game.GameStart;

public class BeginJPanel extends JPanel{
	private ImageIcon img;
	private ImageIcon imgone;
	private ImageIcon imgtwo;
	private ImageIcon imgtro;
	private int width;
	private int height;

	public BeginJPanel() {
		img = new ImageIcon("image/Frame/GameBegin.png");
		imgone = new ImageIcon("image/Frame/oneplayer.png");
		imgtwo = new ImageIcon("image/Frame/twoplayer.png");
		imgtro = new ImageIcon("image/Frame/gameintroduce.png");
		this.width = 1200;
		this.height = 900;
		init();
	}
	private void init() {
		
		this.setSize(width, height);
		this.setLayout(null); //清空布局
		
		JLabel jLabel = new JLabel(img);
		img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jLabel.setBounds(0, 0, width, height);
		jLabel.setIcon(img);
		
//		单人模式按钮
		JButton onePlayer = new JButton();
		onePlayer.setIcon(imgone); //设置图片
		onePlayer.setBounds(510, 300, 180, 60);
		onePlayer.setBorderPainted(false); //移除悬浮特效
		onePlayer.setFocusPainted(false); //移除焦点
		onePlayer.setContentAreaFilled(false); //背景透明
		onePlayer.addActionListener(new ActionListener() { //单击监听
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				GameListener.setTwoPlayer(false);
				GameStart.startNewGame();
			}
		});
		
//		双人模式按钮
		JButton twoPlayer = new JButton();
		twoPlayer.setIcon(imgtwo);
		twoPlayer.setBounds(510, 450, 180, 60);
		twoPlayer.setBorderPainted(false);
		twoPlayer.setFocusPainted(false);
		twoPlayer.setContentAreaFilled(false);
		twoPlayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameStart.toEndJPanel(); //测试：跳转至游戏结束页面
			}
		});
		
//		游戏说明按钮
		JButton gameIntroduce = new JButton();
		gameIntroduce.setIcon(imgtro);
		gameIntroduce.setBounds(510, 600, 180, 60);
		gameIntroduce.setBorderPainted(false);
		gameIntroduce.setFocusPainted(false);
		gameIntroduce.setContentAreaFilled(false);
		gameIntroduce.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameStart.toIntroduce();
			}
		});
		
		this.add(onePlayer);
		this.add(twoPlayer);
		this.add(gameIntroduce);
		this.add(jLabel);
		this.setVisible(true);
		this.setOpaque(true);
	}

}
