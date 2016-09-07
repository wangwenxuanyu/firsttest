package com.example.tgame;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;

public class ParticleSet {
	ArrayList<Particle> particals;

	public ParticleSet() {
		super();
		particals = new ArrayList<Particle>();
	}

	/***
	 * 获取相关颜色
	 * 
	 * @return
	 */
	public int getColor(int i) {
		int color = Color.RED;
		switch (i % 4) {
		case 0:
			color = Color.RED;
			break;
		case 1:
			color = Color.GREEN;
			break;
		case 2:
			color = Color.YELLOW;
			break;
		case 3:
			color = Color.WHITE;
			break;
		default:
			break;
		}
		return color;
	}

	/***
	 * 添加粒子
	 * 
	 * @param count
	 *            个数
	 * @param startTime
	 *            起始时间
	 */
	public void addPartical(int count, double startTime, int window_Width) {
		for (int i = 0; i < count; i++) {
			int color = getColor(i);
			int r = 4;
			double vertical_v = -30 + 10 * (Math.random());// 垂直速度
			double horizontal_v = 10 - 20 * (Math.random());// 水平速度
			
			
		     int startX = (int)(Math.random()*1200);
		
		   
		     int startY = 0;

			Particle partical = new Particle(color, r, vertical_v,
					horizontal_v, startX, startY, startTime);
			particals.add(partical);
		}

	}
} 

