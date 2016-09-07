package com.example.tgame;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class MyService extends Service{
	
	MainActivity activity;
	public static MediaPlayer mp3;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		//¿ªÊ¼µÄÒôÀÖ
		
		mp3 = MediaPlayer.create(MyService.this, R.raw.yunxing);
		mp3.setLooping(true);
		mp3.start();
	   	return super.onStartCommand(intent, flags, startId);
		
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mp3.stop();
	}

}
