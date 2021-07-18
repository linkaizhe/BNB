package com.controller;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * @说明：加载BGM
 *
 */
public class BackgroundMusic extends Thread{
	
	 public static void AutoplayMusic(String locate) {
		 try {
			 File Path = new File(locate);
			 if(Path.exists()) {
				 
				 AudioInputStream audioInput = AudioSystem.getAudioInputStream(Path);
				 Clip bgm = AudioSystem.getClip();
				 bgm.open(audioInput);
				 bgm.start();
				 bgm.loop(Clip.LOOP_CONTINUOUSLY);//循环播放
			 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
}
