package com.crs.utils;

import com.crs.enums.TokenType;

import java.util.List;

/**
 * Created by crs on 8/8/18.
 */
public class Token {

    protected String key;

    protected String jsonBody;

    protected Token(String key, String jsonBody) {
        this.key = key;
        this.jsonBody = jsonBody;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }

    @Override
    public String toString() {
        return String.format("key: %s, jsonBody: %s", this.key, this.jsonBody);
    }
}
