package com.show;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.game.GameStart;

public class EndJPanel extends JPanel{
	private ImageIcon img;
	private ImageIcon imgrestart;
	private int width;
	private int height;
	
	public EndJPanel() {
		img = new ImageIcon("image/Frame/Gameovers.png");
		imgrestart = new ImageIcon("image/Frame/restartgame.png");
		this.width = 1200;
		this.height = 900;
		init();
	}
	private void init() {
		
		this.setSize(width, height);
		this.setLayout(null);
		
		JLabel jLabel = new JLabel(img);
		img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jLabel.setBounds(0, 0, width, height);
		jLabel.setIcon(img);
		
		//重新开始按钮
		JButton restart = new JButton();
		restart.setIcon(imgrestart);
		restart.setBounds(510, 440, 180, 60);
		restart.setBorderPainted(false);
		restart.setFocusPainted(false);
		restart.setContentAreaFilled(false);
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameStart.changePanel("begin");
			}
			
		});
		
		this.add(restart);
		this.add(jLabel);
		
		this.setVisible(true);
		this.setOpaque(true);
	}
		

}
