package com.app.androidcore.components.service.volley.response;

import java.io.IOException;

public abstract class BaseJsonResponse<TResponseObject> extends BaseResponse {
    private TResponseObject mResponseObject;

    public TResponseObject getResponseObject() {
        return mResponseObject;
    }

    public BaseJsonResponse(String responseString, TResponseObject responseObject) throws IOException {
        super(responseString);
        mResponseObject = responseObject;
    }
}
