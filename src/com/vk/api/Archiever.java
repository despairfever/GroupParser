package com.vk.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Денис on 20.05.2015. Ave furby!
 */
public class Archiever {

    private APIRequest req;
    private JSONProcessor proc;
    private int postCount;
    private String savePath;
    private int fileIndex;

    public Archiever() {
        req = new APIRequest();
        req.setDomain("https://api.vk.com/method/");
        req.setMethod("wall.get");
        req.addParam("domain", "durov");
        req.addParam("v", "5.33");
        req.addParam("offset", "0");
        req.addParam("count", "1");
        proc = new JSONProcessor();
        postCount = 1;
        savePath = "";
        fileIndex = 1;
    }

    public void setDomain(String domain) {
        req.addParam("domain", domain);
    }

    public void setOffset(int offset) {
        req.addParam("offset", String.valueOf(offset));
    }

    public void setCount(int count) {
        req.addParam("count", String.valueOf(count));
        postCount = count;
    }

    public void setToken(String token) {
        req.addParam("token", token);
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public void setFileIndex(int fileIndex) {
        this.fileIndex = fileIndex;
    }

    public void archieve() throws IOException {

        System.out.println("Sending request to " + req.getURl());

        ArrayList<WallPost> ar = proc.getPosts(req, postCount);
        String first = ar.get(0).getDays();
        String last = ar.get(ar.size()-1).getDays();
        String filename = savePath + fileIndex + " " + first + "--" + last + ".txt";
        PrintWriter pw = new PrintWriter(filename, "UTF-8");

        for (WallPost wp : ar) {
            pw.println(wp.getDate());
            pw.println(wp.getText());
            pw.println("Attached:");
            ArrayList<String> ph = wp.getAttached();
            for (String photo : ph) {
                pw.println(photo);
            }
            pw.println("-----------------------\n");
        }
        pw.close();
    }

}
