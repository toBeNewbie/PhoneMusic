package com.example.phonemusic.adapter;



import com.example.phonemusic.R;
import com.example.phonemusic.bean.MusicInfoBean;
import com.example.phonemusic.utils.MediaUtils;
import com.example.phonemusic.utils.UIUtils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SongsListAdapter extends BaseAdapter {

	
	@Override
	public int getCount() {
		if (MediaUtils.mMusicInfoBeans!=null) {
			return MediaUtils.mMusicInfoBeans.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return MediaUtils.mMusicInfoBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if (convertView == null) {
			convertView = View.inflate(UIUtils.getContext(),R.layout.item_music , null);
			holder = new ViewHolder();
			holder.mTvSongName = (TextView) convertView.findViewById(R.id.tv_title);
			holder.mTvSingerName=(TextView) convertView.findViewById(R.id.tv_artist);
			
			convertView.setTag(holder);
		}else {
			
			holder=(ViewHolder) convertView.getTag();
		}
		
		/**------  获取数据  --------*/
		MusicInfoBean musicInfoBean = MediaUtils.mMusicInfoBeans.get(position);
		
		/**------  显示数据  --------*/
		holder.mTvSingerName.setText(musicInfoBean.singerName);
		holder.mTvSongName.setText(musicInfoBean.songName);
		return convertView;
	}
	
	
	class ViewHolder{
		
		TextView mTvSongName;
		
		TextView mTvSingerName;
	}

}
