package com.example.tgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {
	ParticleView particalView;// �Զ���View
	SurfaceHolder surfaceHolder;
	boolean flag = false;// �̱߳�ʶ
	int sleepSpan = 15;// �߳�����
	long start = System.nanoTime();// ��ʵʱ�䣬���ڼ���֡����
	int count = 0;// ����֡��

	public DrawThread(ParticleView particalView, SurfaceHolder surfaceHolder) {
		super();
		this.particalView = particalView;
		this.surfaceHolder = surfaceHolder;
		this.flag = true;
	}

	

	@Override
	public void run() {
		Canvas canvas = null;
		while (flag) {
			try {
				canvas = surfaceHolder.lockCanvas();// ��ȡcanvas.
				synchronized (surfaceHolder) {
					particalView.doDraw(canvas);// ���л���ballView.

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);// ����
				}
			}
			this.count++;
			if (count == 20) { // �������20֡
				count = 0; // ��ռ�����
				long tempStamp = System.nanoTime();// ��ȡ��ǰʱ��
				long span = tempStamp - start; // ��ȡʱ����
				start = tempStamp; // Ϊstart���¸�ֵ
				double fps = Math.round(100000000000.0 / span * 20) / 100.0;// ����֡����
				particalView.fps = "FPS:" + fps;// ���������֡�������õ�BallView����Ӧ�ַ���������
			}
			try {
				Thread.sleep(sleepSpan); // �߳�����һ��ʱ��
			} catch (Exception e) {
				e.printStackTrace(); // ���񲢴�ӡ�쳣
			}
		}
	}

} 