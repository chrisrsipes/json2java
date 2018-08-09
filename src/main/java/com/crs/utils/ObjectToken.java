package com.crs.utils;

import com.crs.enums.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crs on 8/8/18.
 */
public class ObjectToken extends Token {

    private List<String> fields = new ArrayList<String>();

    private List<String> inferredTypes = new ArrayList<String>();

    public ObjectToken() {
    }

    public ObjectToken(String key, String jsonBody) {
        super(key, jsonBody);
    }

    @Override
    public String toString() {
        return String.format(
                "Key: %s, type: %s, body: %s",
                this.key,
                TokenType.OBJECT.toString(),
                this.jsonBody
        );
    }

}
