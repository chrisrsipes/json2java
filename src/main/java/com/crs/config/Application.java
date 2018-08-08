package com.crs.config;

import com.crs.processor.DefaultProcessor;
import com.crs.utils.WorkspaceUtils;

/**
 * Created by crs on 8/8/18.
 */
public class Application {

    public static void main(String[] args) throws Exception {
        String jsonString = WorkspaceUtils.getInstance().readJSONString("/Users/crs/Desktop/t.json");

        DefaultProcessor defaultProcessor = new DefaultProcessor(jsonString);
        defaultProcessor.call();

    }
}
