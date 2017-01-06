package com.example.federicomarchesi.bottegadelcaffe;

/**
 * Created by federico.marchesi on 29/12/2016.
 */

public class CoffeeType {
    private String mCoffeeName;
    private String mCoffeeDescription;
    private boolean mIsMacchiato = false;
    private boolean mIsMacchiatoCon = false;
    private boolean mIsInTazzaGrande = false;
    private long mCoffeeTypeId;
    private int mNumberOrdered;


    public CoffeeType(String name, String descr) {
        mCoffeeName = name;
        mCoffeeDescription = descr;
    }

    public CoffeeType(String name) {
        mCoffeeName = name;
    }

    public CoffeeType() {
        mNumberOrdered = 1;
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
        mCoffeeTypeId = id;
    }

    long getCoffeeTypeId() {
        return mCoffeeTypeId;
    }

    void setCoffeeTypeId(long tableId) {
        this.mCoffeeTypeId = tableId;
    }

    String getCoffeeName() {
        return mCoffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.mCoffeeName = coffeeName;
    }

    String getCoffeeDescription() {
        return mCoffeeDescription;
    }

    boolean getIsInTazzaGrande() {
        return mIsInTazzaGrande;
    }

    void setIsInTazzaGrande(boolean isInTazzaGrande) {
        this.mIsInTazzaGrande = isInTazzaGrande;
    }

    boolean getIsMacchiato() {
        return mIsMacchiato;
    }

    void setIsMacchiato(boolean isMacchiato) {
        this.mIsMacchiato = isMacchiato;
    }

    boolean getIsMacchiatoCon() {
        return mIsMacchiatoCon;
    }

    void setIsMacchiatoCon(boolean isMacchiatoCon) {
        this.mIsMacchiatoCon = isMacchiatoCon;
    }

    int getNumberOrdered() {
        return mNumberOrdered;
    }

    void setNumberOrdered(int numberOrdered) {
        this.mNumberOrdered = numberOrdered;
    }

}
