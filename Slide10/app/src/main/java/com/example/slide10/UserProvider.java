package com.example.slide10;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

public class UserProvider extends ContentProvider {
  UserModel userModel;
  SQLiteDatabase db;
  private static final String TABLE_NAME = "user";

  public UserProvider() {
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    return db.delete(TABLE_NAME, selection, selectionArgs);
  }

  @Override
  public String getType(Uri uri) {
    // TODO: Implement this to handle requests for the MIME type of the data
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Uri insert(Uri uri, ContentValues values) {
    long newRowId = db.insert(TABLE_NAME, null, values);
    Toast.makeText(getContext(), "User added", Toast.LENGTH_SHORT).show();
    return Uri.withAppendedPath(uri, String.valueOf(newRowId));
  }

  @Override
  public boolean onCreate() {
    userModel = new UserModel(getContext());
    db = userModel.getWritableDatabase();
    return (db != null);
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection,
                      String[] selectionArgs, String sortOrder) {
    return db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection,
                    String[] selectionArgs) {
    return db.update(TABLE_NAME, values, selection, selectionArgs);
  }
}
