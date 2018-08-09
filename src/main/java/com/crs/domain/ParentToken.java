package com.crs.domain;

import com.crs.enums.TokenType;
import com.crs.utils.JsonUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crs on 8/8/18.
 */
public abstract class CollectionToken extends Token {

    protected List<ImmutablePair<TokenType, Token>> childTokens = new ArrayList<ImmutablePair<TokenType, Token>>();

    protected List<ImmutablePair<Integer, Integer>> childTokenPositions = new ArrayList<ImmutablePair<Integer, Integer>>();

    protected CollectionToken(String key, String jsonBody) {
        super(key, jsonBody);
        this.key = key;
        this.jsonBody = jsonBody;
    }

    public List<ImmutablePair<TokenType, Token>> getChildTokens() {
        return childTokens;
    }

    protected void parseChildTokens() {
        int jsonBodyLength = this.jsonBody.length();
        int startInd = 1;
        int endInd = -1;

        while (startInd < jsonBodyLength && endInd < jsonBodyLength) {
            Token token = JsonUtils.getInstance().getCurrentToken(this.jsonBody.substring(startInd));

            // 2 characters for quotes around the key, the key itself, and the length of the body
            endInd = token.getJsonBody().length() + token.getKey().length() + 2 + startInd;

            this.childTokens.add(new ImmutablePair<TokenType, Token>(token.getTokenType(), token));
            this.childTokenPositions.add(new ImmutablePair<Integer, Integer>(startInd, endInd));

            startInd = endInd + 1;
            while (startInd < jsonBodyLength && (jsonBody.charAt(startInd) == ',' || jsonBody.charAt(startInd) == '}' || jsonBody.charAt(startInd) == ']')) {
                ++startInd;
            }

        }

    }

}
