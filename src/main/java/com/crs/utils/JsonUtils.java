package com.crs.utils;

import com.crs.exception.InvalidTokenException;

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

    public Token getCurrentToken(String jsonString) {
        Integer firstQuote = null;
        Integer lastQuote = null;

        if (jsonString == null || jsonString.length() == 0 || jsonString.charAt(0) != '"') {
            throw new InvalidTokenException("First character of stringified token is expected to be double quotes");
        } else {
            firstQuote = 0;
        }

        int currentInd = firstQuote;
        while (lastQuote == null && currentInd + 1< jsonString.length()) {
            // start at 1st character after the firstQuote
            ++currentInd;
            if (jsonString.charAt(currentInd) == '"') {
                if (currentInd > 1 && jsonString.charAt(currentInd - 1) == '\'') {
                    continue;
                } else {
                    lastQuote = currentInd;
                }
            }
        }

        if (lastQuote == null) {
            throw new InvalidTokenException("Missing closing double quote for key");
        }

        String key = jsonString.substring(firstQuote + 1, lastQuote);
        System.out.println(key);

        if (jsonString.charAt(lastQuote + 1) != ':') {
            throw new InvalidTokenException(String.format("Missing colon following key %s", key));
        }


        return null;
    }
}
