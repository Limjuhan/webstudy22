package com.example.zerobasestudy22;

import java.time.LocalDateTime;

public class HistoryInfo {
    int ID;
    String x;
    String y;
    LocalDateTime checkDate;

    HistoryInfo() {
    }
    HistoryInfo(int ID, String x, String y, LocalDateTime checkDate) {
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.checkDate = checkDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public LocalDateTime getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDateTime checkDate) {
        this.checkDate = checkDate;
    }
}
