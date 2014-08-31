package com.luoyi.android.apidemo;

import android.app.Activity;
import android.widget.Toast;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 28, 2014
 */
public class BaseActivity extends Activity {
	protected void showToast(Object msg) {
		String alertMsg = "";
		if (msg != null) {
			alertMsg = msg.toString();
		}
		Toast.makeText(this, alertMsg, Toast.LENGTH_SHORT).show();
	}
}
