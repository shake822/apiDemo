package com.luoyi.android.apidemo.app;

import java.io.IOException;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.luoyi.android.apidemo.R;

public class SetWallpaperAcitvity extends Activity implements OnClickListener {

	private ImageView mWallpager;

	private WallpaperManager wm;

	private Drawable mDefaultWallpaper;

	final static private int[] mColors = { Color.BLUE, Color.GREEN, Color.RED,
			Color.LTGRAY, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.WHITE };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setwallpaper);
		Button btnChange = (Button) findViewById(R.id.btn_wallpaper_change);
		btnChange.setOnClickListener(this);
		Button btnSet = (Button) findViewById(R.id.btn_wallpaper_set);
		btnSet.setOnClickListener(this);

		wm = WallpaperManager.getInstance(this);
		mDefaultWallpaper = wm.getDrawable();
		mWallpager = (ImageView) findViewById(R.id.iv_wallpager_1);
		mWallpager.setDrawingCacheEnabled(true);
		mWallpager.setImageDrawable(mDefaultWallpaper);
	}

	@Override
	public void onClick(View v) {
		Drawable tmp = mDefaultWallpaper;
		switch (v.getId()) {
		case R.id.btn_wallpaper_change:
			int mColor = (int) Math.floor(Math.random() * mColors.length);
			tmp.setColorFilter(mColors[mColor], PorterDuff.Mode.MULTIPLY);
			mWallpager.setImageDrawable(tmp);
			mWallpager.invalidate();
			break;
		case R.id.btn_wallpaper_set:
			try {
				wm.setBitmap(mWallpager.getDrawingCache());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
		}
	}
}
