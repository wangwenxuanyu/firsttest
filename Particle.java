package com.example.tgame;

import java.util.ArrayList;

import android.graphics.Color;

public class Particle {
	int color; // ��ɫ
	int r;// �뾶
	double vertical_v;// ��ֱ�ٶ�
	double horizontal_v;// ˮƽ�ٶ�
	int startX;
	int startY;
	int x;
	int y;
	double startTime;// ��ʼʱ��

	public Particle(int color, int r, double vertical_v, double horizontal_v,
			int x, int y, double startTime) {
		super();
		this.color = color;
		this.r = r;
		this.vertical_v = vertical_v;
		this.horizontal_v = horizontal_v;
		this.startX = x;
		this.startY = y;
		this.x = x;
		this.y = y;
		this.startTime = startTime;
	}

}

