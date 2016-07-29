package com.app.androidcore.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class SoftKeyboardUtil {
    private Context mContext;
    private InputMethodManager mInputManager;

    public SoftKeyboardUtil(Context context) {
        mContext = context;
        mInputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public void hideKeyboard() {
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            if (activity.getCurrentFocus() != null) {
                activity.getCurrentFocus().clearFocus();
                mInputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } else {
                mInputManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getRootView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public void showKeyboard() {
        mInputManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
