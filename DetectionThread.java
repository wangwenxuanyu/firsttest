package com.example.tgame;

import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

public class DetectionThread extends Thread{
	MainActivity activity;
	boolean detetionflag=false;
	CCSprite detetionmouse;
	CCSprite detetionbg;
	CCSprite detetiongameover;
	CGRect mouse_edge;
	CGRect jl1_edge;
	CGRect jl2_edge;
	CGRect jl3_edge;
	CGRect jl4_edge;
	CGRect jl5_edge;
	CGRect jl6_edge;
	CGRect stone_edge;
	CCSprite detetionjl1;
	CCSprite detetionjl2;
	CCSprite detetionjl3;
	CCSprite detetionjl4;
	CCSprite detetionjl5;
	CCSprite detetionjl6;
	CCSprite detetionstone;
	public DetectionThread() {
		detetionflag = true;
		this.detetionjl1=GameLayer.jl1;
		this.detetionjl2=GameLayer.jl2;
		this.detetionjl3=GameLayer.jl3;
		this.detetionjl4=GameLayer.jl4;
		this.detetionjl5=GameLayer.jl5;
		this.detetionjl6=GameLayer.jl6;
		this.detetionbg=GameLayer.bg;
		this.detetiongameover=GameLayer.gameover;
		this.detetionmouse=GameLayer.mouse;
		this.detetionstone=GameLayer.stone;
	
	}
	@Override
	public void run() {
		while (detetionflag) {
			//得到精灵边缘
			 mouse_edge = detetionmouse.getBoundingBox();
			 jl1_edge = detetionjl1.getBoundingBox();
			 jl2_edge = detetionjl2.getBoundingBox();
			 jl3_edge = detetionjl3.getBoundingBox();
			 jl4_edge = detetionjl4.getBoundingBox();
			 jl5_edge = detetionjl5.getBoundingBox();
			 jl6_edge = detetionjl6.getBoundingBox();
			 stone_edge = detetionstone.getBoundingBox();
        	 if(CGRect.containsRect(stone_edge, mouse_edge)||CGRect.containsRect(mouse_edge, jl1_edge)||CGRect.containsRect(mouse_edge, jl2_edge)||CGRect.containsRect(mouse_edge, jl3_edge)||CGRect.containsRect(mouse_edge, jl4_edge)||CGRect.containsRect(mouse_edge, jl5_edge)||CGRect.containsRect(mouse_edge, jl6_edge)){
   		          detetiongameover.setPosition(CGPoint.ccp(activity.mysize.width/2, activity.mysize.height/2));
   		          CCFadeOut go = CCFadeOut.action(1.0f);
   		          detetionbg.runAction(go);
   		          //线程结束
   		          break;
   		  
   	       }
			
		}
	}

}
