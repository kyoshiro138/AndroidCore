package com.app.androidcore.utils;

import android.content.Context;
import android.util.TypedValue;

public class PixelConverter {
    private Context mContext;

    public PixelConverter(Context context) {
        mContext = context;
    }

    public float fromDp(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
    }

    public float fromSp(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, mContext.getResources().getDisplayMetrics());
    }
}
