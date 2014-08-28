/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.luoyi.android.apidemo.view;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

import com.luoyi.android.apidemo.BaseActivity;
import com.luoyi.android.apidemo.R;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 28, 2014
 */
public class RatingBarActivity1 extends BaseActivity {

	private RatingBar mRatingBar;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_ratingbar_1);
		mRatingBar = (RatingBar) findViewById(R.id.ratingbar_1);
		mRatingBar
				.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

					@Override
					public void onRatingChanged(RatingBar ratingBar,
							float rating, boolean fromUser) {
						showToast(mRatingBar.getRating() + "");
					}
				});
	}

	public void showValue(View view) {
		showToast(mRatingBar.getRating() + "");
	}
}
