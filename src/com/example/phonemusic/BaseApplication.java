package com.example.phonemusic;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.support.v4.view.ViewPager;

/**
 * 
 * @author Administrator
 * @company Newbie
 * @date 2016-11-12
 * @des 单例，全局的盒子，放置大家都刻有用的东西
 */
public class BaseApplication extends Application {

	private static Context mContext;
	private static Handler mHandler;
	private static int mTid;

	@Override
	public void onCreate() {

		// 获取全局上下文
		mContext = getApplicationContext();

		// 放置一个主线程的Handler
		mHandler = new Handler();

		// 得到主线程的ID
		mTid = android.os.Process.myTid();

		super.onCreate();
	}

	
	public Map<String, String> mCacheMap = new HashMap<String, String>();
	
	public Map<String, String> getCacheMap() {
		return mCacheMap;
	}
	
	public static Context getmContext() {
		return mContext;
	}

	public static Handler getmHandler() {
		return mHandler;
	}

	public static int getmTid() {
		return mTid;
	}


	public static Context getContext() {
		return mContext;
	}

	public static Handler getHandler() {
		return mHandler;
	}

	public static int getTid() {
		return mTid;
	}

}
