package com.app.androidcore.navigator;

import android.support.v4.app.Fragment;

public interface Navigable {
    void navigateTo(Fragment fragment);

    void navigateBackToFirstLevelFragment();

    void navigateToFirstLevelFragment(Fragment fragment);

    void navigateBack();

    void navigateBack(int count);
}
