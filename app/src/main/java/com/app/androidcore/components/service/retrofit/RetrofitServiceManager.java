package com.app.androidcore.components.service.retrofit;

import android.content.Context;

import retrofit2.Retrofit;

public abstract class RetrofitServiceManager<TService> {
    protected Context mContext;
    protected TService mService;

    abstract String getBaseUrl();

    public TService getService() {
        return mService;
    }

    public RetrofitServiceManager(Context context, Class<TService> serviceClass) {
        mContext = context;
        mService = createService(serviceClass);
    }

    protected TService createService(Class<TService> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .build();

        return retrofit.create(serviceClass);
    }
}
