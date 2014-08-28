/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.luoyi.android.apidemo.view;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.luoyi.android.apidemo.BaseActivity;
import com.luoyi.android.apidemo.R;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 28, 2014
 */
public class SeekBarActivity extends BaseActivity {

	private SeekBar seekBar1;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_seekbar_1);
		seekBar1 = (SeekBar) findViewById(R.id.seekbar_1);
		final TextView tvProgess = (TextView) findViewById(R.id.seekbar_textview);
		final TextView tvStatus = (TextView) findViewById(R.id.seekbar_textview_1);
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				tvStatus.setText("off");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				tvStatus.setText("on");
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				tvProgess.setText(progress + "");
			}
		});
	}

	public void random(View view) {
		int i = (int) (Math.random() * seekBar1.getMax());
		seekBar1.setProgress(i);
	}
}
