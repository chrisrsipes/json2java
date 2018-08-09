package com.crs.utils;

/**
 * Created by crs on 8/8/18.
 */
public class Token {

    private String key;

    private String jsonBody;

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
