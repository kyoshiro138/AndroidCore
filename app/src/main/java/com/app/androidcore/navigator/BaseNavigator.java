package com.app.androidcore.navigator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public abstract class BaseNavigator implements Navigable {
    protected int mFragmentContainerId;
    protected FragmentManager mFragmentManager;

    public BaseNavigator(int contentFragmentId, FragmentManager fragmentManager) {
        mFragmentContainerId = contentFragmentId;
        mFragmentManager = fragmentManager;
    }

    public void navigateTo(Fragment fragment) {
        if(fragment!=null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.setCustomAnimations(android.support.v7.appcompat.R.anim.abc_fade_in, android.support.v7.appcompat.R.anim.abc_fade_out);
            transaction.replace(mFragmentContainerId, fragment, fragment.getClass().getSimpleName());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void navigateToFirstLevelFragment(Fragment fragment) {
        mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        navigateTo(fragment);
    }

    public final void navigateBack() {
        mFragmentManager.popBackStack();
        reload();
    }

    public final void navigateBack(int count) {
        for (int i = 0; i < count && mFragmentManager.getBackStackEntryCount() - i > 1; i++) {
            mFragmentManager.popBackStack();
        }
        reload();
    }

    public final void navigateBackToFirstLevelFragment() {
        mFragmentManager.popBackStackImmediate(1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        reload();
    }

    private void reload() {
        Fragment fragment = mFragmentManager.findFragmentById(mFragmentContainerId);
        if (fragment != null && fragment instanceof OnBackReloadListener) {
            ((OnBackReloadListener) fragment).onBackReload();
        }
    }

    public interface OnBackReloadListener {
        void onBackReload();
    }
}
