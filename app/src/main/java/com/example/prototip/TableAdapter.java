package com.example.prototip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TableAdapter extends BaseAdapter {

    private Context context;
    private List<Table> tableList;

    public TableAdapter(Context context, List<Table> tableList) {
        this.context = context;
        this.tableList = tableList;
    }

    @Override
    public int getCount() {
        return tableList.size();
    }

    @Override
    public Object getItem(int position) {
        return tableList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_table, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textViewTableNumber = convertView.findViewById(R.id.textViewTableNumber);
            viewHolder.textViewSeats = convertView.findViewById(R.id.textViewSeats);
            viewHolder.textViewPrice = convertView.findViewById(R.id.textViewPrice);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Table table = tableList.get(position);

        viewHolder.textViewTableNumber.setText("Номер столика: " + table.getNumber());
        viewHolder.textViewSeats.setText("Количество мест: " + table.getSeats());
        viewHolder.textViewPrice.setText("Цена: $" + table.getPrice());

        return convertView;
    }

    private static class ViewHolder {
        TextView textViewTableNumber;
        TextView textViewSeats;
        TextView textViewPrice;
    }
}