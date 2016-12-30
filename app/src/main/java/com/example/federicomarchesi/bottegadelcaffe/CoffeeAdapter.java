package com.example.federicomarchesi.bottegadelcaffe;

import android.content.Context;
import android.icu.lang.UCharacter;
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

import static com.example.federicomarchesi.bottegadelcaffe.R.styleable.Spinner;

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

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.coffee_arrays, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                mCoffeTypes.get(position).setCoffeeName(parent.getItemAtPosition(position).toString());
                mCoffeTypes.get(position).setTableId(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                mCoffeTypes.get(position).setTableId();
            }
        });

        CheckBox isMacchiato = (CheckBox) listItemView.findViewById(R.id.is_macchiato);
        isMacchiato.setChecked(currentCoffee.getIsMacchiato());
        isMacchiato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentCoffee.setIsMacchiato(isChecked);
                notifyDataSetChanged();
            }
        });

        CheckBox isMacchiatoCon = (CheckBox) listItemView.findViewById(R.id.is_macchiato_con);
        isMacchiatoCon.setChecked(currentCoffee.getIsMacchiatoCon());
        isMacchiatoCon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentCoffee.setIsMacchiatoCon(isChecked);
            }
        });

        CheckBox isInTazzaGrande = (CheckBox) listItemView.findViewById(R.id.is_in_tazza_grande);
        isInTazzaGrande.setChecked(currentCoffee.getIsInTazzaGrande());
        isInTazzaGrande.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentCoffee.setIsInTazzaGrande(isChecked);
            }
        });


        //        TextView txtView = (TextView) listItemView.findViewById(R.id.coffe_name);
//
//        txtView.setText(TextUtils.isEmpty(currentCoffee.getCoffeeName()) ? "" : currentCoffee.getCoffeeName());
//
//        txtView = (TextView) listItemView.findViewById(R.id.coffee_descr);
//        txtView.setText(TextUtils.isEmpty(currentCoffee.getCoffeeDescription()) ? "" : currentCoffee.getCoffeeDescription());
//
//        ImageView deleteCoffee = (ImageView) listItemView.findViewById(R.id.delete_coffee_button);
//
//        deleteCoffee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCoffeTypes.remove(position);
//                notifyDataSetChanged();
//            }
//        });

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
