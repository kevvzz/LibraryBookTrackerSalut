package com.example.librarybooktracker_salut;

public class Premium extends Book{
    public Premium (int numDays){
        super(numDays);
    }

    @Override
    public double calculateTotalPrice() {
        int premprice = 50;
        int borrowdays = getDayBorrow();
        int totalprice = 0;
        if (borrowdays > 7) {
            totalprice = premprice * 7;
            borrowdays = borrowdays - 7;
            premprice = premprice + 25;
        }
        totalprice = totalprice + premprice * borrowdays;
        return totalprice;
    }
}
