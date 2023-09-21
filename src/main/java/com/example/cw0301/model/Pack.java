package com.example.cw0301.model;

import java.util.Calendar;

public class Pack extends Animals {
    private double capacity;
    private double speed;

    public Pack(String name, Calendar birthDate, String commands) {
        super(name, birthDate, commands);
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
