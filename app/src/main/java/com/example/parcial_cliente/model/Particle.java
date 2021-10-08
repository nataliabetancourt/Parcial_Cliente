package com.example.parcial_cliente.model;

public class Particle {

    private int xPos, yPos, r, g, b, amount;
    private String name;

    public Particle() {}

    public Particle(int xPos, int yPos, int r, int g, int b, int amount, String name) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.r = r;
        this.g = g;
        this.b = b;
        this.amount = amount;
        this.name = name;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
