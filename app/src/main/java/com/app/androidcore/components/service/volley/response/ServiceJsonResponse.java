package com.app.androidcore.components.service.volley.response;

import java.io.IOException;

public class ServiceJsonResponse<TResponseObject> extends BaseJsonResponse<TResponseObject> {
    public ServiceJsonResponse(String response, TResponseObject responseObject) throws IOException {
        super(response, responseObject);
    }
}
