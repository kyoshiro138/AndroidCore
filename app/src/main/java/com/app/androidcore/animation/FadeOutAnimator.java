package com.app.androidcore.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class FadeOutAnimator extends BaseAnimator implements ValueAnimator.AnimatorUpdateListener {
    public FadeOutAnimator() {
        super();
    }

    public FadeOutAnimator(String tag) {
        super(tag);
    }

    @Override
    protected Animator initAnimation() {
        ValueAnimator appearAnimation = ValueAnimator.ofFloat(0.0f, 1.0f);
        appearAnimation.setDuration(getDefaultDuration());
        appearAnimation.addUpdateListener(this);
        appearAnimation.setInterpolator(new AccelerateInterpolator());

        return appearAnimation;
    }

    @Override
    public void setAnimationView(View view) {
        super.setAnimationView(view);

        view.setAlpha(0.0f);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float alpha = (Float) animation.getAnimatedValue();

        mAnimationView.setAlpha(alpha);
    }
}
