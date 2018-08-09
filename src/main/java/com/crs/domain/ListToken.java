package com.crs.domain;

import com.crs.enums.TokenType;

/**
 * Created by crs on 8/8/18.
 */
public class ListToken extends ParentToken {

    public ListToken(String key, String jsonBody) {
        super(key, jsonBody);
        this.parseChildTokens();
    }

    public TokenType getTokenType() {
        return TokenType.LIST;
    }




}
