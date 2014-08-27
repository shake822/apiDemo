package com.luoyi.android.apidemo.app;

import android.app.Activity;
import android.os.Bundle;

import com.luoyi.android.apidemo.R;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 27, 2014
 */
public class Translucent extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Be sure to call the super class.
		super.onCreate(savedInstanceState);

		// See assets/res/any/layout/translucent_background.xml for this
		// view layout definition, which is being set here as
		// the content of our screen.
		setContentView(R.layout.translucent_background);
	}
}
