package com.example.slide10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserModel extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "user.db";
  private static final int DATABASE_VERSION = 1;
  private static final String TABLE_NAME = "user";
  private static final String COLUMN_ID = "id";
  private static final String COLUMN_USERNAME = "username";
  private static final String COLUMN_PASSWORD = "password";

  private static final String SQL_CREATE_ENTRIES =
          "CREATE TABLE " + TABLE_NAME + " (" +
                  COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  COLUMN_USERNAME + " TEXT, " +
                  COLUMN_PASSWORD + " TEXT)";

  public UserModel(@Nullable Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_ENTRIES);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
  }

  public long insertUser(String username, String password) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_USERNAME, username);
    values.put(COLUMN_PASSWORD, password);
    return db.insert(TABLE_NAME, null, values);
  }

  public Cursor queryUserByUsername(String username) {
    SQLiteDatabase db = this.getReadableDatabase();
    return db.query(TABLE_NAME, null, COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);
  }

  // TODO: Add methods for updating, deleting, and other database operations as needed

}
