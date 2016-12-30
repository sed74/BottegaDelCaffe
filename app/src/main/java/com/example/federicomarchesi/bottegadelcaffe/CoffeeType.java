package com.example.federicomarchesi.bottegadelcaffe;

import java.util.ArrayList;

/**
 * Created by federico.marchesi on 29/12/2016.
 */

public class CoffeeType {
    private String mCoffeeName;
    private String mCoffeeDescription;
    private int mTableId;


    public CoffeeType(String name, String descr) {
        mCoffeeName = name;
        mCoffeeDescription = descr;
    }

    public CoffeeType(String name, String descr, int id) {
        mCoffeeName = name;
        mCoffeeDescription = descr;
        mTableId = id;
    }

    int getCoffeeId() {
        return mTableId;
    }
    String getCoffeeName() {
        return mCoffeeName;
    }

    String getCoffeeDescription() {
        return mCoffeeDescription;
    }
}
