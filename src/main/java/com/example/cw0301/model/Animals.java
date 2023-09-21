package com.example.cw0301.model;

import java.util.Calendar;

public abstract class Animals {
    private String name;
    private Calendar birthDate;
    private String commands;

    Animals(String name, Calendar birthDate, String commands){
        this.name = name;
        this.birthDate = birthDate;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return Integer.toString(birthDate.get(Calendar.YEAR)) + "-"
                + Integer.toString(birthDate.get(Calendar.MONTH)) + "-"
                + Integer.toString(birthDate.get(Calendar.DAY_OF_MONTH));
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }
}
