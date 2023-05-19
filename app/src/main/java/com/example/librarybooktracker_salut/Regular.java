package com.example.librarybooktracker_salut;

public class Regular extends Book{

    public Regular (int numDays){
        super(numDays);
    }

    @Override
    public double calculateTotalPrice() {
        double totalprice = 20 * getDayBorrow();
        return totalprice;
    }
}
