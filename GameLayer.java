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
	//����һ���������
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
		//���õ�ǰͼ��ɴ���
		super();
		this.setIsTouchEnabled(true);
		//��ʼ���������
		 bg = CCSprite.sprite("bg.jpg");
         // CCSprite��Ϸ�����࣬��Ҫ����һ��ͼƬ������Ϸ����
         bg.setAnchorPoint(CGPoint.getZero());
         // ���þ����ê��
         // ê������������Ļ����ʾ��λ�ã�ԭ��Ϊ�������½�Ϊ׼��ê���ֵ���Ա�����ê���Ԫ�ؿ��ߣ�Ϊ�ƶ��ľ���
         bg.setPosition(CGPoint.getZero());
         // ���þ�������꣬����Ļ�����½�Ϊԭ�㣬���ң�����Ϊ������,����OpenGL����ϵ
	     this.addChild(bg);
	     //��ʼ���������
	     mouse = CCSprite.sprite("mouse.png");
	    
	     //CGPoint����ͨ���������꣬�����Ǳ�ʾ����
	     CGPoint point = CGPoint.make(activity.mysize.width/2, activity.mysize.height/2);
	    
	     //���þ���λ��
	     mouse.setPosition(point);
	   
	     //������������
	     this.addChild(mouse);
	   
	     // player�������Ҫ��Ϊ1����bg����Ҫ��Ϊ0
	     // ����player����ʾ��bg���Ϸ�
	     // ����֮������Ҫ��Խ�ߣ�Խ������ʾ���ϲ�
	     
	     gameover = CCSprite.sprite("gameover.png");//����
	     jl1 = CCSprite.sprite("jl1.png");
	     jl2 = CCSprite.sprite("jl2.png");
	     jl3 = CCSprite.sprite("jl3.png");//����ת
	     jl4 = CCSprite.sprite("jl4.png");//����ת
	     jl5 = CCSprite.sprite("jl5.png");
	     jl6 = CCSprite.sprite("jl6.png");
	     stone = CCSprite.sprite("stone_hen.png");
	    
	     //���þ�������
	     jl1.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     jl2.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     jl3.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     jl4.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     jl5.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     jl6.setPosition(CGPoint.ccp(activity.mysize.width+100, activity.mysize.height/2));
	     gameover.setPosition(CGPoint.ccp(1800, 300));
	     stone.setPosition(CGPoint.ccp(activity.mysize.width/2, 0));
		
	     //����������
	 	 this.addChild(jl1);
	 	 this.addChild(jl2);
	 	 this.addChild(jl3);
	 	 this.addChild(jl4);
	 	 this.addChild(jl5);
	 	 this.addChild(jl6);
	 	 this.addChild(gameover);
	 	 this.addChild(stone);
 
	     //���������
	     //ʹ�����ظ�����
	    AddSpriteThread sts = new AddSpriteThread();
	    sts.start();
	     //��ײ���
	    DetectionThread dt = new DetectionThread();
	    dt.start();
	    //dt.setPriority(Thread.NORM_PRIORITY+3);//��������3
	    
	}
    
	//������ִ�иö���
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