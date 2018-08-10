package com.crs.domain;

import com.crs.enums.TokenType;

/**
 * Created by crs on 8/8/18.
 */
public class PrimitiveToken extends Token {

    private TokenType tokenType = TokenType.PRIMITIVE;

    private Integer intValue;

    private Long longValue;

    private Double doubleValue;

    private Float floatValue;

    public PrimitiveToken(String key, String jsonBody) {
        super(key, jsonBody);
        this.parseValue();
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    private void parseValue() {
        if (this.jsonBody.indexOf(".") != -1) {
            this.doubleValue = Double.valueOf(jsonBody);
            this.tokenType = TokenType.PRIMITIVE_DOUBLE;
        } else {
            try {
                this.intValue = Integer.valueOf(jsonBody);
                this.tokenType = TokenType.PRIMITIVE_INT;
            } catch (Exception e) {
                this.longValue = Long.valueOf(jsonBody);
                this.tokenType = TokenType.PRIMITIVE_LONG;
            }
        }
    }

    public String getValue() {
        String retVal = null;

        switch (this.tokenType) {
            case PRIMITIVE_DOUBLE:
                retVal = String.valueOf(this.doubleValue);
                break;
            case PRIMITIVE_INT:
                retVal = String.valueOf(this.intValue);
                break;
            case PRIMITIVE_LONG:
                retVal = String.valueOf(this.longValue);
        }

        return retVal;
    }

}
