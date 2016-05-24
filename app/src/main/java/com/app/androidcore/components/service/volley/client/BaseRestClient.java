package com.app.androidcore.components.service.volley.client;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.app.androidcore.components.service.volley.response.BaseJsonResponse;

import java.io.IOException;

public abstract class BaseRestClient<TResponseObject> extends BaseServiceClient {
    private OnServiceResponseListener<TResponseObject> mListener;

    public BaseRestClient(Context context, String tag, String url, OnServiceResponseListener<TResponseObject> listener) {
        super(context, tag, url);
        mListener = listener;
    }

    @Override
    public void onResponse(String responseString) {
        try {
            Log.d(mContext.getPackageName(), String.format("[%s RESPONSE: %s]", mTag, responseString));

            BaseJsonResponse<TResponseObject> response = createResponse(responseString);
            if (mListener != null && response != null) {
                mListener.onResponseSuccess(mTag, response.getResponseObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(mContext.getPackageName(), String.format("[%s PARSE ERROR]", mTag));
            if (mListener != null) {
                mListener.onParseError(mTag, responseString);
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        if (mListener != null) {
            mListener.onResponseFailed(mTag, error);
        }
    }

    protected abstract BaseJsonResponse<TResponseObject> createResponse(String responseString) throws IOException;
}
