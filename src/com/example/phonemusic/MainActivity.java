package com.example.phonemusic;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonemusic.adapter.SongsListAdapter;
import com.example.phonemusic.utils.MediaUtils;
import com.example.phonemusic.utils.UIUtils;
import com.example.phonemusic.view.ScrollableViewGroup;
import com.example.phonemusic.view.ScrollableViewGroup.OnCurrentViewChangedListener;
public class MainActivity extends Activity implements OnClickListener {

	
	/**------  当前的播放进度  --------*/
	private TextView mTv_curduration;
	
	/**------  迷你的歌词显示  --------*/
	private TextView mTv_minilrc;
	
	/**------  音乐播放总进度  --------*/
	private TextView mTv_totalduration;
	
	/**------  seekBar的显示进度  --------*/
	private SeekBar mSk_duration;
	
	/**------  音乐的播放模式  --------*/
	private ImageView mIv_bottom_model;
	
	/**------  播放or暂停音乐  --------*/
	private ImageView mIv_bottom_play;
	
	/**------  歌词or歌曲显示列表  --------*/
	private ListView mLv_list;

	/**------  上方播放按钮  --------*/
	private ImageButton mIbTopPlay;

	/**------  音乐列表按钮  --------*/
	private ImageButton mIbTopList;

	/**------  歌词列表按钮  --------*/
	private ImageButton mIbTopLrc;

	/**------  声音大小按钮  --------*/
	private ImageButton mIbTopVolum;

	/**------  播放模式按钮  --------*/
	private ImageButton mIbBottomModel;

	/**------  上一曲按钮  --------*/
	private ImageButton mIbBottomLast;

	/**------  开始播放按钮  --------*/
	private ImageButton mIbBottomPlay;

	/**------  下一曲按钮  --------*/
	private ImageButton mIbBottomNext;

	/**------  下方更新按钮  --------*/
	private ImageButton mIbBottomUpdate;

	/**------  不同页面切换按钮  --------*/
	private ScrollableViewGroup mSvg_main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initView();
		
		initEvent();
		
		initData();
		
		initListener();

	}

	private void initView() {
		
		setContentView(R.layout.activity_main);	
		
		mTv_curduration = (TextView) findViewById(R.id.tv_curduration);
		
		mTv_minilrc = (TextView) findViewById(R.id.tv_minilrc);
		
		mTv_totalduration = (TextView) findViewById(R.id.tv_totalduration);
		
		mSk_duration = (SeekBar) findViewById(R.id.sk_duration);
		
		mIv_bottom_model = (ImageView) findViewById(R.id.iv_bottom_model);
		
		mIv_bottom_play = (ImageView) findViewById(R.id.iv_bottom_play);
		
		mLv_list = (ListView) findViewById(R.id.lv_list);
		
		mSvg_main = (ScrollableViewGroup) findViewById(R.id.svg_main);
		
		
		
		
		mIbTopPlay = (ImageButton) findViewById(R.id.ib_top_play);
		mIbTopList = (ImageButton) findViewById(R.id.ib_top_list);
		mIbTopLrc = (ImageButton) findViewById(R.id.ib_top_lrc);
		
		mIbTopVolum = (ImageButton) findViewById(R.id.ib_top_volumn);
		mIbBottomModel = (ImageButton) findViewById(R.id.ib_bottom_model);
		mIbBottomLast = (ImageButton) findViewById(R.id.ib_bottom_last);
		mIbBottomPlay = (ImageButton) findViewById(R.id.ib_bottom_play);
		mIbBottomNext = (ImageButton) findViewById(R.id.ib_bottom_next);
		mIbBottomUpdate = (ImageButton) findViewById(R.id.ib_bottom_update);
		
		//设置默认选中播放界面
		setTopSelectId(mIbTopPlay);

	}

	private void initData() {
		
		/**------  初始化本地音乐列表  --------*/
		MediaUtils.initSongsFromNative(this);
		
		SongsListAdapter mSongsListAdapter=new SongsListAdapter();
		
		mLv_list.setAdapter(mSongsListAdapter);
	
	}

	private void initListener() {

		mIbTopList.setOnClickListener(this);
		mIbTopLrc.setOnClickListener(this);
		mIbTopPlay.setOnClickListener(this);
		mIbTopVolum.setOnClickListener(this);
		
		
		mIbBottomLast.setOnClickListener(this);
		mIbBottomModel.setOnClickListener(this);
		mIbBottomNext.setOnClickListener(this);
		mIbBottomPlay.setOnClickListener(this);
		mIbBottomUpdate.setOnClickListener(this);
		
		/**------  给ScrollableViewGroup设置滑动事件  --------*/
		mSvg_main.setOnCurrentViewChangedListener(new OnCurrentViewChangedListener() {
			
			@Override
			public void onCurrentViewChanged(View view, int currentview) {
				switch (currentview) {
				case 0:
					setTopSelectId(mIbTopPlay);
					break;
				case 1:
					setTopSelectId(mIbTopList);
					break;
				case 2:
					setTopSelectId(mIbTopLrc);
					break;

				default:
					break;
				}
				
			}
		});
		
	}

	private void initEvent() {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_top_play://上方播放按钮
			mSvg_main.setCurrentView(0);//设置显示播放界面
			setTopSelectId(mIbTopPlay);
			break;
		case R.id.ib_top_list://上方歌曲列表按钮
			mSvg_main.setCurrentView(1);//显示歌曲列表界面
			setTopSelectId(mIbTopList);
			break;
		case R.id.ib_top_lrc://上方歌词列表按钮
			mSvg_main.setCurrentView(2);//显示歌词列表界面
			setTopSelectId(mIbTopLrc);
			break;
		case R.id.ib_top_volumn://上方声音按钮
			Toast.makeText(UIUtils.getContext(), "上方声音按钮", 0).show();
			setTopSelectId(mIbTopVolum);
			break;
			
		case R.id.ib_bottom_model://下方播放模式切换按钮
			Toast.makeText(UIUtils.getContext(), "下方播放模式切换按钮", 0).show();
			break;
		case R.id.ib_bottom_last://下方上一曲按钮
			Toast.makeText(UIUtils.getContext(), "下方上一曲按钮", 0).show();
			
			break;
		case R.id.ib_bottom_play://下方播放按钮
			Toast.makeText(UIUtils.getContext(), "下方播放按钮", 0).show();
			
			break;
		case R.id.ib_bottom_next://下方下一曲按钮
			Toast.makeText(UIUtils.getContext(), "下方下一曲按钮", 0).show();
			
			break;
		case R.id.ib_bottom_update://下方更新按钮
			Toast.makeText(UIUtils.getContext(), "下方更新按钮", 0).show();
			
			break;

		default:
			break;
		}
		
	}

	/**
	 * @des 顶部按钮的选中效果
	 * 
	 * @param ibTopPlay
	 */
	private void setTopSelectId(ImageButton ibTopPlay) {
		//还原所有控件的效果，让top按钮初始化为未选中状态
		mIbTopList.setSelected(false);
		mIbTopLrc.setSelected(false);
		mIbTopPlay.setSelected(false);
		mIbTopVolum.setSelected(false);
		
		//让传进来的imageButton设置为选中选中状态
		ibTopPlay.setSelected(true);
	}
}
