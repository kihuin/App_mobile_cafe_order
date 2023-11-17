package com.example.prototip;

public class Table {
    private int id;
    private int number;
    private int seats;
    private double price;

    public Table(int id, int number, int seats, double price) {
        this.id = id;
        this.number = number;
        this.seats = seats;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getSeats() {
        return seats;
    }

    public double getPrice() {
        return price;
    }
}