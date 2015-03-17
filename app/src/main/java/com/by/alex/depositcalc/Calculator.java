package com.by.alex.depositcalc;

public  class Calculator {

    public static Float[] calcProfit(float SummBegin, float Percent, int Days){

        float profit, fullsumm, percent;
        Float[] result = new Float[3];

        profit = (SummBegin*Percent*Days)/(365*100);
        fullsumm = SummBegin + profit;
        percent = ((fullsumm - SummBegin)/SummBegin)*100;
        result[0] = percent;
        result[1] = profit;
        result[2] = fullsumm;
        return result;
    }

}
