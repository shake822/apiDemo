package com.luoyi.android.apidemo.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AutoCompleteTextView.OnDismissListener;
import android.widget.Toast;

import com.luoyi.android.apidemo.R;
import com.luoyi.android.apidemo.utils.CountryUtils;

@SuppressLint("NewApi")
public class AutoComplete1 extends Activity implements OnDismissListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_autocomplete_1);
		AutoCompleteTextView act = (AutoCompleteTextView) findViewById(R.id.act_1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line,
				CountryUtils.COUNTRIES);
		act.setAdapter(adapter);
		act.setOnDismissListener(this);
		AutoCompleteTextView.Validator validator = new AutoCompleteTextView.Validator() {

			@Override
			public boolean isValid(CharSequence text) {
				System.out.println("isValid  " + text);
				return false;
			}

			@Override
			public CharSequence fixText(CharSequence invalidText) {
				System.out.println("fixText  " + invalidText);
				return null;
			}
		};
		act.setValidator(validator);
	}

	@Override
	public void onDismiss() {
		Toast.makeText(this, "请选择", Toast.LENGTH_SHORT).show();
	}
}
