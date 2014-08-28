/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.luoyi.android.apidemo.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luoyi.android.apidemo.R;
import com.luoyi.android.apidemo.bean.Person;

/**
 * FIXME 类注释信息
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @createDate Aug 27, 2014
 */
public class AutoComplete4 extends Activity {

	private List<Person> persons;

	private AutoCompleteTextView act;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		act = new AutoCompleteTextView(this);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		act.setHint("输入电话号码");
		persons = getPerson();
		ArrayAdapter<Person> adapter = new PersonAdapter<Person>(this,
				R.layout.list_text_item, new ArrayList());
		act.setCompletionHint("CompletionHint");
		act.setAdapter(adapter);
		act.setThreshold(1);
		addContentView(act, layoutParams);
	}

	private class PersonAdapter<T> extends ArrayAdapter<Person> {

		private PersonFilter mFilter;

		/**
		 * 构造函数
		 * 
		 * @param context
		 * @param textViewResourceId
		 */
		public PersonAdapter(Context context, int textViewResourceId,
				List<Person> objects) {
			super(context, textViewResourceId, objects);
		}

		/**
		 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
		 *      android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Person p = getItem(position);
			LayoutInflater inflater = LayoutInflater
					.from(getApplicationContext());
			View personItemView = inflater.inflate(R.layout.person_item, null);
			((TextView) personItemView.findViewById(R.id.person_item_name))
					.setText(p.getName());
			((TextView) personItemView.findViewById(R.id.person_item_tel))
					.setText(p.getTel());
			personItemView.setTag(p);
			return personItemView;
		}

		/**
		 * @see android.widget.Filterable#getFilter()
		 */
		@Override
		public Filter getFilter() {
			if (mFilter == null) {
				mFilter = new PersonFilter();
			}
			return mFilter;
		}

		private class PersonFilter extends Filter {

			/**
			 * @see android.widget.Filter#performFiltering(java.lang.CharSequence)
			 */
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults results = new FilterResults();
				if (constraint == null) {
					results.values = persons;
					results.count = persons.size();
					return results;
				}

				List<Person> resultPerson = new ArrayList<Person>();
				for (Person p : persons) {
					if (p.getName().startsWith(constraint.toString())
							|| p.getTel().startsWith(constraint.toString())) {
						resultPerson.add(p);
					}
				}

				results.values = resultPerson;
				results.count = resultPerson.size();
				return results;
			}

			/**
			 * @see android.widget.Filter#publishResults(java.lang.CharSequence,
			 *      android.widget.Filter.FilterResults)
			 */
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				if (results.count > 0) {
					clear();
					addAll((List<Person>) results.values);
					notifyDataSetChanged();
				} else {
					notifyDataSetInvalidated();
				}
			}

			/**
			 * @see android.widget.Filter#convertResultToString(java.lang.Object)
			 */
			@Override
			public CharSequence convertResultToString(Object resultValue) {
				Person p = (Person) resultValue;
				return p.getName();
			}

		}

	}

	private List<Person> getPerson() {
		List<Person> users = new ArrayList<Person>();
		HashMap<String, String> personTel = new HashMap<String, String>();
		Cursor telC = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		if (telC.moveToFirst()) {
			int idColumn = telC
					.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
			int telColumn = telC
					.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
			do {
				String contactId = telC.getString(idColumn);
				String number = telC.getString(telColumn);
				if (!personTel.containsKey(contactId)) {
					personTel.put(contactId, number);
				}
			} while (telC.moveToNext());
		}
		telC.close();

		Cursor c = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null,
				ContactsContract.Contacts.DISPLAY_NAME);
		if (c.moveToFirst()) {
			int idColumn = c.getColumnIndex(ContactsContract.Contacts._ID);
			int displayNameColumn = c
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
			do {
				Person person = new Person();
				String contactId = c.getString(idColumn);
				person.setName(c.getString(displayNameColumn));
				person.setTel(personTel.get(contactId));
				users.add(person);
			} while (c.moveToNext());
		}
		c.close();
		return users;
	}
}
