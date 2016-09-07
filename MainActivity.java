package com.example.tgame;

import java.util.HashMap;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.particlesystem.CCParticleSnow;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4F;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	//cocos2d�����ͼ�λ����ڸö�����
	private CCGLSurfaceView view = null;
	private ParticleView view1 = null;
	public static Handler myhandler; 
	//private int voiceID;
	SoundPool mPool;
	Intent intent;
	public static MainActivity app;
	public static GameLayer gamelayer;
	public static CGSize mysize;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app=this;

		
		 intent = new Intent(MainActivity.this,MyService.class);
		 startService(intent);
		//����Ϊ������ʾ
		CCDirector.sharedDirector().setDeviceOrientation(CCDirector.kCCDeviceOrientationPortrait);
			
		
		// voiceID = initSoundPool();
		// mPool.play(voiceID,1,1,0,0,1);
		 view1 = new ParticleView(this, 400, 800);
		 view = new CCGLSurfaceView(this);
		 setContentView(view1);
		  // ��ʼ��handler  
         myhandler = new Handler(){  
                 @Override  
                 public void handleMessage(Message msg)   {  
                	 if(msg.what==0) {// handler���յ���ص���Ϣ��  
                	   
                            setContentView(view); // ��ʾ������Ӧ�ý���  
                            stopService(intent);
                           
                	 }     
                 }  
        };           

        // �½�һ���̣߳���5���Ӻ���handler����һ����Ϣ  
        Runnable runnable = new Runnable(){   
       
                 public void run() { 
                          try  
                          {  
                               Thread.sleep(6000);  
                          }  
                          catch (InterruptedException e)  
                          {  
                               e.printStackTrace();  
                          }
                         Message m = myhandler.obtainMessage(); // ��ȡһ��Message
          				 m.what = 0; // ������Ϣ��ʶ
          				 m.arg1 = 1;
                         myhandler.sendMessage(m);
                       
                 }  
        };  

        Thread thread = new Thread(runnable);  
        
        thread.start(); 
       
		//�õ�CCDirector����
		CCDirector director = CCDirector.sharedDirector();
		//����Ӧ�ó����������
		//�������õ�ǰ��Ϸ��ʹ�õ�view����
		director.attachInView(view);
		//director.attachInView(view1);
		director.setLandscape(false);
		//������Ϸ�Ƿ���ʾFPSֵ
		director.setDisplayFPS(true);
		//������Ⱦһ֡��������Ҫ��ʱ��
		director.setAnimationInterval(1/60.0);
		//��ȡ��Ļ�ߴ�
		mysize = CCDirector.sharedDirector().winSize();
		//������Ļ�ֱ���
		CCDirector.sharedDirector().setScreenSize(mysize.width, mysize.height);
		//����һ����Ϸ��������
		CCScene scene = CCScene.node();
		//���ɲ��������
	
		gamelayer = new GameLayer();
		scene.addChild(gamelayer);
	
		//������Ϸ����
		
	    director.runWithScene(scene);
	
	}
	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//�ָ���Ϸ����
		CCDirector.sharedDirector().resume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//��ͣ����Ϸ�г�ʱ����
		CCDirector.sharedDirector().onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//��Ϸ����ʱ����
		CCDirector.sharedDirector().end();
	}
    
	@TargetApi(Build.VERSION_CODES.BASE)
	@SuppressLint("NewApi") int initSoundPool(){
       
		/*
         * 21�汾��SoundPool�Ĵ��������ܴ�ı�
         */
        //�ж�ϵͳsdk�汾������汾����21�����õ�һ��
        if(Build.VERSION.SDK_INT>=21){
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams(1);//������Ƶ����
            //AudioAttributes��һ����װ��Ƶ�������Եķ���
            AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
            attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);//������Ƶ���ĺ��ʵ�����
            builder.setAudioAttributes(attrBuilder.build());//����һ��AudioAttributes
            mPool = builder.build();
        }else{
            mPool = new SoundPool(2,AudioManager.STREAM_MUSIC,0);
        }
        //load�ķ���ֵ��һ��int���ֵ����Ƶ��id����SoundPool��play()�����м������id���ܲ��������Ƶ
        return mPool.load(getApplicationContext(),R.raw.stond,1);
    }
 
}
