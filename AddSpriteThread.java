package com.example.tgame;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class AddSpriteThread extends Thread{
	boolean addspriteflag=false;
    MainActivity activity;
	CCSprite addjl1;
	CCSprite addjl2;
	CCSprite addjl3;
	CCSprite addjl4;
	CCSprite addjl5;
	CCSprite addjl6;
	
	public AddSpriteThread() {
		// TODO Auto-generated constructor stub
		addspriteflag = true;
		this.addjl1 = GameLayer.jl1;
		this.addjl2 = GameLayer.jl2;
		this.addjl3 = GameLayer.jl3;
		this.addjl4 = GameLayer.jl4;
		this.addjl5 = GameLayer.jl5;
		this.addjl6 = GameLayer.jl6;
	}
    @Override
    public void run() {
    	while (!AddSpriteThread.currentThread().isInterrupted()) {
    		//得到精灵位置
    		CGPoint point_jl1 = addjl1.getPosition();
	    	CGPoint point_jl2 = addjl2.getPosition();
	    	CGPoint point_jl3 = addjl3.getPosition();
	    	CGPoint point_jl4 = addjl4.getPosition();
	    	CGPoint point_jl5 = addjl5.getPosition();
	    	CGPoint point_jl6 = addjl6.getPosition();
	    	
	       
	    	int i = (int)(Math.random()*6);
		     switch(i){
		        case 0:
		        	//检测精灵是否在屏幕上
	    	      if(point_jl1.x>=(activity.mysize.width+100)||point_jl1.x<10){
		             CCJumpBy actionUp1 = CCJumpBy.action(1, CGPoint.ccp(-160,0), 8, 4);
		             addjl1.setPosition(CGPoint.ccp((activity.mysize.width+100), activity.mysize.height/2));
		             addjl1.runAction(CCRepeatForever.action(actionUp1));
	    	      }
	    	      try{
		        	  Thread.sleep(4000);
		          }catch(Exception e){
		        	  e.printStackTrace();
		          }
		          break;
		     case 1:
		    	//检测精灵是否在屏幕上
		    	  if(point_jl2.x>=(activity.mysize.width+100)||point_jl2.x<10){
		            CCJumpBy actionUp2 = CCJumpBy.action(1, CGPoint.ccp(-160,0), 8, 4);
		            addjl2.setPosition(CGPoint.ccp((activity.mysize.width+100), activity.mysize.height/2));
		            addjl2.runAction(CCRepeatForever.action(actionUp2));
		    	  }
		    	  try{
		        	  Thread.sleep(4000);
		          }catch(Exception e){
		        	  e.printStackTrace();
		          }
		       break;
		     case 2:
		    	//检测精灵是否在屏幕上
		    	  if(point_jl3.x>=(activity.mysize.width+100)||point_jl3.x<10){
		    		CCSpawn action = CCSpawn.actions(CCJumpBy.action(1, CGPoint.ccp(-160,0), 8, 4),CCRotateBy.action(1, 720));
		    		addjl3.setPosition(CGPoint.ccp((activity.mysize.width+100), activity.mysize.height/2));
		    		addjl3.runAction(CCRepeatForever.action(action));
		    	  }
		    	  try{
		        	  Thread.sleep(4000);
		          }catch(Exception e){
		        	  e.printStackTrace();
		          }
		       break;
		     case 3:
		    	//检测精灵是否在屏幕上
		    	  if(point_jl4.x>=(activity.mysize.width+100)||point_jl4.x<10){
		    		CCSpawn action = CCSpawn.actions(CCJumpBy.action(1, CGPoint.ccp(-160,0), 8, 4),CCRotateBy.action(1, 720));
		    		addjl4.setPosition(CGPoint.ccp((activity.mysize.width+100), activity.mysize.height/2));
		    		addjl4.runAction(CCRepeatForever.action(action));
		    	  }
		    	  try{
		        	  Thread.sleep(4000);
		          }catch(Exception e){
		        	  e.printStackTrace();
		          }
		       break;
		     case 4:
		    	//检测精灵是否在屏幕上
		    	  if(point_jl5.x>=(activity.mysize.width+100)||point_jl5.x<10){
		            CCJumpBy actionUp2 = CCJumpBy.action(1, CGPoint.ccp(-160,0), 8, 4);
		            addjl5.setPosition(CGPoint.ccp((activity.mysize.width+100), activity.mysize.height/2));
		            addjl5.runAction(CCRepeatForever.action(actionUp2));
		    	  }
		    	  try{
		        	  Thread.sleep(4000);
		          }catch(Exception e){
		        	  e.printStackTrace();
		          }
		       break;
		     case 5:
		    	//检测精灵是否在屏幕上
		    	  if(point_jl6.x>=(activity.mysize.width+100)||point_jl6.x<10){
		    		CCSpawn action = CCSpawn.actions(CCJumpBy.action(1, CGPoint.ccp(-160,0), 8, 4),CCRotateBy.action(1, 720));
		    		addjl6.setPosition(CGPoint.ccp((activity.mysize.width+100), activity.mysize.height/2));
		    		addjl6.runAction(CCRepeatForever.action(action));
		    	  }
		    	  try{
		        	  Thread.sleep(4000);
		          }catch(Exception e){
		        	  e.printStackTrace();
		          }
		       break;
		      default:
		    	  try{
		        	  Thread.sleep(4000);
		          }catch(Exception e){
		        	  e.printStackTrace();
		          }
					break;
		    }
			
		}
    }
}
