package com.example.cw0301.model;

import java.util.Calendar;

public class Cat extends Pets implements CanSay{

    public Cat(String name, Calendar birthDate, String commands) {
        super(name, birthDate, commands);
        this.setWeight(5);
    }

    @Override
    public void saySomething() {
        System.out.println("Мяу! Мяу!");
    }
}
