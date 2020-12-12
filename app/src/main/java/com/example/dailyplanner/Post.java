package com.example.dailyplanner;

public class Post {
    public Post(String sdate, String stime, String description) {
        this.sdate = sdate;
        this.stime = stime;
        this.description = description;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    private String sid;
    private String sdate;

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String stime;

    public Post(String sdate, String stime, String description, String sid) {
        this.sid= sid;
        this.sdate = sdate;
        this.stime = stime;
        this.description = description;
    }

    private String description;

}
