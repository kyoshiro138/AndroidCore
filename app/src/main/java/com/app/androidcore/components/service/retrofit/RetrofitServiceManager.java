package com.app.androidcore.components.service.retrofit;

import retrofit2.Retrofit;

public abstract class RetrofitServiceManager<TService> {
    protected TService mService;

    abstract String getBaseUrl();

    public TService getService() {
        return mService;
    }

    public RetrofitServiceManager(Class<TService> serviceClass) {
        mService = createService(serviceClass);
    }

    protected TService createService(Class<TService> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .build();

        return retrofit.create(serviceClass);
    }
}
