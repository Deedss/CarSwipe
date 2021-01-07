package com.example.vroomrr;

import org.json.JSONObject;

public interface ServerCallback {
    void completionHandler(Boolean success, String object);
}
