package com.company;

import java.io.File;

public class DepthFirstSearch {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Ivan\\Desktop\\SoftUni\\Data Structures");
        dfs(file, "");
    }

    private static void dfs(File file, String offset) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File currentFile : files) {
                if(currentFile.isDirectory()) {
                    System.out.println(offset + currentFile.getAbsolutePath());
                    dfs(currentFile, offset + "  ");
                } else {
                    System.out.println(offset + currentFile.getAbsolutePath());
                }
            }
        }
    }
}
