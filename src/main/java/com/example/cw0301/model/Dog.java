package com.example.cw0301.model;

import java.util.Calendar;

public class Dog extends Pets implements CanSay {

    public Dog(String name, Calendar birthDate, String commands) {
        super(name, birthDate, commands);
        this.setWeight(10);
    }

    @Override
    public void saySomething() {
        System.out.println("Гав! Гав!");
    }
}
