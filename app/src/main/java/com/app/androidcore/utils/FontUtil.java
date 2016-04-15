package com.app.androidcore.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class FontUtil {

    private static Hashtable<String, Typeface> mFontCache = new Hashtable<>();

    public static Typeface loadFont(Context context, String fontPath) {
        Typeface tf = mFontCache.get(fontPath);
        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), fontPath);
            } catch (Exception e) {
                e.printStackTrace();
                return Typeface.defaultFromStyle(Typeface.NORMAL);
            }
            mFontCache.put(fontPath, tf);
        }
        return tf;
    }
}
