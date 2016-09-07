package com.example.tgame;

import java.util.HashMap;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class PlaySoundPool {
	private Context context;
	public PlaySoundPool(Context context) {
	// TODO Auto-generated constructor stub
	this.context = context;
	initSounds();
	}
	//��Ч������ 
	int streamVolume; 

	//����SoundPool ���� 
	private SoundPool soundPool; 

	//����HASH�� 
	private HashMap soundPoolMap; 

	 
	public void initSounds() { 
	//��ʼ��soundPool ����,��һ�������������ж��ٸ�������ͬʱ����,��2����������������,������������������Ʒ�� 
	soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
	//��ʼ��HASH�� 
	soundPoolMap = new HashMap(); 

	//��������豸���豸���� 
	AudioManager mgr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE); 
	streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC); 
	} 

	 
	public void loadSfx(int raw, int ID) { 
	//����Դ�е���Ч���ص�ָ����ID(���ŵ�ʱ��Ͷ�Ӧ�����ID���ž�����) 
	soundPoolMap.put(ID, soundPool.load(context, raw, ID)); 
	} 

	 
	public void play(int sound, int uLoop) { 
	soundPool.play((Integer) soundPoolMap.get(sound), streamVolume, streamVolume, 1, uLoop, 1f); 
	} 

}
