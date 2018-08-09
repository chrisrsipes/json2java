package com.crs.domain;

import com.crs.enums.TokenType;
import com.crs.utils.JsonUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crs on 8/8/18.
 */
public class ObjectToken extends CollectionToken {

    public ObjectToken(String key, String jsonBody) {
        super(key, jsonBody);
        this.parseChildTokens();
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

    public TokenType getTokenType() {
        return TokenType.OBJECT;
    }

    public List<ImmutablePair<TokenType, Token>> getChildTokens() {
        return childTokens;
    }

}
