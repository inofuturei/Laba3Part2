package com.example.laba3part2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GroupmatesActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupmates);

        databaseHelper = new DatabaseHelper(this);
        TextView textViewGroupmates = findViewById(R.id.text_view_groupmates);

        Cursor cursor = databaseHelper.getAllGroupmates();
        StringBuilder groupmatesList = new StringBuilder();

        while (cursor.moveToNext()) {
            String lastName = cursor.getString(cursor.getColumnIndex("last_name"));
            String firstName = cursor.getString(cursor.getColumnIndex("first_name"));
            String middleName = cursor.getString(cursor.getColumnIndex("middle_name"));
            String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));

            groupmatesList.append(lastName).append(" ").append(firstName).append(" ").append(middleName)
                    .append(" - ").append(timestamp).append("n");
        }

        textViewGroupmates.setText(groupmatesList.toString());
        cursor.close();
    }
}