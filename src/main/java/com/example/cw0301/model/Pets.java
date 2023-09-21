package com.example.cw0301.model;

import java.util.Calendar;

public abstract class Pets extends Animals {
    private double weight;

    Pets(String name, Calendar birthDate, String commands) {
        super(name, birthDate, commands);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
