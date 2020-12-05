package com.blueboard.knumeet;

public class MeetInfo {

    private String name;
    private int now;
    private int full;

    public MeetInfo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public int getFull() {
        return full;
    }

    public void setFull(int full) {
        this.full = full;
    }
}
