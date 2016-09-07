package com.example.tgame;

import java.util.ArrayList;

public class ParticleThread extends Thread {
	boolean flag;// �̱߳�ʶ
	ParticleView particalView;
	int sleepSpan = 80;
	double time = 0;
	double span = 0.15;
	private int window_Width;

	public ParticleThread(ParticleView particleView, int window_Width) {
		this.particalView = particleView;
		this.flag = true; // �����߳�ִ�б�־λΪtrue
		this.window_Width = window_Width;
	}

	@Override
	public void run() {
		while (flag) {
			particalView.particleSet.addPartical(5, time, window_Width);// ÿ�����5��
			ArrayList<Particle> particals = particalView.particleSet.particals;// ��ȡ���Ӽ���
			int count = particals.size();
			for (int i = 0; i < count; i++) {
				Particle partical = particals.get(i);
				double timeSpan = time - partical.startTime;// �������ʼ�����ڵ�ʱ���
				int tempX = (int) (partical.startX + partical.horizontal_v
						* timeSpan);// �����ʱ���ӵ�Xֵ
				// 4.9 * timeSpan * timeSpan=1/2*g*t*t;//����ʽ��g=9.8m/s;
				int tempY = (int) (partical.startY + 4.9 * timeSpan * timeSpan);// �����ʱ���ӵ�Yֵ
				if (tempY > ParticleView.DIE_OUT_LINE) {
					particals.remove(partical);// ɾ��������
					count = particals.size();// ���»�ȡ����
				}
				partical.x = tempX;
				partical.y = tempY;
			}
			time += span;
			try {
				Thread.sleep(sleepSpan);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
} 
