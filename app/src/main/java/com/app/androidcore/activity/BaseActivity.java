package com.app.androidcore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by tuuphungd on 4/14/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private boolean mExitAppOnBack = false;
    private int mExitAppOnBackDelay = 2000;

    protected abstract int getActivityLayoutResId();

    protected abstract int getFragmentContainerId();

    protected final void setExitAppOnBack(boolean enabled) {
        mExitAppOnBack = enabled;
    }

    protected final void setExitAppOnBackDelay(int millisecond) {
        mExitAppOnBackDelay = millisecond;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutResId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initActivity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fragment fragment = getSupportFragmentManager().findFragmentById(getFragmentContainerId());
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            if (mExitAppOnBack) {
                finish();
            } else {
                mExitAppOnBack = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mExitAppOnBack = false;
                    }
                }, mExitAppOnBackDelay);
            }
        }
    }

    protected void initActivity() {
    }

    protected void setContentFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(getFragmentContainerId(), fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
