package com.example.federicomarchesi.bottegadelcaffe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by federico.marchesi on 29/12/2016.
 */

public class CoffeeAdapter extends ArrayAdapter<CoffeeType> {

    private ArrayList<CoffeeType> mCoffeTypes;

    public CoffeeAdapter(Context context, ArrayList<CoffeeType> objects) {
        super(context, 0, objects);

        mCoffeTypes = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.coffee_details, parent, false);
        }

        final CoffeeType currentCoffee = getItem(position);

        TextView txtView = (TextView) listItemView.findViewById(R.id.coffe_name);

        txtView.setText(TextUtils.isEmpty(currentCoffee.getCoffeeName()) ? "" : currentCoffee.getCoffeeName());

        txtView = (TextView) listItemView.findViewById(R.id.coffee_descr);
        txtView.setText(TextUtils.isEmpty(currentCoffee.getCoffeeDescription()) ? "" : currentCoffee.getCoffeeDescription());

        ImageView deleteCoffee = (ImageView) listItemView.findViewById(R.id.delete_coffee_button);

        deleteCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCoffeTypes.remove(position);
                notifyDataSetChanged();
            }
        });

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
