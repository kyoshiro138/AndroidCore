package com.app.androidcore.components.service.volley.response;

public abstract class BaseResponse {
    private String mResponseString;

    public String getResponseString() {
        return mResponseString;
    }

    public BaseResponse(String response) {
        mResponseString = response;
    }
}
