package com.crs.processor;

import com.crs.config.Constants;
import com.crs.exception.InvalidProcessorException;
import com.crs.utils.JsonUtils;
import com.crs.domain.ObjectToken;

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
        System.out.println(this.trimmedJsonString);

        ObjectToken rootToken = new ObjectToken(Constants.ROOT_OBJECT_KEY, this.trimmedJsonString);

        System.out.println(rootToken);

        return null;
    }
}
