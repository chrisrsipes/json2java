package com.crs.domain;

import com.crs.enums.TokenType;
import com.crs.utils.JsonUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crs on 8/8/18.
 */
public class ListToken extends CollectionToken {

    public ListToken(String key, String jsonBody) {
        super(key, jsonBody);
        this.parseChildTokens();
    }

    public TokenType getTokenType() {
        return TokenType.LIST;
    }




}
