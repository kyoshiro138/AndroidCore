package com.app.androidcore.components.service.volley.client;

import android.content.Context;

import com.android.volley.Response;
import com.app.androidcore.components.service.volley.request.BaseRequest;
import com.app.androidcore.components.service.volley.request.ServiceRequest;
import com.app.androidcore.components.service.volley.response.BaseResponse;
import com.app.androidcore.components.service.volley.response.ServiceResponse;

public class ServiceClient extends BaseServiceClient {
    public ServiceClient(Context context, String tag, String url) {
        super(context, tag, url);
    }

    @Override
    protected BaseResponse createResponse(String responseString) {
        return new ServiceResponse(responseString);
    }

    @Override
    protected BaseRequest createRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        return new ServiceRequest(method, url, listener, errorListener);
    }
}
