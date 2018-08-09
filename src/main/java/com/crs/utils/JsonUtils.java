package com.crs.utils;

import com.crs.enums.TokenType;
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

    public TokenType getTokenType(char c) {
        TokenType type;

        switch(c) {
            case '{':
                type = TokenType.OBJECT;
                break;
            case '[':
                type = TokenType.LIST;
                break;
            case '"':
                type = TokenType.STRING;
                break;
            default:
                type = Character.isDigit(c) ? TokenType.PRIMITIVE : TokenType.UNKNOWN;
        }

        return type;
    }

    public Token getCurrentToken(String jsonString) {
        Integer firstQuote = null;
        Integer lastQuote = null;

        if (jsonString == null || jsonString.length() == 0 || jsonString.charAt(0) != '"') {
            throw new InvalidTokenException("First character of stringified token is expected to be double quotes, found " + jsonString.charAt(0));
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

        if (jsonString.charAt(lastQuote + 1) != ':') {
            throw new InvalidTokenException(String.format("Missing colon following key %s", key));
        }

        int bodyStart = lastQuote + 2;
        TokenType tokenType = getTokenType(jsonString.charAt(bodyStart));

        int bodyEnd = getTokenBodyEnd(jsonString, bodyStart, tokenType);

        Token token = null;

        // capture the surrounding parentheses/quotes/braces if applicable
        switch (tokenType) {
            case OBJECT:
                token = new ObjectToken(key, jsonString.substring(bodyStart, bodyEnd + 1));
                break;
            case LIST:
                token = new ListToken(key, jsonString.substring(bodyStart, bodyEnd + 1));
                break;
            case STRING:
                token = new StringToken(key, jsonString.substring(bodyStart, bodyEnd + 1));
                break;
            case PRIMITIVE:
                token = new PrimitiveToken(key, jsonString.substring(bodyStart, bodyEnd));
                break;
        }

//        System.out.println(token);

        return token;
    }

    public int getTokenBodyEnd(String jsonString, int startInd, TokenType type) {
        int endInd;

        switch (type) {
            case OBJECT:
                endInd = getObjectTokenBodyEnd(jsonString, startInd);
                break;
            case LIST:
                endInd = getListTokenBodyEnd(jsonString, startInd);
                break;
            case STRING:
                endInd = getStringTokenBodyEnd(jsonString, startInd);
                break;
            case PRIMITIVE:
                endInd = getPrimitiveTokenBodyEnd(jsonString, startInd);
                break;
            default:
                endInd = -1;
        }

        return endInd;
    }

    public int getObjectTokenBodyEnd(String jsonString, int startInd) {
        int strLen = jsonString.length();

        // should be able to look for at least one character ahead for closing brace
        if (startInd + 1 >= strLen) {
            throw new InvalidTokenException("Reached end of file trying to find closing curly brace");
        }

        if (jsonString.charAt(startInd) != '{') {
            throw new InvalidTokenException("Expected start index to be `{` for object tokens");
        }

        boolean doubleQuotesOpen = false;
        int endInd = -1;

        for (int ind = startInd + 1; ind < strLen; ind++) {
            if (!doubleQuotesOpen && jsonString.charAt(ind) == '}') {
                endInd = ind;
                break;
            } else if (!doubleQuotesOpen && jsonString.charAt(ind) == '"') {
                doubleQuotesOpen = true;
            } else if (doubleQuotesOpen && jsonString.charAt(ind) == '"') {
                doubleQuotesOpen = false;
            }

        }

        return endInd;
    }

    public int getListTokenBodyEnd(String jsonString, int startInd) {
        int strLen = jsonString.length();

        // should be able to look for at least one character ahead for closing brace
        if (startInd + 1 >= strLen) {
            throw new InvalidTokenException("Reached end of file trying to find closing square bracket");
        }

        if (jsonString.charAt(startInd) != '[') {
            throw new InvalidTokenException("Expected start index to be `[` for list tokens");
        }

        boolean doubleQuotesOpen = false;
        int endInd = -1;

        for (int ind = startInd + 1; ind < strLen; ind++) {
            if (!doubleQuotesOpen && jsonString.charAt(ind) == ']') {
                endInd = ind;
                break;
            } else if (!doubleQuotesOpen && jsonString.charAt(ind) == '"') {
                doubleQuotesOpen = true;
            } else if (doubleQuotesOpen && jsonString.charAt(ind) == '"') {
                doubleQuotesOpen = false;
            }

        }

        return endInd;
    }

    public int getStringTokenBodyEnd(String jsonString, int startInd) {
        int strLen = jsonString.length();

        // should be able to look for at least one character ahead for closing quote
        if (startInd + 1 >= strLen) {
            throw new InvalidTokenException("Reached end of file trying to find closing double quotes");
        }

        if (jsonString.charAt(startInd) != '"') {
            throw new InvalidTokenException("Expected start index to be `\"` for string tokens");
        }

        int endInd = -1;

        for (int ind = startInd + 1; ind < strLen; ind++) {
            if (jsonString.charAt(ind) == '"') {
                if (ind > 1 && jsonString.charAt(ind - 1) == '\\') {
                    continue;
                } else {
                    endInd = ind;
                    break;
                }
            }
        }

        return endInd;
    }

    public int getPrimitiveTokenBodyEnd(String jsonString, int startInd) {
        int strLen = jsonString.length();

        boolean decimalFound = false;
        int endInd = -1;

        for (int ind = startInd; ind < strLen; ind++) {
            if (Character.isDigit(jsonString.charAt(ind))) {
                continue;
            } else if (jsonString.charAt(ind) == '.') {
                if (decimalFound) {
                    throw new InvalidTokenException("Error parsing primitive value");
                } else {
                    decimalFound = true;
                }
            } else {
                endInd = ind;
                break;
            }
        }

        return endInd;
    }
}
