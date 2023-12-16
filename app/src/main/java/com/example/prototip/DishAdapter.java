package com.example.prototip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {
    public DishAdapter(Context context, List<Dish> dishes) {
        super(context, 0, dishes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_dish, parent, false);
        }

        Dish dish = getItem(position);

        TextView dishNameTextView = convertView.findViewById(R.id.dishNameTextView);
        TextView dishPriceTextView = convertView.findViewById(R.id.dishPriceTextView);

        dishNameTextView.setText(dish.getName());
        dishPriceTextView.setText(String.valueOf(dish.getPrice()));

        return convertView;
    }
}