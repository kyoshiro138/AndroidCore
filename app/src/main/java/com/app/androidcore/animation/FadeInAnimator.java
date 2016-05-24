package com.app.androidcore.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateInterpolator;

public class FadeInAnimator extends BaseAnimator implements ValueAnimator.AnimatorUpdateListener {
    public FadeInAnimator() {
        super();
    }

    public FadeInAnimator(String tag) {
        super(tag);
    }

    @Override
    protected Animator initAnimation() {
        ValueAnimator appearAnimation = ValueAnimator.ofFloat(1.0f, 0.0f);
        appearAnimation.setDuration(getDefaultDuration());
        appearAnimation.addUpdateListener(this);
        appearAnimation.setInterpolator(new AccelerateInterpolator());

        return appearAnimation;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float alpha = (Float) animation.getAnimatedValue();

        mAnimationView.setAlpha(alpha);
    }
}
