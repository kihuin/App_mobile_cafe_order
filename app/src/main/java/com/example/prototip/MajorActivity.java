package com.example.prototip;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MajorActivity extends AppCompatActivity {
    private ListView listViewTables;
    private DatabaseManager databaseManager;
    private TableAdapter tableAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);
        // Инициализация элементов интерфейса
        listViewTables = findViewById(R.id.listViewTables);

        // Инициализация базы данных
        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        // Загрузка данных из базы данных
        List<Table> tableList = loadTablesFromDatabase();

        // Создание адаптера и привязка его к ListView
        tableAdapter = new TableAdapter(this, tableList);
        listViewTables.setAdapter(tableAdapter);

        // Обработка нажатия на элемент списка
        listViewTables.setOnItemClickListener((adapterView, view, position, id) -> {
            Table selectedTable = tableList.get(position);
            int selectedTableId = selectedTable.getId();

            // Передача ID выбранного столика на другую страницу для создания заказа (реализация этой логики зависит от вашего приложения)
            // Здесь вы можете использовать Intent для передачи данных на другую активность
            // Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            // intent.putExtra("selectedTableId", selectedTableId);
            // startActivity(intent);
            Toast.makeText(MajorActivity.this, "Вы выбрали столик с ID: " + selectedTableId, Toast.LENGTH_SHORT).show();
        });


    }

    // Метод для загрузки данных из базы данных
    @SuppressLint("Range")
    private List<Table> loadTablesFromDatabase() {
        List<Table> tableList = new ArrayList<>();

        // Запрос на получение данных из таблицы "Столики"
        Cursor cursor = databaseManager.getTables();

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int tableId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLES_COLUMN_ID));
                int number;
                number = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLES_COLUMN_NUMBER));
                int seats = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLES_COLUMN_SEATS));
                double price = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.TABLES_COLUMN_PRICE));

                // Создание объекта Table и добавление его в список
                Table table = new Table(tableId, number, seats, price);
                tableList.add(table);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return tableList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Закрытие соединения с базой данных при закрытии активности
        databaseManager.close();
    }


}