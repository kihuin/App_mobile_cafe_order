package com.example.prototip;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
private DishAdapter dishAdapter;
private ListView dishListView;
private DatabaseManager databaseManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
dishListView = findViewById(R.id.dishListView);
        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        List<Dish> dishesList = populateDishList();

        // Создайте экземпляр адаптера DishAdapter
        DishAdapter dishAdapter = new DishAdapter(this, dishesList);

        // Свяжите адаптер с ListView
        ListView dishListView = findViewById(R.id.dishListView);
        dishListView.setAdapter(dishAdapter);

        populateDishList();

    }
    @SuppressLint("Range")
    private  List<Dish> populateDishList() {

        // Получите список блюд из базы данных
        List<Dish> dishes = new ArrayList<>();
        Cursor cursor = databaseManager.getDishes();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int dishId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.DISHES_COLUMN_ID));
                String dishName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DISHES_COLUMN_NAME));
                double dishPrice = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.DISHES_COLUMN_PRICE));

                Dish dish = new Dish(dishId, dishName, dishPrice);
                dishes.add(dish);
            } while (cursor.moveToNext());
        }
        cursor.close();


        return dishes;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Закрытие соединения с базой данных при закрытии активности
        databaseManager.close();
    }
}