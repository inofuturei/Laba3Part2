package com.example.laba3part2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        Button buttonViewGroupmates = findViewById(R.id.button_view_groupmates);
        Button buttonAddGroupmate = findViewById(R.id.button_add_groupmate);
        Button buttonUpdateLastGroupmate = findViewById(R.id.button_update_last_groupmate);

        buttonViewGroupmates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GroupmatesActivity.class);
                startActivity(intent);
            }
        });

        buttonAddGroupmate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addGroupmate("Фамилия", "Имя", "Отчество");
            }
        });

        buttonUpdateLastGroupmate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateLastGroupmate("Иванов", "Иван", "Иванович");
            }
        });
    }
}