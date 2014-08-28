package com.luoyi.android.apidemo.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.luoyi.android.apidemo.R;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 28, 2014
 */
public class SpinnerActivity1 extends Activity {

	private Spinner mSpinner;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_spinner_1);
		mSpinner = (Spinner) findViewById(R.id.spinner_2);

		// final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		// getApplicationContext(), R.layout.list_text_item, new String[] {
		// "123", "2323" });
		final ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(getApplicationContext(),
						R.array.planets_array,
						android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner.setAdapter(adapter);
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("onItemSelected" + adapter.getItem(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				System.out.println("onNothingSelected");
			}

		});

		Spinner mSpinner1 = (Spinner) findViewById(R.id.spinner_1);

		// final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		// getApplicationContext(), R.layout.list_text_item, new String[] {
		// "123", "2323" });
		final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter
				.createFromResource(getApplicationContext(),
						R.array.planets_array,
						android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner1.setAdapter(adapter);
		mSpinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("onItemSelected"
						+ adapter1.getItem(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				System.out.println("onNothingSelected");
			}

		});

	}
}
