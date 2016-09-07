package com.example.tgame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.jar.Attributes;
import android.content.Context;


import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.ease.CCEaseInOut;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCIntervalAction;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.particlesystem.CCParticleFire;
import org.cocos2d.particlesystem.CCParticleSnow;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.sound.SoundEngine;

import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.ccColor4F;



import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;

public class GameLayer extends CCLayer{
	//声明一个精灵对象
	public static CCSprite bg;
	public static CCSprite gameover;
	public static CCSprite mouse;
	public static CCSprite stone;
	public static CCSprite jl1;
	public static CCSprite jl2;
	public static CCSprite jl3;
	public static CCSprite jl4;
	public static CCSprite jl5;
	public static CCSprite jl6;
	CCParticleSystem emitter;
    CCSprite background;
    SoundPool mPool;
    private int voiceID;
    MainActivity activity;
   
	public GameLayer(){
		//设置当前图层可触摸
		super();
		this.setIsTouchEnabled(true);
		//初始化精灵对象
		 bg = CCSprite.sprite("bg.jpg");
         // CCSprite游戏精灵类，需要加载一张图片代表游戏精灵
         bg.setAnchorPoint(CGPoint.getZero());
         // 设置精灵的锚点
         // 锚点是设置在屏幕上显示的位置，原点为自身左下角为准，锚点的值乘以被设置锚点的元素宽或高，为移动的距离
         bg.setPosition(CGPoint.getZero());
         // 设置精灵的坐标，以屏幕的左下角为原点，向右，向上为正方向,属于OpenGL坐标系
	     this.addChild(bg);
	     //初始化精灵对象
	     mouse = CCSprite.sprite("mouse.png");
	    
	     //CGPoint对象通常用于坐标，或者是表示向量
	     CGPoint point = CGPoint.make(activity.mysize.width/2, activity.mysize.height/2);
	    
	     //设置精灵位置
	     mouse.setPosition(point);
	   
	     //加至布景层中
	     this.addChild(mouse);
	   
	     // player精灵的重要性为1，而bg的重要性为0
	     // 所以player会显示在bg的上方
	     // 换言之就是重要性越高，越优先显示在上层
	     
	     gameover = CCSprite.sprite("gameover.png");//背景
	     jl1 = CCSprite.sprite("jl1.png");
	     jl2 = CCSprite.sprite("jl2.png");
	     jl3 = CCSprite.sprite("jl3.png");//加旋转
	     jl4 = CCSprite.sprite("jl4.png");//加旋转
	     jl5 = CCSprite.sprite("jl5.png");
	     jl6 = CCSprite.sprite("jl6.png");
	     stone = CCSprite.sprite("stone_hen.png");
	    
	     //设置精灵坐标
	     jl1.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     jl2.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     jl3.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     jl4.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     jl5.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     jl6.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     gameover.setPosition(CGPoint.ccp(1800, 300));
	     stone.setPosition(CGPoint.ccp(activity.mysize.width/2, 0));
		
	     //加至布景层
	 	 this.addChild(jl1);
	 	 this.addChild(jl2);
	 	 this.addChild(jl3);
	 	 this.addChild(jl4);
	 	 this.addChild(jl5);
	 	 this.addChild(jl6);
	 	 this.addChild(gameover);
	 	 this.addChild(stone);
 
	     //产生随机数
	     //使精灵重复出现
	    AddSpriteThread sts = new AddSpriteThread();
	    sts.start();
	     //碰撞检测
	    DetectionThread dt = new DetectionThread();
	    dt.start();
	    //dt.setPriority(Thread.NORM_PRIORITY+3);//正常加上3
	    
	}
    
	//触屏就执行该动作
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		
		// TODO Auto-generated method stub
		 CCJumpBy actionUp3 = CCJumpBy.action(1, CGPoint.ccp(0,160), 8, 4);
         mouse.runAction(actionUp3);
         SoundEngine.sharedEngine().playEffect(activity.app, R.raw.cat);
	     return super.ccTouchesBegan(event);
	}
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		// TODO Auto-generated method stub
		CCJumpBy actionUp3 = CCJumpBy.action(1, CGPoint.ccp(0,-120), 8, 4);
	    mouse.runAction(CCRepeatForever.action(actionUp3));
		return super.ccTouchesEnded(event);
	}
	
}