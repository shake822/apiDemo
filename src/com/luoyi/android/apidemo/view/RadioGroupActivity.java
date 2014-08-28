/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.luoyi.android.apidemo.view;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.luoyi.android.apidemo.BaseActivity;
import com.luoyi.android.apidemo.R;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 28, 2014
 */
public class RadioGroupActivity extends BaseActivity {

	private RadioGroup mRadioGroup;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_radiogroup_1);
		mRadioGroup = (RadioGroup) findViewById(R.id.radio_group_1);
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				displayMessage();
			}

		});

	}

	/**
	 * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
	 * 
	 * @param group
	 */
	private void displayMessage() {
		RadioButton tmp = (RadioButton) findViewById(mRadioGroup
				.getCheckedRadioButtonId());
		if (tmp != null) {
			showToast(tmp.getText());
		}
	}

	public void showValue(View view) {
		displayMessage();
		mRadioGroup.clearCheck();
	}
}
