package com.luoyi.android.apidemo.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.luoyi.android.apidemo.BaseActivity;
import com.luoyi.android.apidemo.R;

public class ListActivity7 extends BaseActivity {
	private ListView mListView;
	private ArrayList<String> mData = new ArrayList<String>(32);
	private int times;
	private View mMoreView;
	private int lastItem;

	private ArrayAdapter<String> adapter;
	private Handler mHandler = new Handler();

	private String getPrefix() {
		String prefix = "";
		switch (times % 3) {
		case 0:
			prefix = "First";
			break;
		case 1:
			prefix = "Second";
			break;
		case 2:
			prefix = "Third";
			break;
		default:
			break;
		}
		return prefix;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_1);
		mListView = (ListView) findViewById(R.id.list_1);
		mMoreView = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.more_data_loading, null);
		mData.addAll(getData());
		adapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.list_text_item, mData);
		mListView.addFooterView(mMoreView);
		mListView.setAdapter(adapter);
		mListView.setOnScrollListener(new MyOnscrollListener());
	}

	private final class MyOnscrollListener implements OnScrollListener {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			mMoreView.setVisibility(View.VISIBLE);
			if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
					&& lastItem == mData.size()) {
				if (lastItem > 30) {
					mHandler.postDelayed(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(getApplicationContext(), "沒有数据了",
									Toast.LENGTH_LONG).show();
							mListView.removeFooterView(mMoreView);
						}
					}, 3000);
				}
				times++;
				mData.addAll(getData());
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						adapter.notifyDataSetChanged();
						mMoreView.setVisibility(View.GONE);
					}
				}, 3000);
			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			lastItem = visibleItemCount + firstVisibleItem - 1;
		}

	}

	private ArrayList<String> getData() {
		ArrayList<String> data = new ArrayList<String>(10);
		for (int i = 0; i < 10; i++) {
			data.add(getPrefix() + "NO " + i);
		}
		return data;
	}
}
