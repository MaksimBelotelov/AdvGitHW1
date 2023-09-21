package com.example.cw0301.model;

import java.util.Calendar;

public class Horse extends Pack implements CanSay {
    public Horse(String name, Calendar birthDate, String commands) {
        super(name, birthDate, commands);
        this.setCapacity(200);
        this.setSpeed(25);
    }

    @Override
    public void saySomething() {
        System.out.println("Игого-игого!");
    }
}
