package com.crs.domain;

import com.crs.enums.TokenType;

/**
 * Created by crs on 8/8/18.
 */
public class StringToken extends Token {

    private String value;

    public StringToken(String key, String jsonBody) {
        super(key, jsonBody);
    }

    public TokenType getTokenType() {
        return TokenType.STRING;
    }

}
