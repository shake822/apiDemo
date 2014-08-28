package com.luoyi.android.apidemo.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.luoyi.android.apidemo.BaseActivity;
import com.luoyi.android.apidemo.R;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 28, 2014
 */
public class ProgressActivity1 extends BaseActivity {

	private ProgressBar mProgressBar;

	private int mProgressStatus = 0;
	private Thread mThread;
	private final Handler mHandler = new Handler();

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setProgressBarVisibility(true);
		setContentView(R.layout.view_progress_1);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar2);
	}

	public void goClick(View view) {
		mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (mProgressStatus < 100) {
					mProgressStatus = mProgressStatus + 10;
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							System.out.println(mProgressStatus);
							mProgressBar.setProgress(mProgressStatus);
						}
					});
				}
			}
		});
		mThread.start();
	}

	public void stopClick(View view) {
		mThread.interrupt();
	}

	public void backClick(View view) {
		mThread.interrupt();
		mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (mProgressStatus > 0) {
					mProgressStatus = mProgressStatus - 10;
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							mProgressBar.setProgress(mProgressStatus);
						}
					});
				}
			}
		});
		mThread.start();
	}
}
