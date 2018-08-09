package com.crs.utils;

import com.crs.enums.TokenType;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crs on 8/8/18.
 */
public class ObjectToken extends Token {

    private List<ImmutablePair<TokenType, Token>> childTokens = new ArrayList<ImmutablePair<TokenType, Token>>();

    private List<String> inferredTypes = new ArrayList<String>();

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
