package com.omarkhan;


public class student {

    private int ID;
    private String name;
    private String password;
    private int highestScore;
    private int attempts;

    public student(int ID, String name, String password, int highestScore, int attempts) {
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.highestScore = highestScore;
        this.attempts = attempts;
    }

    public int getID() { return ID; }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public int getAttempts() {
        return attempts;
    }
}
