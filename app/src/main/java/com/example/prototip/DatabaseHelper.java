package com.example.prototip;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "restaurant.db";
    public static final int DATABASE_VERSION = 1;

    // Таблица "Столики"
    public static final String TABLE_TABLES_NAME = "tables";
    public static final String TABLES_COLUMN_ID = "id";
    public static final String TABLES_COLUMN_NUMBER = "number";
    public static final String TABLES_COLUMN_SEATS = "seats";
    public static final String TABLES_COLUMN_PRICE = "price";

    // Таблица "Доступные столики"
    public static final String TABLE_AVAILABLE_TABLES = "available_tables";
    public static final String AVAILABLE_TABLES_COLUMN_ID = "id";
    public static final String AVAILABLE_TABLES_COLUMN_TABLE_ID = "table_id";
    public static final String AVAILABLE_TABLES_COLUMN_DATE_TIME = "date_time";

    // Таблица "Заблокированные столики"
    public static final String TABLE_BLOCKED_TABLES = "blocked_tables";
    public static final String BLOCKED_TABLES_COLUMN_ID = "id";
    public static final String BLOCKED_TABLES_COLUMN_TABLE_ID = "table_id";
    public static final String BLOCKED_TABLES_COLUMN_DATE_TIME = "date_time";

    // Таблица "Бронирование"
    public static final String TABLE_RESERVATIONS = "reservations";
    public static final String RESERVATIONS_COLUMN_ID = "id";
    public static final String RESERVATIONS_COLUMN_TABLE_ID = "table_id";
    public static final String RESERVATIONS_COLUMN_DATE_TIME = "date_time";
    public static final String RESERVATIONS_COLUMN_ORDER_CODE = "order_code";
    public static final String RESERVATIONS_COLUMN_CUSTOMER_NAME = "customer_name";
    public static final String RESERVATIONS_COLUMN_CUSTOMER_NUMBER = "customer_number";

    // Таблица "Блюдо"
    public static final String TABLE_DISHES = "dishes";
    public static final String DISHES_COLUMN_ID = "dishes_id";
    public static final String DISHES_COLUMN_NAME = "name_dishes";
    public static final String DISHES_COLUMN_PRICE = "price_dishes";


    // Таблица "Заказ"
    public static final String TABLE_ORDERS = "orders";
    public static final String ORDERS_COLUMN_ID = "orders_id";
    public static final String RESERVATIONS_COLUMN_ORDER_CODES = "order_code";
    public static final String ORDERS_COLUMN_DATE = "order_date";
    public static final String ORDERS_COLUMN_PHONE = "order_phone";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы "Столики"
        String createTablesTable = "CREATE TABLE " + TABLE_TABLES_NAME + " (" +
                TABLES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLES_COLUMN_NUMBER + " INTEGER, " +
                TABLES_COLUMN_SEATS + " INTEGER, " +
                TABLES_COLUMN_PRICE + " REAL)";

        // Создание таблицы "Доступные столики"
        String createAvailableTablesTable = "CREATE TABLE " + TABLE_AVAILABLE_TABLES + " (" +
                AVAILABLE_TABLES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AVAILABLE_TABLES_COLUMN_TABLE_ID + " INTEGER, " +
                AVAILABLE_TABLES_COLUMN_DATE_TIME + " TEXT)";

        // Создание таблицы "Заблокированные столики"
        String createBlockedTablesTable = "CREATE TABLE " + TABLE_BLOCKED_TABLES + " (" +
                BLOCKED_TABLES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BLOCKED_TABLES_COLUMN_TABLE_ID + " INTEGER, " +
                BLOCKED_TABLES_COLUMN_DATE_TIME + " TEXT)";

        // Создание таблицы "Бронирование"
        String createReservationsTable = "CREATE TABLE " + TABLE_RESERVATIONS + " (" +
                RESERVATIONS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RESERVATIONS_COLUMN_TABLE_ID + " INTEGER, " +
                RESERVATIONS_COLUMN_DATE_TIME + " TEXT, " +
                RESERVATIONS_COLUMN_ORDER_CODE + " TEXT, " +
                RESERVATIONS_COLUMN_CUSTOMER_NAME + " TEXT, " +
                RESERVATIONS_COLUMN_CUSTOMER_NUMBER + " TEXT)";


        String createDishesTable = "CREATE TABLE " + TABLE_DISHES + " (" +
                DISHES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DISHES_COLUMN_NAME + " TEXT, " +
                DISHES_COLUMN_PRICE + " REAL)";

        // Создание таблицы "Заказ"
        String createOrdersTable = "CREATE TABLE " + TABLE_ORDERS + " (" +
                ORDERS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RESERVATIONS_COLUMN_ORDER_CODES + " TEXT, " +
                ORDERS_COLUMN_DATE + " TEXT, " +
                ORDERS_COLUMN_PHONE + " TEXT)";

        // Выполнение создания таблиц
        db.execSQL(createTablesTable);
        db.execSQL(createAvailableTablesTable);
        db.execSQL(createBlockedTablesTable);
        db.execSQL(createReservationsTable);
        db.execSQL(createDishesTable);
        db.execSQL(createOrdersTable);


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Удаление старых таблиц при обновлении базы данных
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TABLES_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AVAILABLE_TABLES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOCKED_TABLES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATIONS);
        onCreate(db);
    }

}
