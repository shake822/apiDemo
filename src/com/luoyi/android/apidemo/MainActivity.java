package com.luoyi.android.apidemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActivityList();
	}

	private ArrayList getActivityList() {
		ArrayList<Map> lst = new ArrayList<Map>();
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_SAMPLE_CODE);
		PackageManager pm = getPackageManager();
		List<ResolveInfo> lstAll = pm.queryIntentActivities(mainIntent, 0);
		System.out.println(lstAll);
		for (ResolveInfo resolveInfo : lstAll) {
			System.out.println(resolveInfo.resolvePackageName + " "
					+ resolveInfo);
		}
		return lst;
	}
}
