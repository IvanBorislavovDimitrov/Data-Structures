package com.company;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    public static void main(String[] args) {
        breadthFirstSearch(new File("C:\\Users\\Ivan\\Desktop\\SoftUni\\Data Structures"));
    }

    private static void breadthFirstSearch(File file) {
        Queue<File> files = new LinkedList<>();
        files.offer(file);
        while (! files.isEmpty()) {
            File thisFile = files.poll();
            File[] thisFiles = thisFile.listFiles();

            if (thisFiles != null) {
                for (File currentFile : thisFiles) {
                    if (currentFile.isDirectory()) {
                        System.out.println(currentFile.getAbsolutePath());
                        files.offer(currentFile);
                    } else {
                        System.out.println(repeatWhitespace(2) + currentFile.getAbsolutePath());
                    }
                }
            }
        }
    }

    private static String repeatWhitespace(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
