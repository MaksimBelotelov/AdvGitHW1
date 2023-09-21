package com.example.cw0301.model;

import java.util.Calendar;

public class Donkey extends Pack implements CanSay{
    public Donkey(String name, Calendar birthDate, String commands) {
        super(name, birthDate, commands);
        this.setCapacity(2000);
        this.setSpeed(10);
    }

    @Override
    public void saySomething() {
        System.out.println("Иа-иа-иа!");
    }
}
