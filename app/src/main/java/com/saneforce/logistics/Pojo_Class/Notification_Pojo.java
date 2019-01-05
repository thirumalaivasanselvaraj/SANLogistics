package com.saneforce.logistics.Pojo_Class;

/**
 * Created by nnandroid03user on 20/10/16.
 */

public class Notification_Pojo {


    private String notificationdate, genre, year;

    public Notification_Pojo() {
    }

    public Notification_Pojo(String notificationdate, String genre, String year) {
        this.notificationdate = notificationdate;
        this.genre = genre;
        this.year = year;
    }

    public String getNotificationdate() {
        return notificationdate;
    }

    public void setNotificationdate(String name) {
        this.notificationdate = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
