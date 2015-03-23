package com.by.alex.depositcalc;

public  class Calculator {

    static final int PERCENT = 0;
    static final int PROFIT = 1;
    static final int FULLSUMM = 2;


    public static Float[] calcProfit(float SummBegin, float Percent, int Days){

        float profit, fullsumm, percent;
        Float[] result = new Float[3];

        profit = (SummBegin*Percent*Days)/(365*100);
        fullsumm = SummBegin + profit;
        percent = ((fullsumm - SummBegin)/SummBegin)*100;
        result[PERCENT] = percent;
        result[PROFIT] = profit;
        result[FULLSUMM] = fullsumm;
        return result;
    }
//difficult percents
    public static Float[] calcProfit(float SummBegin, float Percent, int Days, int Capitalization){

        Float[] result = new Float[3];

        if (Capitalization==1) {

            float profit, fullsumm, percent;



            fullsumm = (float) (SummBegin *Math.pow(1+Percent/(365*100),Days));
            profit = fullsumm - SummBegin;
            percent = (profit/ SummBegin) * 100;
            result[PERCENT] = percent;
            result[PROFIT] = profit;
            result[FULLSUMM] = fullsumm;


        }

        return result;
    }

}
