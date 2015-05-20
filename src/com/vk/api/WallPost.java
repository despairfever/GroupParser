package com.vk.api;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by Денис on 20.05.2015. Ave furby!
 */
public class WallPost {

    private DateTime date;
    private String text;
    private ArrayList<String> attached;

    public WallPost() {
        attached = new ArrayList<String>();
    }

    public String getDate() {
        return date.toString();
    }

    public String getDays() {
        String day = date.dayOfMonth().getAsShortText();
        String month = date.monthOfYear().getAsShortText();
        String year = date.year().getAsShortText();
        return day+"-"+month+"-"+year;
    }

    public void setDate(String date) {
        this.date = new DateTime(Long.parseLong(date) * 1000);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addItem(String item) {
        attached.add(item);
    }

    public void setAttached(ArrayList<String> attached) {
        this.attached = attached;
    }

    public ArrayList<String> getAttached() {
        return attached;
    }
}
