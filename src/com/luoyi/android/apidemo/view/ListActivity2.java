package com.luoyi.android.apidemo.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.luoyi.android.apidemo.BaseActivity;
import com.luoyi.android.apidemo.R;
import com.luoyi.android.apidemo.bean.Person;

public class ListActivity2 extends BaseActivity {
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_1);
		mListView = (ListView) findViewById(R.id.list_1);
		List<Person> persons = new ArrayList<Person>(10);
		for (int i = 0; i < 10; i++) {
			Person p = new Person();
			p.setName("shake" + i);
			p.setTel(i + "5797");
			persons.add(p);
		}
		List2Adapter adapter = new List2Adapter(getApplicationContext(),
				persons);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				((List2Adapter) parent.getAdapter()).toggle(position);
			}
		});
	}

	private class List2Adapter extends BaseAdapter {
		private List<Person> lists;
		private Context context;

		public List2Adapter(Context context, List<Person> data) {
			this.context = context;
			this.lists = (List<Person>) data;
		}

		public void toggle(int position) {
			Person p = (Person) getItem(position);
			p.setExpand(!p.isExpand());
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return lists.size();
		}

		@Override
		public Object getItem(int position) {
			return lists.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			PersonDetail pd = null;
			Person p = (Person) getItem(position);
			if (convertView == null) {
				pd = new PersonDetail(context, p.getName(), p.getTel(), false);
			} else {
				pd = (PersonDetail) convertView;
				pd.setTitle(p.getName());
				pd.setContent(p.getTel());
				pd.setContentExpand(p.isExpand());
			}
			return pd;
		}
	}

	private class PersonDetail extends LinearLayout {

		private TextView mTitle;
		private TextView mContent;

		public PersonDetail(Context context, String title, String content,
				boolean expanded) {
			super(context);
			this.setOrientation(LinearLayout.VERTICAL);
			mTitle = new TextView(context);
			mTitle.setText(title);
			addView(mTitle, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			mContent = new TextView(context);
			mContent.setText(content);
			addView(mContent, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			mContent.setVisibility(expanded ? VISIBLE : GONE);
		}

		public void setTitle(String title) {
			mTitle.setText(title);
		}

		public void setContent(String content) {
			mContent.setText(content);
		}

		public void setContentExpand(boolean expand) {
			mContent.setVisibility(expand ? VISIBLE : GONE);
		}
	}
}
