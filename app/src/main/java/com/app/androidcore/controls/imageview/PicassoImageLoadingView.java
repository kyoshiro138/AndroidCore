package com.app.androidcore.controls.imageview;

import android.content.Context;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;

public class PicassoImageLoadingView extends BaseImageLoadingView {
    public PicassoImageLoadingView(Context context) {
        super(context);
    }

    public PicassoImageLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PicassoImageLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void loadImageFromUrl(String url) {
        Picasso.with(getContext())
                .load(url)
                .into(this);
    }

    @Override
    public void loadImageFromUrl(String url, int placeHolder) {
        Picasso.with(getContext())
                .load(url)
                .placeholder(placeHolder)
                .into(this);
    }
}
