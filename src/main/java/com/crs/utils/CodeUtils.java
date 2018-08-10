package com.crs.utils;

import com.crs.domain.*;
import com.crs.enums.TokenType;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;

/**
 * Created by crs on 8/8/18.
 */
public class CodeUtils {

    private static CodeUtils instance = new CodeUtils();

    public static CodeUtils getInstance() {
        return instance;
    }

    public void generateCode(ObjectToken root) {
        HashMap<String, Integer> nameCounts = new HashMap<String, Integer>();
        this.generateCodeHelper(root, TokenType.OBJECT, nameCounts);
    }

    private String generateCodeHelper(Token token, TokenType tokenType, HashMap<String, Integer> nameCounts) {

        String variableCreated = null;

        switch (tokenType) {
            case OBJECT:
                ObjectToken objectToken = (ObjectToken) token;
                variableCreated = getVariableName(objectToken.getKey(), nameCounts);
                System.out.println(String.format("%s %s = new %s();", getClassName(objectToken.getKey()), variableCreated, getClassName(objectToken.getKey())));

                for (ImmutablePair<TokenType, Token> childTokenPair : objectToken.getChildTokens()) {
                    String newVariableCreated = generateCodeHelper(childTokenPair.getRight(), childTokenPair.getLeft(), nameCounts);
                    System.out.println(String.format("%s.set%s(%s);", variableCreated, getClassName(childTokenPair.getRight().getKey()), newVariableCreated));
                }
                break;
        }

        return variableCreated;
    }

    private String getClassName(String baseName) {
        return Character.toUpperCase(baseName.charAt(0)) + baseName.substring(1);
    }

    private String getVariableName(String baseName, HashMap<String, Integer> nameCounts) {
        if (nameCounts.get(baseName) == null) {
            nameCounts.put(baseName, 0);
        }

        int appendValue = nameCounts.get(baseName);
        nameCounts.put(baseName, appendValue + 1);
        return Character.toLowerCase(baseName.charAt(0)) + baseName.substring(1) + appendValue;
    }
}
