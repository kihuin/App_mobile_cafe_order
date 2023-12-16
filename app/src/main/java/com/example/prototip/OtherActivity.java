package com.example.prototip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {


    private TextView textViewCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        // Инициализация элемента интерфейса
        textViewCode = findViewById(R.id.textViewCode);

        // Получение уникального кода из предыдущей страницы
        String code = getIntent().getStringExtra("code");

        // Отображение уникального кода на экране
        textViewCode.setText(code);
    }
}