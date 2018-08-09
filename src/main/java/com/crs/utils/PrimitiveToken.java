package com.crs.utils;

import java.util.ArrayList;
import java.util.List;

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

}
