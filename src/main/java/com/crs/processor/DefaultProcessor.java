package com.crs.processor;

import com.crs.config.Constants;
import com.crs.domain.*;
import com.crs.enums.TokenType;
import com.crs.exception.InvalidProcessorException;
import com.crs.utils.CodeUtils;
import com.crs.utils.JsonUtils;
import com.crs.utils.TokenUtils;
import com.crs.utils.WorkspaceUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.concurrent.Callable;

/**
 * Created by crs on 8/8/18.
 */
public class DefaultProcessor implements Callable {

    private String jsonString;

    private String trimmedJsonString;

    public DefaultProcessor(String jsonString) {

        if (jsonString == null) {
            throw new InvalidProcessorException();
        }

        this.jsonString = jsonString;
    }

    public Object call() throws Exception {

        this.trimmedJsonString = JsonUtils.getInstance().removeWhitespace(this.jsonString);

        ObjectToken rootToken = new ObjectToken(Constants.ROOT_OBJECT_KEY, this.trimmedJsonString);

        System.out.println("Printing tokens recursively");
        TokenUtils.getInstance().printTokensRecursively(rootToken);
        CodeUtils.getInstance().generateCode(rootToken);

        return null;
    }


}
