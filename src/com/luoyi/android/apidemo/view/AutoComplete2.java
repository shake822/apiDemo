package com.luoyi.android.apidemo.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.luoyi.android.apidemo.R;
import com.luoyi.android.apidemo.utils.CountryUtils;

public class AutoComplete2 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_autocomplete_2);
		AutoCompleteTextView act = (AutoCompleteTextView) findViewById(R.id.act_2);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_text_item, CountryUtils.COUNTRIES);
		act.setAdapter(adapter);
	}
}
