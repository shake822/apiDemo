/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.luoyi.android.apidemo.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.luoyi.android.apidemo.R;
import com.luoyi.android.apidemo.utils.CountryUtils;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 27, 2014
 */
public class AutoComplete3 extends Activity {

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AutoCompleteTextView act = new AutoCompleteTextView(this);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		act.setHint("选择国家");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_text_item, CountryUtils.COUNTRIES);
		act.setCompletionHint("CompletionHint");
		act.setAdapter(adapter);
		act.setThreshold(4);
		addContentView(act, layoutParams);
	}
}
