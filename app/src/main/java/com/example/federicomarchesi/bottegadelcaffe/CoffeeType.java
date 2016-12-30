package com.example.federicomarchesi.bottegadelcaffe;

import java.util.ArrayList;

/**
 * Created by federico.marchesi on 29/12/2016.
 */

public class CoffeeType {
    private String mCoffeeName;
    private String mCoffeeDescription;
    private boolean mIsMacchiato = false;
    private boolean mIsMacchiatoCon = false;
    private boolean mIsInTazzaGrande = false;
    private long mTableId;


    public CoffeeType(String name, String descr) {
        mCoffeeName = name;
        mCoffeeDescription = descr;
    }
    public CoffeeType(String name) {
        mCoffeeName = name;
    }
    public CoffeeType() {

    }
    public CoffeeType(String name, String descr, boolean isMacchiato, boolean isMacchiatoCon, boolean isInTazzaGrande) {
        mCoffeeName = name;
        mCoffeeDescription = descr;
        mIsMacchiato = isMacchiato;
        mIsMacchiatoCon = isMacchiatoCon;
        mIsInTazzaGrande = isInTazzaGrande;
    }

    public CoffeeType(String name, String descr, int id) {
        mCoffeeName = name;
        mCoffeeDescription = descr;
        mTableId = id;
    }

    long getCoffeeId() {
        return mTableId;
    }

    String getCoffeeName() {
        return mCoffeeName;
    }

    String getCoffeeDescription() {
        return mCoffeeDescription;
    }

    public boolean getIsInTazzaGrande() {
        return mIsInTazzaGrande;
    }

    public boolean getIsMacchiato() {
        return mIsMacchiato;
    }

    public boolean getIsMacchiatoCon() {
        return mIsMacchiatoCon;
    }

    public void setCoffeeName(String coffeeName) {
        this.mCoffeeName = coffeeName;
    }

    public void setIsInTazzaGrande(boolean isInTazzaGrande) {
        this.mIsInTazzaGrande = isInTazzaGrande;
    }

    public void setIsMacchiato(boolean isMacchiato) {
        this.mIsMacchiato = isMacchiato;
    }

    public void setIsMacchiatoCon(boolean isMacchiatoCon) {
        this.mIsMacchiatoCon = isMacchiatoCon;
    }

    public void setTableId(long tableId) {
        this.mTableId = tableId;
    }
}
