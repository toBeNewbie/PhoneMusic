package com.example.phonemusic.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.phonemusic.bean.MusicInfoBean;

/**
 * 
 * @author Administrator
 *@company Newbie
 *@date 2016-11-29
 *@des 获取手机列表音乐的工具类
 */
public class MediaUtils {
	
	public static List<MusicInfoBean> mMusicInfoBeans=new ArrayList<MusicInfoBean>();
	
	/**
	 * @des 初始化本地歌曲列表
	 * @param context
	 */
	public static void initSongsFromNative(Context context){
		
		mMusicInfoBeans.clear();
		
		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;//uri
		
		String[] projection = { MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST,
				MediaStore.Audio.Media.DATA };
		
		Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
		
		while (cursor.moveToNext()) {
			
			String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
			String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
			String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
			
			MusicInfoBean musicInfoBean=new MusicInfoBean();
			musicInfoBean.pathName=path;
			musicInfoBean.singerName=artist;
			musicInfoBean.songName=title;
			
			mMusicInfoBeans.add(musicInfoBean);
		}
	}
}
