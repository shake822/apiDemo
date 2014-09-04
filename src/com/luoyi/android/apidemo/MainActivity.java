package com.luoyi.android.apidemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {

	private static final String INTENT_PATH = "intent_path";

	private static final String LABEL_SPLITE = "/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String path = intent.getStringExtra(INTENT_PATH);
		if (path != null && path.length() > 0) {
			setTitle(path);
			// getActionBar().setHomeButtonEnabled(true);
			// getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		setListAdapter(new SimpleAdapter(this, getActivityList(path),
				R.layout.activity_list_1, new String[] { "title" },
				new int[] { R.id.tv_title }));
	}

	/**
	 * 获取列表的数据
	 * 
	 * @param prefix
	 * @return
	 */
	private ArrayList<HashMap<String, Object>> getActivityList(String prefix) {
		ArrayList<HashMap<String, Object>> lst = new ArrayList<HashMap<String, Object>>();
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory("android.intent.category.shake");
		PackageManager pm = getPackageManager();
		List<ResolveInfo> lstAll = pm.queryIntentActivities(mainIntent, 0);
		String[] prefixPath;
		if (prefix == null || prefix.length() == 0) {
			prefixPath = null;
		} else {
			prefixPath = prefix.split(LABEL_SPLITE);
		}
		Map<String, Boolean> entries = new HashMap<String, Boolean>();
		for (ResolveInfo resolveInfo : lstAll) {
			CharSequence labelSeq = resolveInfo.loadLabel(pm);
			String label = labelSeq == null ? resolveInfo.activityInfo.name
					: labelSeq.toString();
			String[] labelPath = label.split(LABEL_SPLITE);
			String nextLabel = prefixPath == null ? labelPath[0]
					: labelPath[prefixPath.length];
			if (prefixPath == null || label.startsWith(prefix)) {
				if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
					// 真实的Activity
					addItem(lst,
							nextLabel,
							activityIntent(
									resolveInfo.activityInfo.packageName,
									resolveInfo.activityInfo.name));
				} else {
					if (entries.get(nextLabel) == null) {
						addItem(lst, nextLabel,
								browseIntent(prefixPath == null ? nextLabel
										: prefix + LABEL_SPLITE + nextLabel));
						entries.put(nextLabel, true);
					}

				}
			}
		}
		return lst;
	}

	/**
	 * 添加列表数据
	 * 
	 * @param data
	 * @param name
	 * @param intent
	 */
	protected void addItem(ArrayList<HashMap<String, Object>> data,
			String name, Intent intent) {
		HashMap<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}

	/**
	 * 真实的
	 * 
	 * @param pkg
	 * @param componentName
	 * @return
	 */

	protected Intent activityIntent(String pkg, String componentName) {
		Intent result = new Intent();
		result.setClassName(pkg, componentName);
		return result;
	}

	/**
	 * 显示列表的
	 * 
	 * @param path
	 * @return
	 */
	protected Intent browseIntent(String path) {
		Intent result = new Intent();
		result.setClass(this, MainActivity.class);
		result.putExtra(INTENT_PATH, path);
		return result;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		HashMap<String, Object> map = (HashMap<String, Object>) l
				.getItemAtPosition(position);
		Intent intent = (Intent) map.get("intent");
		startActivity(intent);
	}
}
