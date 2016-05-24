package com.app.androidcore.components.service.volley.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;

import java.util.Map;

public class ServiceRequest extends BaseRequest {
    public ServiceRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public ServiceRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        // TODO: Authentication
        return super.getHeaders();
    }
}
