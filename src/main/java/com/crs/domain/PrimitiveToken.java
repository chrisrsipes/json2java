package com.crs.domain;

import com.crs.enums.TokenType;

/**
 * Created by crs on 8/8/18.
 */
public class PrimitiveToken extends Token {

    private Integer intValue;

    private Long longValue;

    private Double doubleValue;

    private Float floatValue;

    public PrimitiveToken(String key, String jsonBody) {
        super(key, jsonBody);
    }

    public TokenType getTokenType() {
        // @TODO: make this dynamic based on field filled in
        return TokenType.PRIMITIVE;
    }

}
