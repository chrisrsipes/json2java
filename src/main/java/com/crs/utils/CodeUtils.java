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
            case LIST:
                ListToken listToken = (ListToken) token;
                variableCreated = getVariableName(listToken.getKey() + "List", nameCounts);
                System.out.println(String.format("List<%s> %s = new ArrayList<%s>();", getClassName(listToken.getKey()), variableCreated, getClassName(listToken.getKey())));

                for (ImmutablePair<TokenType, Token> childTokenPair : listToken.getChildTokens()) {
                    childTokenPair.getRight().setKey(getClassName(listToken.getKey()));
                    String newVariableCreated = generateCodeHelper(childTokenPair.getRight(), childTokenPair.getLeft(), nameCounts);
                    System.out.println(String.format("%s.add(%s);", variableCreated, newVariableCreated));
                }

                break;
            case STRING:
                StringToken stringToken = (StringToken) token;
                variableCreated = getVariableName(stringToken.getKey(), nameCounts);
                System.out.println(String.format("String %s = \"%s\";", variableCreated, stringToken.getValue()));
                break;
            case PRIMITIVE_DOUBLE:
                PrimitiveToken primitiveToken1 = (PrimitiveToken) token;
                variableCreated = getVariableName(primitiveToken1.getKey(), nameCounts);
                System.out.println(String.format("double %s = %s;", variableCreated, primitiveToken1.getValue()));
                break;
            case PRIMITIVE_INT:
                PrimitiveToken primitiveToken2 = (PrimitiveToken) token;
                variableCreated = getVariableName(primitiveToken2.getKey(), nameCounts);
                System.out.println(String.format("int %s = %s;", variableCreated, primitiveToken2.getValue()));
                break;
            case PRIMITIVE_LONG:
                PrimitiveToken primitiveToken3 = (PrimitiveToken) token;
                variableCreated = getVariableName(primitiveToken3.getKey(), nameCounts);
                System.out.println(String.format("long %s = %sl;", variableCreated, primitiveToken3.getValue()));
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
