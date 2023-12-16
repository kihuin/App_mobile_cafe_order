package com.example.prototip;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnReserveTable;
    private Button btnOrderFood;
    private DatabaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReserveTable = findViewById(R.id.button);
        btnOrderFood = findViewById(R.id.button2);
        dbManager = new DatabaseManager(this);
        dbManager.open();

        // Тут проверка занесения данных в таблицу из класса databasemanger
        dbManager.addTablesData();
        dbManager.addAvailableTablesData();
        dbManager.addBlockedTablesData();
        dbManager.addReservationsData();
        dbManager.addDishesData();


        dbManager.close();


        btnReserveTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MajorActivity.class);
                startActivity(intent);

            }
        });

        btnOrderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });
    }
    }