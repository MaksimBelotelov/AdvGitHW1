package com.example.cw0301.model;

import java.util.Calendar;

public class Camel extends Pack implements CanSay{

    public Camel(String name, Calendar birthDate, String commands) {
        super(name, birthDate, commands);
        this.setCapacity(180);
        this.setSpeed(5);
    }

    @Override
    public void saySomething() {
        System.out.println("Я верблюд!");
    }
}
