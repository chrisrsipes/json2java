package com.crs.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by crs on 8/8/18.
 */
public class WorkspaceUtils {

    private static WorkspaceUtils instance = new WorkspaceUtils();

    public static WorkspaceUtils getInstance() {
        return instance;
    }

    public String readJSONString(String stringPath) {
        String result = null;
        Path path = Paths.get(stringPath);

        try {
            List<String> resultLines = Files.readAllLines(path);
            result = String.join("\n", resultLines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
