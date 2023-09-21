package com.example.cw0301.controller;


public class Counter implements AutoCloseable {
    private int i;

    public Counter() {
        this.i = 0;
    }

    public void add() {
        i++;
    }

    public int getI() {
        return i;
    }

    @Override
    public void close() throws Exception {

    }
}
