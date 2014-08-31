package com.luoyi.android.apidemo.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.luoyi.android.apidemo.BaseActivity;
import com.luoyi.android.apidemo.R;

public class ListActivity1 extends BaseActivity {

	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_1);
		mListView = (ListView) findViewById(R.id.list_1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.list_text_item, new String[] {
						"shake", "ask" });
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showToast(parent.getItemAtPosition(position));
			}
		});
	}
}
