package com.evan.etcweb.entities;

/**
 * Created by Evan on 2016-09-19.
 */
public class PrayDetail {

    String name;
    String date;
    String message;
    int totalPrayers;

    public PrayDetail(String name, String date, String message, int totalPrayers) {
        this.name = name;
        this.date = date;
        this.message = message;
        this.totalPrayers = totalPrayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalPrayers() {
        return totalPrayers;
    }

    public void setTotalPrayers(int totalPrayers) {
        this.totalPrayers = totalPrayers;
    }
}
