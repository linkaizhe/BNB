package com.show;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.game.GameStart;

public class IntroJPanel extends JPanel{
	private ImageIcon imgdaoju;
	private ImageIcon imggame;
	private ImageIcon imgup;
	private ImageIcon imgdown;
	private ImageIcon imgback;
	private int width;
	private int height;
	
	public IntroJPanel() {
		imgdaoju = new ImageIcon("image/Frame/daoju.png");
		imggame = new ImageIcon("image/Frame/gamerule.png");
		imgup = new ImageIcon("image/Frame/shang.png");
		imgdown = new ImageIcon("image/Frame/xia.png");
		imgback = new ImageIcon("image/Frame/back.png");
		this.width = 1200;
		this.height = 900;
		init();
	}
	private void init() {
		this.setSize(width, height);
		this.setLayout(null);
		
		JLabel daojuJL = new JLabel(imgdaoju);
		imgdaoju.setImage(imgdaoju.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		daojuJL.setBounds(0, 0, width, height);
		daojuJL.setIcon(imgdaoju);
		
		JLabel gameJL = new JLabel(imggame);
		imggame.setImage(imggame.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		gameJL.setBounds(0, 0, width, height);
		gameJL.setIcon(imggame);
		gameJL.setVisible(false);
		
		JButton shangye = new JButton();
		shangye.setIcon(imgup);
		shangye.setBounds(870, 810, 37, 37);
		shangye.setBorderPainted(false);
		shangye.setFocusPainted(false);
		shangye.setContentAreaFilled(false);
		shangye.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				daojuJL.setVisible(true);
				gameJL.setVisible(false);
			}
		});
		
		JButton xiaye = new JButton();
		xiaye.setIcon(imgdown);
		xiaye.setBounds(940, 810, 37, 37);
		xiaye.setBorderPainted(false);
		xiaye.setFocusPainted(false);
		xiaye.setContentAreaFilled(false);
		xiaye.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				daojuJL.setVisible(false);
				gameJL.setVisible(true);
			}
		});
		
		JButton back = new JButton();
		back.setIcon(imgback);
		back.setBounds(1100, 35, 53, 37);
		back.setBorderPainted(false);
		back.setFocusPainted(false);
		back.setContentAreaFilled(false);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameStart.changePanel("begin");
			}
		});
		
		this.add(shangye);
		this.add(xiaye);
		this.add(back);
		this.add(daojuJL);
		this.add(gameJL);
		
		this.setVisible(true);
		this.setOpaque(true);

	}
}
