package com.example.slide10;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

  public void onShowUsers(View view) {
    try {
      Uri uri = Uri.parse("content://com.example.slide10.UserProvider/users");
      String[] projection = {"id", "username", "password"};
      Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
      TableLayout tableLayout = findViewById(R.id.table);
      if (tableLayout.getChildCount() > 1) {
        tableLayout.removeViews(1, tableLayout.getChildCount() - 1);
      }
      while (cursor.moveToNext()) {
        TableRow tableRow = new TableRow(this);
        int id = cursor.getInt(0);
        String username = cursor.getString(1);

        TextView idView = new TextView(this);
        idView.setText(String.valueOf(id));
        TextView usernameView = new TextView(this);
        usernameView.setText(username);
        TextView passwordView = new TextView(this);
        passwordView.setText("••••••"); // Masked password for security reasons

        Button deleteButton = new Button(this);
        deleteButton.setText("Delete");
        deleteButton.setBackgroundColor(Color.rgb(70, 80, 90));
        deleteButton.setOnClickListener(this::onDeleteItem);

        tableRow.addView(idView);
        tableRow.addView(usernameView);
        tableRow.addView(passwordView);
        tableRow.addView(deleteButton);
        tableLayout.addView(tableRow);
      }
    } catch (Exception e) {
      Toast.makeText(this, "Error fetching users.", Toast.LENGTH_SHORT).show();
    }
  }

  public void onDeleteItem(View view) {
    TableRow tableRow = (TableRow) view.getParent();
    TextView idView = (TextView) tableRow.getChildAt(0);
    String id = idView.getText().toString();

    Uri uri = Uri.parse("content://com.example.slide10.UserProvider/users");
    getContentResolver().delete(uri, "id = ?", new String[]{id});

    ((TableLayout) tableRow.getParent()).removeView(tableRow); // remove the row directly
  }

  public void onAddUser(View view) {
    ContentValues values = new ContentValues();
    EditText username = findViewById(R.id.username);
    EditText password = findViewById(R.id.password);
    values.put("username", username.getText().toString());
    values.put("password", password.getText().toString());

    try {
      getContentResolver().insert(Uri.parse("content://com.example.slide10.UserProvider"), values);
      onShowUsers(view);
    } catch (Exception e) {
      Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
