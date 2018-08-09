package com.crs.utils;

/**
 * Created by crs on 8/8/18.
 */
public abstract class Token {

    protected String key;

    protected String jsonBody;

    public Token() {

    }

    public Token(String key, String jsonBody) {
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
}
