package com.show;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.manager.GameLoad;

public class BeginJPanel extends JPanel{
	private ImageIcon img;
	private int width;
	private int height;

	public BeginJPanel() {
		img = new ImageIcon("image/Frame/GameBegin.png"); //
		this.width = 1200;
		this.height = 900;
		init();
	}
	private void init() {
		
		this.setSize(width,height);
		this.setLayout(null);
		
		JLabel jLabel = new JLabel(img);
		img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jLabel.setBounds(0, 0, width, height);
		jLabel.setIcon(img);
		
		this.add(jLabel);
		this.setVisible(true);
		this.setOpaque(true);
	}

}
