package com.by.alex.depositcalc;

public  class Calculator {

    public static int calcProfit(int SummBegin, int Percent, int Days){

        int profit;

        profit = SummBegin + (SummBegin+Percent+Days)/(365*100);
        return profit;
    }

}
