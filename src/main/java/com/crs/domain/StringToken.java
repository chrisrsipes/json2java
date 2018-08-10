package com.crs.domain;

import com.crs.enums.TokenType;
import com.crs.exception.InvalidTokenException;

/**
 * Created by crs on 8/8/18.
 */
public class StringToken extends Token {

    private String value;

    public StringToken(String key, String jsonBody) {
        super(key, jsonBody);
        this.parseValue();
    }

    public TokenType getTokenType() {
        return TokenType.STRING;
    }

    public String getValue() {
        return value;
    }

    private void parseValue() {
        if (jsonBody.charAt(0) != '"' || jsonBody.charAt(jsonBody.length() - 1) != '"') {
            throw new InvalidTokenException("Expected jsonBody to contain quotations");
        }

        this.value = jsonBody.substring(1, jsonBody.length() - 1);
    }
}
