package com.app.androidcore.activity.drawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

import com.app.androidcore.activity.BaseActivity;

public abstract class BaseDrawerActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;

    protected abstract int getDrawerLayoutId();

    protected abstract int getLeftDrawerFragmentContainerId();

    protected abstract int getRightDrawerFragmentContainerId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getDrawerLayoutId() > 0) {
            mDrawerLayout = (DrawerLayout) findViewById(getDrawerLayoutId());
        }
    }

    @Override
    public void onBackPressed() {
        if(isDrawersOpened()) {
            closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    protected void SetLeftDrawerFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getLeftDrawerFragmentContainerId(), fragment, fragment.getClass().getSimpleName());
        transaction.commit();
    }

    protected void SetRightDrawerFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getRightDrawerFragmentContainerId(), fragment, fragment.getClass().getSimpleName());
        transaction.commit();
    }

    public final boolean getDrawerEnabled(int gravity) {
        return mDrawerLayout != null && mDrawerLayout.getDrawerLockMode(gravity) == DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    public final void setDrawerEnabled(int gravity, boolean isEnabled) {
        if (mDrawerLayout != null)
        {
            int lockMode = isEnabled ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
            mDrawerLayout.setDrawerLockMode(lockMode, gravity);
        }
    }

    public final void setDrawersEnabled(boolean isEnabled) {
        setDrawerEnabled(Gravity.START, isEnabled);
        setDrawerEnabled(Gravity.END, isEnabled);
    }

    public final void closeDrawer(int gravity) {
        if (isDrawerOpened(gravity)) {
            mDrawerLayout.closeDrawer(gravity);
        }
    }

    public final void closeDrawers() {
        closeDrawer(Gravity.START);
        closeDrawer(Gravity.END);
    }

    public final boolean isDrawerOpened(int gravity) {
        if (mDrawerLayout != null)
        {
            if (gravity == Gravity.START || gravity == Gravity.END)
            {
                return mDrawerLayout.isDrawerOpen(gravity);
            }
        }
        return false;
    }

    public final boolean isDrawersOpened() {
        return isDrawerOpened(Gravity.START) || isDrawerOpened(Gravity.END);
    }

    public final void setDrawerListener(DrawerLayout.DrawerListener listener) {
        if(mDrawerLayout!=null) {
            if(listener!=null) {
                mDrawerLayout.addDrawerListener(listener);
            } else {
                mDrawerLayout.removeDrawerListener(listener);
            }
        }
    }
}