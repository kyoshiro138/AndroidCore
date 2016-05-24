package com.app.androidcore.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.View;

public abstract class BaseAnimator implements Animator.AnimatorListener {
    private String mTag;
    private OnAnimationFinishedListener mAnimationEndListener;
    private OnAnimationBeginListener mAnimationBeginListener;

    protected View mAnimationView;
    protected Animator mAnimator;

    public BaseAnimator() {
        mTag = this.getClass().getSimpleName();

        mAnimator = initAnimation();
        if (mAnimator != null) {
            mAnimator.addListener(this);
        }
    }

    public BaseAnimator(String tag) {
        mTag = tag;

        mAnimator = initAnimation();
        if (mAnimator != null) {
            mAnimator.addListener(this);
        }
    }

    protected abstract Animator initAnimation();

    public void setAnimationView(View view) {
        if (mAnimator != null && mAnimator.isRunning()) {
            // Animation is running, not allow to change view
            return;
        }
        mAnimationView = view;
    }

    public void start() {
        if (mAnimationView != null && mAnimator != null && !mAnimator.isRunning()) {
            if (mAnimationBeginListener != null) {
                mAnimationBeginListener.onAnimationBegin(this, mAnimationView);
            }
            mAnimator.start();
        }
    }

    public void setDuration(long milliseconds) {
        if (mAnimator != null) {
            mAnimator.setDuration(milliseconds);
        }
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        if (mAnimator != null) {
            mAnimator.setInterpolator(interpolator);
        }
    }

    public String getTag() {
        return mTag;
    }

    public void setOnAnimationFinishedListener(OnAnimationFinishedListener listener) {
        mAnimationEndListener = listener;
    }

    public void setOnAnimationBeginListener(OnAnimationBeginListener listener) {
        mAnimationBeginListener = listener;
    }

    protected int getDefaultDuration() {
        return 500;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (mAnimationEndListener != null) {
            mAnimationEndListener.onAnimationFinished(this, mAnimationView);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
