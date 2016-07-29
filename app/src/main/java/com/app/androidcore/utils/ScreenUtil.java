package com.app.androidcore.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenUtil {
    private DisplayMetrics mDisplayMetrics;

    public ScreenUtil(Activity activity) {
        mDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
    }

    public int getScreenWidth() {
        return mDisplayMetrics.widthPixels;
    }

    public int getScreenHeight() {
        return mDisplayMetrics.heightPixels;
    }
}
