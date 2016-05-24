package com.app.androidcore.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

public class ResizeAnimator extends BaseAnimator implements ValueAnimator.AnimatorUpdateListener {
    private int mViewWidth;
    private int mViewHeight;
    private float mViewYPosition;

    private int mChangeWidth = 0;
    private int mChangeHeight = 0;


    public ResizeAnimator() {
        super();
    }

    public ResizeAnimator(String tag) {
        super(tag);
    }

    @Override
    protected Animator initAnimation() {
        ValueAnimator appearAnimation = ValueAnimator.ofInt(0, 100);
        appearAnimation.setDuration(getDefaultDuration());
        appearAnimation.addUpdateListener(this);
        appearAnimation.setInterpolator(new AccelerateInterpolator());

        return appearAnimation;
    }

    @Override
    public void setAnimationView(View view) {
        super.setAnimationView(view);

        mViewWidth = view.getMeasuredWidth();
        mViewHeight = view.getMeasuredHeight();
        mViewYPosition = view.getY();
    }

    public void setSizeChange(int width, int height) {
        if(mAnimator!=null && !mAnimator.isRunning()) {
            mChangeWidth = width;
            mChangeHeight = height;
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int val = (Integer) animation.getAnimatedValue();
        int changeWidth = mChangeWidth * val / 100;
        int changeHeight = mChangeHeight * val / 100;
        int width = mViewWidth + changeWidth;
        int height = mViewHeight + changeHeight;
        float y = mViewYPosition + ((mViewHeight - height) / 2);

        ViewGroup.LayoutParams layoutParams = mAnimationView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;

        mAnimationView.setLayoutParams(layoutParams);
        mAnimationView.setY(y);
    }
}
