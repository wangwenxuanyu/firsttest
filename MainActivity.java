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
	//cocos2d将会把图形绘制在该对象上
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
		//设置为横屏显示
		CCDirector.sharedDirector().setDeviceOrientation(CCDirector.kCCDeviceOrientationPortrait);
			
		
		// voiceID = initSoundPool();
		// mPool.play(voiceID,1,1,0,0,1);
		 view1 = new ParticleView(this, 400, 800);
		 view = new CCGLSurfaceView(this);
		 setContentView(view1);
		  // 初始化handler  
         myhandler = new Handler(){  
                 @Override  
                 public void handleMessage(Message msg)   {  
                	 if(msg.what==0) {// handler接收到相关的消息后  
                	   
                            setContentView(view); // 显示真正的应用界面  
                            stopService(intent);
                           
                	 }     
                 }  
        };           

        // 新建一个线程，过5秒钟后向handler发送一个消息  
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
                         Message m = myhandler.obtainMessage(); // 获取一个Message
          				 m.what = 0; // 设置消息标识
          				 m.arg1 = 1;
                         myhandler.sendMessage(m);
                       
                 }  
        };  

        Thread thread = new Thread(runnable);  
        
        thread.start(); 
       
		//得到CCDirector对象
		CCDirector director = CCDirector.sharedDirector();
		//设置应用程序相关属性
		//设置设置当前游戏所使用的view对象
		director.attachInView(view);
		//director.attachInView(view1);
		director.setLandscape(false);
		//设置游戏是否显示FPS值
		director.setDisplayFPS(true);
		//设置渲染一帧数据所需要的时间
		director.setAnimationInterval(1/60.0);
		//获取屏幕尺寸
		mysize = CCDirector.sharedDirector().winSize();
		//设置屏幕分辨率
		CCDirector.sharedDirector().setScreenSize(mysize.width, mysize.height);
		//生成一个游戏场景对象
		CCScene scene = CCScene.node();
		//生成布景层对象
	
		gamelayer = new GameLayer();
		scene.addChild(gamelayer);
	
		//运行游戏场景
		
	    director.runWithScene(scene);
	
	}
	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//恢复游戏运行
		CCDirector.sharedDirector().resume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//暂停，游戏切除时调用
		CCDirector.sharedDirector().onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//游戏结束时调用
		CCDirector.sharedDirector().end();
	}
    
	@TargetApi(Build.VERSION_CODES.BASE)
	@SuppressLint("NewApi") int initSoundPool(){
       
		/*
         * 21版本后，SoundPool的创建发生很大改变
         */
        //判断系统sdk版本，如果版本超过21，调用第一种
        if(Build.VERSION.SDK_INT>=21){
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams(1);//传入音频数量
            //AudioAttributes是一个封装音频各种属性的方法
            AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
            attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);//设置音频流的合适的属性
            builder.setAudioAttributes(attrBuilder.build());//加载一个AudioAttributes
            mPool = builder.build();
        }else{
            mPool = new SoundPool(2,AudioManager.STREAM_MUSIC,0);
        }
        //load的返回值是一个int类的值：音频的id，在SoundPool的play()方法中加入这个id就能播放这个音频
        return mPool.load(getApplicationContext(),R.raw.stond,1);
    }
 
}
