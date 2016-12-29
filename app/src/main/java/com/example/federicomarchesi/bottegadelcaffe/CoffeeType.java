package com.example.federicomarchesi.bottegadelcaffe;

import java.util.ArrayList;

/**
 * Created by federico.marchesi on 29/12/2016.
 */

public class CoffeeType {
    private String mCoffeeName;
    private String mCoffeeDescription;


    public CoffeeType(String name, String descr) {
        mCoffeeName = name;
        mCoffeeDescription = descr;
    }

    String getCoffeeName() {
        return mCoffeeName;
    }

    String getCoffeeDescription() {
        return mCoffeeDescription;
    }
}
