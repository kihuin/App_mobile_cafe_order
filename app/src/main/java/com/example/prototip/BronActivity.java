package com.example.prototip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BronActivity extends AppCompatActivity {

    private EditText editTextDateTime;
    private EditText editTextName;
    private EditText editTextPhoneNumber;
    private Button buttonBron;
    private int tableId;

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bron);

        // Инициализация элементов интерфейса
        editTextDateTime = findViewById(R.id.editTextDate);
        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonBron = findViewById(R.id.buttonBron);

        // Инициализация объекта для работы с базой данных
        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        // Обработчик нажатия на кнопку "Забронировать стол"
        buttonBron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получение введенных пользователем данных
                String dateTime = editTextDateTime.getText().toString();
                String name = editTextName.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                tableId = getIntent().getIntExtra("tableId", 0); // 0 - значениe по умолчанию, если "tableId" не было передано

                // Проверка на заполнение обязательных полей
                if (dateTime.isEmpty() || name.isEmpty() || phoneNumber.isEmpty()) {
                    Toast.makeText(BronActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                } else {
                    // Добавление данных в таблицу с бронированиями
                    String uniqueCode = generateOrderCode();
                    databaseManager.addReservation(tableId, dateTime, uniqueCode, name, phoneNumber);
                    Toast.makeText(BronActivity.this, "Стол забронирован", Toast.LENGTH_SHORT).show();

                    // Передача уникального кода на другую страницу
                    Intent intent = new Intent(BronActivity.this, OtherActivity.class);
                    intent.putExtra("code", uniqueCode);
                    startActivity(intent);

                    finish(); // Закрытие активити после успешного бронирования
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseManager.close(); // Закрытие подключения к базе данных при уничтожении активити
    }

    private String generateOrderCode() {
        return UniqueCodeGenerator.generateCode();
    }
}