package com.app.androidcore.controls.imageview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public abstract class BaseImageLoadingView extends ImageView {
    protected BaseImageLoadingView(Context context) {
        super(context);
    }

    protected BaseImageLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected BaseImageLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void loadImageFromUrl(String url);

    public abstract void loadImageFromUrl(String url, int placeHolder);
}
