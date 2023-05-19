package com.example.librarybooktracker_salut;

public class Book {
    private int numDays;
    private double totalPrice;


    public Book(int numDays) {
        this.numDays = numDays;
    }

    public double calculateTotalPrice(){
        return totalPrice;
    }

    public int getDayBorrow(){
        return numDays;
    }

}
