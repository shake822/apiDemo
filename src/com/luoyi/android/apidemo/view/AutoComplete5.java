/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.luoyi.android.apidemo.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 27, 2014
 */
public class AutoComplete5 extends Activity {

	private AutoCompleteTextView mAct;

	private static final String[] emailSuffix = { "@szcomtop.com", "@qq.com",
			"@163.com", "@126.com", "@gmail.com", "@sina.com", "@hotmail.com",
			"@yahoo.cn", "@sohu.com", "@foxmail.com", "@139.com", "@yeah.net",
			"@vip.qq.com", "@vip.sina.com" };

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mAct = new AutoCompleteTextView(getApplicationContext());
		init();
		setContentView(mAct, layoutParams);
	}

	private void init() {
		mAct.setBackgroundColor(Color.BLACK);
		mAct.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				System.out.println("onItemSelected");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		mAct.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("viewviewviewviewview");
			}

		});
		final MyAdatper adapter = new MyAdatper(getApplicationContext());
		mAct.setAdapter(adapter);
		mAct.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				String input = s.toString();
				adapter.mList.clear();
				if (input.length() > 0) {
					int atIndex = input.indexOf('@');
					if (atIndex > 0) {
						// 已经有@
						String mailType = input.substring(atIndex);
						for (int i = 0; i < emailSuffix.length; ++i) {
							if (emailSuffix[i].startsWith(mailType)) {
								adapter.mList.add(input.substring(0, atIndex)
										+ emailSuffix[i]);
							}
						}
					} else {
						for (int i = 0; i < emailSuffix.length; ++i) {
							adapter.mList.add(input + emailSuffix[i]);
						}
					}

				}
				adapter.notifyDataSetChanged();
				mAct.showDropDown();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});
	}

	class MyAdatper extends BaseAdapter implements Filterable {
		List<String> mList;
		private final Context mContext;
		private MyFilter mFilter;

		public MyAdatper(Context context) {
			mContext = context;
			mList = new ArrayList<String>();
		}

		@Override
		public int getCount() {
			return mList == null ? 0 : mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList == null ? null : mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				TextView tv = new TextView(mContext);
				tv.setTextColor(Color.BLACK);
				tv.setTextSize(20);
				convertView = tv;
			}
			TextView txt = (TextView) convertView;
			txt.setText(mList.get(position));
			return txt;
		}

		@Override
		public Filter getFilter() {
			if (mFilter == null) {
				mFilter = new MyFilter();
			}
			return mFilter;
		}

		private class MyFilter extends Filter {

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults results = new FilterResults();
				if (mList == null) {
					mList = new ArrayList<String>();
				}
				results.values = mList;
				results.count = mList.size();
				return results;
			}

			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				if (results.count > 0) {
					notifyDataSetChanged();
				} else {
					notifyDataSetInvalidated();
				}
			}
		}
	}
}
