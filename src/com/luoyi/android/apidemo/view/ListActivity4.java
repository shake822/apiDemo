package com.luoyi.android.apidemo.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.luoyi.android.apidemo.BaseActivity;
import com.luoyi.android.apidemo.R;

public class ListActivity4 extends BaseActivity {

	private ListView mListView;

	private String[] names = new String[] { "shake", "ask", "zhaoqunqi",
			"lianlian" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_1);
		mListView = (ListView) findViewById(R.id.list_1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.list_radio_item, names);
		mListView.setAdapter(adapter);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}
}
