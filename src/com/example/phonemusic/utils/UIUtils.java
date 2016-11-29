package com.example.phonemusic.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

import com.example.phonemusic.BaseApplication;

/**
 * 
 * @author Administrator
 *@company Newbie
 *@date 2016-11-12
 *@des 和UI处理有关的工具类
 */
public class UIUtils {

	/**
	 * 得到上下文
	 */
	public static Context getContext(){
		
		return BaseApplication.getContext();
	}
	
	/**
	 * 得到resource对象
	 */
	public static Resources getResources(){
		
		return getContext().getResources();
	}
	
	/**
	 * 得到String.xml中的字符
	 */
	public static String getString(int resId){
		return getResources().getString(resId);
	}
	
	/**
	 * @des 得到string.xml中的字符，带有占位符
	 * @param id
	 * @param formatArgs
	 * @return
	 */
	public static String getString(int id, Object... formatArgs){
		return getResources().getString(id, formatArgs);
	}
	
	/**
	 * 获得String.xml中的字符数组
	 */
	public static String[] getStringArr(int resId){
		
		return getResources().getStringArray(resId);
	}
	
	/**
	 * 获得color.xml中的颜色值数据
	 */
	public static int getColor(int resId){
		return getResources().getColor(resId);
	}
	
	/**
	 * 获得应用程序的包名
	 * @return
	 */
	public static String getPackageName(){
		return getContext().getPackageName();
	}
	
	
	/**
	 * 获得主线程的Id
	 * @return
	 */
	public static long getMainThreadId(){
		return BaseApplication.getTid();
	}
	
	
	/**
	 * 得到主线程的handler
	 * @return
	 */
	public static Handler getMainThreadHandler(){
		return BaseApplication.getHandler();
	}
	
	/**
	 * @des 安全地执行一个任务
	 * 
	 * @param task
	 */
	public static void performSecurityTask(Runnable task){
		//当前线程==子线程：通过消息机制，把任务交个主线程handle去执行
		//当前线程==主线程：直接执行任务
		int currentThreadId = android.os.Process.myTid();
		
		if (currentThreadId==getMainThreadId()) {
			task.run();
		}else {
			getMainThreadHandler().post(task);
		}
	}
	
	
	/**
	 * @des 设备像素转屏幕像素
	 * @param dip 设备像素
	 * @return
	 */
	public static int dip2Px(int dip){
		//得到px和dip的比例关系
		float mDensity = getResources().getDisplayMetrics().density;
		
		int px = (int) (mDensity*dip+.5f);
		
		return px;
	}
	
	
	/**
	 * @des 屏幕像素转成设备像素
	 * @param px 屏幕像素
	 * @return
	 */
	public static int px2Dip(int px){
		float mDensity = getResources().getDisplayMetrics().density;
	
		int dip = (int) (px/mDensity+.5f);
		
		return dip;
	}
	
	
}
