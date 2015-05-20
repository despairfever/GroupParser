package com.vk.api;

import java.io.IOException;

/**
 * Created by Денис on 19.05.2015. Ave furby!
 */
public class Main {

    public static void main(String[] args) {
        int postcount = 100;

        Archiever archiever = new Archiever();
        archiever.setCount(postcount);
        archiever.setOffset(0);
        archiever.setSavePath("groups\\knn\\");
        archiever.setDomain("men_are_not_needed");

        try {
            int maxOffset = 5000;
            int offset = 0;
            int fileIndex = 1;

            while (offset <= maxOffset) {
                archiever.setFileIndex(fileIndex);
                archiever.setOffset(offset);
                archiever.archieve();
                offset += postcount;
                fileIndex++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
