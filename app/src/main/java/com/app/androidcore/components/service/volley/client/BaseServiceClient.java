package com.app.androidcore.components.service.volley.client;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.app.androidcore.components.service.volley.request.BaseRequest;
import com.app.androidcore.components.service.volley.request.RequestParam;
import com.app.androidcore.components.service.volley.response.BaseResponse;

import java.io.IOException;

public abstract class BaseServiceClient implements Response.ErrorListener, Response.Listener<String> {
    protected Context mContext;
    protected RequestQueue mRequestQueue;
    protected String mUrl;
    protected String mTag;
    private OnServiceResponseListener<String> mListener;

    public BaseServiceClient(Context context, String tag, String url) {
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(context);
        mTag = tag;
        mUrl = url;
    }

    public void executeGet() {
        BaseRequest request = createRequest(Request.Method.GET, mUrl, this, this);
        request.setTag(mTag);

        Log.d(mContext.getPackageName(), String.format("[%s REQUEST] [URL: %s]", mTag, mUrl));

        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    public void executePost(RequestParam param) {
        BaseRequest request = createRequest(Request.Method.POST, mUrl, this, this);
        request.setTag(mTag);

        if (param != null) {
            request.addParam(param);
            Log.d(mContext.getPackageName(), String.format("[%s REQUEST] [URL: %s] [PARAM: %s]", mTag, mUrl, param.getRequestParam().toString()));
        }

        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    public void setServiceResponseListener(OnServiceResponseListener<String> listener) {
        mListener = listener;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(mContext.getPackageName(), String.format("[%s RESPONSE ERROR]", mTag));
        if (mListener != null) {
            mListener.onResponseFailed(mTag, error);
        }
    }

    @Override
    public void onResponse(String responseString) {
        Log.d(mContext.getPackageName(), String.format("[%s RESPONSE: %s]", mTag, responseString));
        try {
            if (mListener != null) {
                BaseResponse response = createResponse(responseString);
                mListener.onResponseSuccess(mTag, response.getResponseString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract BaseResponse createResponse(String responseString) throws IOException;

    protected abstract BaseRequest createRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener);
}
