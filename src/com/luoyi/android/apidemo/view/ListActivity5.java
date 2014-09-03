package com.luoyi.android.apidemo.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.luoyi.android.apidemo.BaseActivity;
import com.luoyi.android.apidemo.R;

public class ListActivity5 extends BaseActivity {

	private ListView mListView;

	private EditText mEditText;
	private ArrayList<CharSequence> names = new ArrayList<CharSequence>(16);

	private ArrayAdapter<CharSequence> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_5_text);
		mListView = (ListView) findViewById(R.id.list_5);
		mEditText = (EditText) findViewById(R.id.list_5_text);
		mEditText.setOnKeyListener(new MyKeyListener());
		mAdapter = new ArrayAdapter<CharSequence>(getApplicationContext(),
				R.layout.list_text_item, names);
		mListView.setAdapter(mAdapter);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

	private void sendText() {
		names.add(mEditText.getText());
		mAdapter.notifyDataSetChanged();
		mEditText.setText(null);
	}

	private final class MyKeyListener implements OnKeyListener {
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				switch (keyCode) {
				case KeyEvent.KEYCODE_ENTER:
				case KeyEvent.KEYCODE_DPAD_CENTER:
					sendText();
					break;
				}
			}
			return false;
		}
	}
}
