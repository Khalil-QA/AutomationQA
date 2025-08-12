package com.tests.project_name.utils;

import java.io.File;

public class FileUtils {

    /**
     * Gets the absolute path of a file in the project directory.
     *
     * @param relativePath The relative path to the file from the project's root directory.
     * @return The absolute path as a string.
     */
    public static String getAbsolutePath(String relativePath) {
        File file = new File(relativePath);
        return file.getAbsolutePath();
    }
}

