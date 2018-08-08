package com.crs.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crs on 8/8/18.
 */
public class JsonUtils {

    private static JsonUtils instance = new JsonUtils();

    public static JsonUtils getInstance() {
        return instance;
    }

    public String removeWhitespace(String jsonString) {
        List<Integer> indsToRemove = new ArrayList();
        boolean doubleQuotesOpen = false;
        int ind;

        for (ind = 0; ind < jsonString.length(); ind++) {
            char current = jsonString.charAt(ind);

            // not in quotes and found whitespace char, mark it to be removed
            if (!doubleQuotesOpen && isWhitespaceChar(current)) {
                indsToRemove.add(ind);
            } else if (current == '"' && doubleQuotesOpen) {
                doubleQuotesOpen = false;
            } else if (current == '"' && !doubleQuotesOpen) {
                doubleQuotesOpen = true;
            }
        }

        StringBuilder jsonStringBuilder = new StringBuilder(jsonString);

        for (ind = indsToRemove.size() - 1; ind >= 0; ind-- ) {
            int indToRemove = indsToRemove.get(ind);
            jsonStringBuilder.deleteCharAt(indToRemove);
        }

        return jsonStringBuilder.toString();
    }

    public boolean isWhitespaceChar(char c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r';
    }
}
