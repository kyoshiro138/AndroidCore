package com.app.androidcore.utils;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

import java.util.Locale;

public class PixelConverter {
    private static final String DEBUG_TAG = "PIXEL_CONVERTER";
    private static final boolean LOG_DEBUG = false;

    private Context mContext;

    public PixelConverter(Context context) {
        mContext = context;
    }

    public float fromDp(float dp) {
        float pixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());

        if (LOG_DEBUG) {
            Log.d(DEBUG_TAG, String.format(Locale.getDefault(), "CONVERT FROM DP [DP:%f] [PIXEL:%f]", dp, pixel));
        }

        return pixel;
    }

    public float fromSp(float sp) {
        float pixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, mContext.getResources().getDisplayMetrics());

        if (LOG_DEBUG) {
            Log.d(DEBUG_TAG, String.format(Locale.getDefault(), "CONVERT FROM SP [SP:%f] [PIXEL:%f]", sp, pixel));
        }

        return pixel;
    }
}
