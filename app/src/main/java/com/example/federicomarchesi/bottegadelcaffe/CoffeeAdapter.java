package com.example.federicomarchesi.bottegadelcaffe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by federico.marchesi on 29/12/2016.
 */

public class CoffeeAdapter extends ArrayAdapter<CoffeeType> {

    private ArrayList<CoffeeType> mCoffeTypes;


    public CoffeeAdapter(Context context, ArrayList<CoffeeType> coffeeArray) {
        super(context, 0, coffeeArray);

        mCoffeTypes = coffeeArray;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.coffe_order_detail, parent, false);
        }

        final CoffeeType currentCoffee = getItem(position);

        Spinner spinner = (Spinner) listItemView.findViewById(R.id.caffe_name_spinner);
        if (convertView == null) {
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.coffee_arrays, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                mCoffeTypes.get(position).setCoffeeName(parent.getItemAtPosition(position).toString());
                currentCoffee.setCoffeeTypeId(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                mCoffeTypes.get(position).setCoffeeTypeId();
            }
        });

        CheckBox isMacchiato = (CheckBox) listItemView.findViewById(R.id.is_macchiato);

        isMacchiato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCoffeTypes.get(position).setIsMacchiato(isChecked);
                notifyDataSetChanged();
            }
        });
        isMacchiato.setChecked(currentCoffee.getIsMacchiato());

        CheckBox isMacchiatoCon = (CheckBox) listItemView.findViewById(R.id.is_macchiato_con);

        isMacchiatoCon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCoffeTypes.get(position).setIsMacchiatoCon(isChecked);
                notifyDataSetChanged();
            }
        });
        isMacchiatoCon.setChecked(currentCoffee.getIsMacchiatoCon());

        CheckBox isInTazzaGrande = (CheckBox) listItemView.findViewById(R.id.is_in_tazza_grande);

        isInTazzaGrande.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCoffeTypes.get(position).setIsInTazzaGrande(isChecked);
                notifyDataSetChanged();
            }
        });
        isInTazzaGrande.setChecked(currentCoffee.getIsInTazzaGrande());

        NumberPickerView numberPickerView = (NumberPickerView)
                listItemView.findViewById(R.id.number_picker);
        numberPickerView.setMinValue(0);
        numberPickerView.setMaxValue(10);


        numberPickerView.setOnValueChangeListener(new NumberPickerView.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPickerView picker, int oldValue, int newValue) {
                mCoffeTypes.get(position).setNumberOrdered(newValue);
            }
        });
        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

    public String getCoffeeOrder() {
        // Recupero Caffè normali
        int totCoffeeNormali = getCoffeeNumberByType(0);
        int totCoffeeInGrande = getCoffeeNumberByType(0, false, false, true);
        int totCoffeeMacchiati = getCoffeeNumberByType(0, true, false, false);
        int totCoffeeMacchiatiCon = getCoffeeNumberByType(0, true, true, false);
        int totAmerica = getCoffeeNumberByType(1);

        // Recupero caffé

        String caffe = totCoffeeNormali + ", ";
        if (totCoffeeMacchiati == 0 && totCoffeeMacchiatiCon > 0) {
            caffe += totCoffeeMacchiatiCon + " macchia con,";
        }
        if (totCoffeeMacchiati > 0 && totCoffeeMacchiatiCon >= 0) {
            caffe += totCoffeeMacchiati + totCoffeeMacchiatiCon + " macchia,";
        }
        if (totCoffeeMacchiati > 0 && totCoffeeMacchiatiCon == 0) {
            caffe += totCoffeeMacchiati + " macchia,";
        }
        caffe += (totCoffeeInGrande > 0 ? totCoffeeInGrande + " in grande, " : "");
        caffe += (totAmerica > 0 ? totAmerica + " america, " : "");

        return caffe;
    }

    private int getCoffeeNumberByType(long coffeeTypeId, boolean isMacchiato,
                                      boolean isMacchiatoCon, boolean isInTazzaGrande) {
        int noOfCoffee = 0;
        for (CoffeeType coffe : mCoffeTypes) {
            if (coffe.getCoffeeTypeId() == coffeeTypeId &&
                    coffe.getIsMacchiato() == isMacchiato &&
                    coffe.getIsMacchiatoCon() == isMacchiatoCon &&
                    coffe.getIsInTazzaGrande() == isInTazzaGrande)
                noOfCoffee += coffe.getNumberOrdered();
        }
        return noOfCoffee;
    }

    private int getCoffeeNumberByType(long coffeeTypeId) {
        int noOfCoffee = 0;
        for (CoffeeType coffe : mCoffeTypes) {
            if (coffe.getCoffeeTypeId() == coffeeTypeId && !coffe.getIsInTazzaGrande())
                noOfCoffee += coffe.getNumberOrdered();
        }
        return noOfCoffee;
    }

}
