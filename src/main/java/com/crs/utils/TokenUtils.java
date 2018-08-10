package com.crs.utils;

import com.crs.domain.*;
import com.crs.enums.TokenType;
import com.crs.exception.InvalidTokenException;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crs on 8/8/18.
 */
public class TokenUtils {

    private static TokenUtils instance = new TokenUtils();

    public static TokenUtils getInstance() {
        return instance;
    }

    public void printTokensRecursively(ObjectToken root) {
        printTokensRecursively(root, TokenType.OBJECT,0, "");

    }

    private void printTokensRecursively(Token token, TokenType tokenType, int currentLevel, String prefix) {
        String offset = WorkspaceUtils.repeat("\t", currentLevel);

        switch (tokenType) {
            case OBJECT:
                ObjectToken objectToken = (ObjectToken) token;
                System.out.println(String.format("%s%s[%s] %s", offset, prefix, objectToken.getTokenType().toString(), objectToken.getKey()));

                for (ImmutablePair<TokenType, Token> childTokenPair : objectToken.getChildTokens()) {
                    printTokensRecursively(childTokenPair.getRight(), childTokenPair.getLeft(), currentLevel + 1, "");
                }

                break;
            case LIST:
                ListToken listToken = (ListToken) token;
                System.out.println(String.format("%s%s[%s] %s", offset, prefix, listToken.getTokenType().toString(), listToken.getKey()));

                for (ImmutablePair<TokenType, Token> childTokenPair : listToken.getChildTokens()) {
                    printTokensRecursively(childTokenPair.getRight(), childTokenPair.getLeft(), currentLevel + 1, "- ");
                }

                break;
            case STRING:
                StringToken stringToken = (StringToken) token;
                System.out.println(String.format("%s%s[%s] %s: %s", offset, prefix, stringToken.getTokenType().toString(), stringToken.getKey(), stringToken.getValue()));
                break;
            case PRIMITIVE:
                PrimitiveToken primitiveToken1 = (PrimitiveToken) token;
                System.out.println(String.format("%s%s[%s] %s: %s", offset, prefix, primitiveToken1.getTokenType().toString(), primitiveToken1.getKey(), primitiveToken1.getValue()));
                break;
            case PRIMITIVE_INT:
                PrimitiveToken primitiveToken2 = (PrimitiveToken) token;
                System.out.println(String.format("%s%s[%s] %s: %s", offset, prefix, primitiveToken2.getTokenType().toString(), primitiveToken2.getKey(), primitiveToken2.getValue()));
                break;
            case PRIMITIVE_LONG:
                PrimitiveToken primitiveToken3 = (PrimitiveToken) token;
                System.out.println(String.format("%s%s[%s] %s: %s", offset, prefix, primitiveToken3.getTokenType().toString(), primitiveToken3.getKey(), primitiveToken3.getValue()));
                break;
            case PRIMITIVE_DOUBLE:
                PrimitiveToken primitiveToken4 = (PrimitiveToken) token;
                System.out.println(String.format("%s%s[%s] %s: %s", offset, prefix, primitiveToken4.getTokenType().toString(), primitiveToken4.getKey(), primitiveToken4.getValue()));
                break;
            default:
                System.out.println(String.format("%s%s[%s] %s: %s", offset, prefix, token.getTokenType(), "not implemented"));
                break;


        }


    }
}
