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

    private List<ImmutablePair<Integer, Integer>> childTokenPositions = new ArrayList<ImmutablePair<Integer, Integer>>();

    public ObjectToken(String key, String jsonBody) {
        super(key, jsonBody);
        parseChildTokens();
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

    private void parseChildTokens() {
        int jsonBodyLength = this.jsonBody.length();
        int startInd = 1;
        int endInd = -1;

        while (startInd < jsonBodyLength && endInd < jsonBodyLength) {
            Token token = JsonUtils.getInstance().getCurrentToken(this.jsonBody.substring(startInd));
            // "key":body,
            // len(key) + 2 + len(body)
            endInd = token.getJsonBody().length() + token.getKey().length() + 2 + startInd;
            startInd = endInd + 1;

            while (startInd < jsonBodyLength && (jsonBody.charAt(startInd) == ',' || jsonBody.charAt(startInd) == '}')) {
                ++startInd;
            }

            System.out.println(token);
        }

    }

}
