package com.example.tgame;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//�̳���SurfaceView��ʵ��SurfaceHolder.Callback�ӿڵ���
public class ParticleView extends SurfaceView implements SurfaceHolder.Callback {
	public static int DIE_OUT_LINE = 420;// ���ӵ�Y���곬����ֵ������Ӽ����Ƴ�
	DrawThread drawThread; // ��̨ˢ����Ļ�߳�
	ParticleSet particleSet; // ParticleSet��������
	ParticleThread particleThread; // ParticleThread��������
	String fps = "FPS:N/A"; // ����֡�����ַ���

	// ����������ʼ����Ҫ��Ա����
	public ParticleView(Context context, int window_Width, int window_Height) {
		super(context); // ���ø��๹����
		DIE_OUT_LINE = window_Height;
		this.getHolder().addCallback(this); // ���Callback�ӿ�
		drawThread = new DrawThread(this, getHolder()); // ����DrawThread����
		particleSet = new ParticleSet(); // ����ParticleSet����
		particleThread = new ParticleThread(this, window_Width); // ����ParticleThread����
	}

	// ������������Ļ
	public void doDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK); // ����
		ArrayList<Particle> particleSet = this.particleSet.particals; // ���ParticleSet�����е����Ӽ��϶���
		Paint paint = new Paint(); // �������ʶ���
		for (int i = 0; i < particleSet.size(); i++) { // �������Ӽ��ϣ�����ÿ������
			Particle p = particleSet.get(i);
			paint.setColor(p.color); // ���û�����ɫΪ������ɫ
			int tempX = p.x; // �������X����
			int tempY = p.y; // �������Y����
			int tempRadius = p.r; // ������Ӱ뾶
			RectF oval = new RectF(tempX, tempY, tempX + 2 * tempRadius, tempY
					+ 2 * tempRadius);
			// canvas.drawOval(oval, paint); // ������Բ����
			canvas.drawCircle(tempX, tempY, tempRadius, paint);
		}
		paint.setColor(Color.WHITE); // ���û�����ɫ
		paint.setTextSize(18); // �������ִ�С
		paint.setAntiAlias(true); // ���ÿ����
		canvas.drawText(fps, 15, 15, paint);// ����֡�����ַ���
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {// ��дsurfaceChanged����
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {// ��дsurfaceCreated����
		if (!drawThread.isAlive()) { // ���DrawThreadû������������������߳�
			drawThread.start();
		}
		if (!particleThread.isAlive()) { // ���ParticleThreadû������������������߳�
			particleThread.start();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {// ��дsurfaceDestroyed����
		drawThread.flag = false; // ֹͣ�̵߳�ִ��
		drawThread = null; // ��dtָ��Ķ�������Ϊ����
	}
} 