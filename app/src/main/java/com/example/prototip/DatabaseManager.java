package com.example.prototip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    // Метод для добавления столика в таблицу "Столики"
    public void addTable(int number, int seats, double price) {
        ContentValues values = new ContentValues();
        values.put("number", number);
        values.put("seats", seats);
        values.put("price", price);
        database.insert("tables", null, values);
    }

    // Метод для добавления доступного столика
    public void addAvailableTable(int tableId, String dateTime) {
        ContentValues values = new ContentValues();
        values.put("table_id", tableId);
        values.put("date_time", dateTime);
        database.insert("available_tables", null, values);
    }

    // Метод для добавления заблокированного столика
    public void addBlockedTable(int tableId, String dateTime) {
        ContentValues values = new ContentValues();
        values.put("table_id", tableId);
        values.put("date_time", dateTime);
        database.insert("blocked_tables", null, values);
    }

    // Метод для добавления бронирования столика
    public void addReservation(int tableId, String dateTime, String orderCode, String customerName, String customerNumber) {
        ContentValues values = new ContentValues();
        values.put("table_id", tableId);
        values.put("date_time", dateTime);
        values.put("order_code", orderCode);
        values.put("customer_name", customerName);
        values.put("customer_number", customerNumber);
        database.insert("reservations", null, values);
    }

    public Cursor getTables() {
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_TABLES_NAME;
        return database.rawQuery(query, null);
    }

    // Метод для добавления столика в таблицу "Столики"
    public void addTablesData() {
        addTable(1, 4, 10.0);
        addTable(2, 6, 15.0);
        addTable(3, 2, 8.0);
    }

    // Метод для добавления данных в таблицу "Доступные столики"
    public void addAvailableTablesData() {
        addAvailableTable(1, "2023-11-18 16:00:00");
        addAvailableTable(2, "2023-11-18 18:30:00");
        addAvailableTable(3, "2023-11-19 12:00:00");
    }

    // Метод для добавления данных в таблицу "Заблокированные столики"
    public void addBlockedTablesData() {
        addBlockedTable(1, "2023-11-18 15:30:00");
        addBlockedTable(2, "2023-11-19 19:00:00");
        addBlockedTable(3, "2023-11-20 14:30:00");
    }

    // Метод для добавления данных в таблицу "Бронирование"
    public void addReservationsData() {
        addReservation(1, "2023-11-18 17:30:00", "ABC123", "John Doe", "1234567890");
        addReservation(2, "2023-11-19 20:00:00", "DEF456", "Jane Smith", "9876543210");
        addReservation(3, "2023-11-20 16:30:00", "GHI789", "Alex Johnson", "0123456789");
    }
}