package com.bowling.edward.bowling;

public class CompletedGame {



    private int averageFrame, finalScore, strikeCount, spareCount, clearCount;
    String date;
    String time;
    String dateTime;

    public CompletedGame(){

    }

    public CompletedGame(int averageFrame, int finalScore, int strikeCount, int spareCount, int clearCount, String date, String time, String dateTime){
        this.averageFrame = averageFrame;
        this.finalScore = finalScore;
        this.strikeCount = strikeCount;
        this.spareCount = spareCount;
        this.clearCount = clearCount;
        this.date = date;
        this.time = time;
        this.dateTime = dateTime;
    }

    public int getAverageFrame() {
        return averageFrame;
    }

    public void setAverageFrame(int averageFrame) {
        this.averageFrame = averageFrame;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public void setStrikeCount(int strikeCount) {
        this.strikeCount = strikeCount;
    }

    public int getSpareCount() {
        return spareCount;
    }

    public void setSpareCount(int spareCount) {
        this.spareCount = spareCount;
    }

    public int getClearCount() {
        return clearCount;
    }

    public void setClearCount(int clearCount) {
        this.clearCount = clearCount;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


}
