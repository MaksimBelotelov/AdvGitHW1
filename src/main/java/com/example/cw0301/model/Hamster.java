package com.example.cw0301.model;

import java.util.Calendar;

public class Hamster extends Pets implements CanSay {

    public Hamster(String name, Calendar birthDate, String commands) {
        super(name, birthDate, commands);
        this.setWeight(0.03);
    }

    @Override
    public void saySomething() {
        System.out.println("Фу-фу-фу");
    }
}
