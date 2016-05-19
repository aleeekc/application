package com.example.aleksandar.application;

/**
 * Created by aleksandar on 5/18/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDB.db";
    Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE category" +
                        "(primaryid INTEGER PRIMARY KEY AUTOINCREMENT, category_name text, category_id text)");
        db.execSQL("CREATE TABLE subcategory" + "(primaryid INTEGER PRIMARY KEY AUTOINCREMENT, category_id text, subcategory_id text, subcategory_name text, cover text)");
        db.execSQL("CREATE TABLE subsubcategory" + "(primaryid INTEGER PRIMARY KEY AUTOINCREMENT, sub_subcategory_name text, subcategory_id text, sub_subcategory_id text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS subcategory");
        db.execSQL("DROP TABLE IF EXISTS subsubcategory");
        onCreate(db);
    }


    public void DropTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS subcategory");
        db.execSQL("DROP TABLE IF EXISTS subsubcategory");
        context.deleteDatabase(DATABASE_NAME);
        Log.d("DB", "THE DATABASE WAS DELETED");
        db.close();
    }

    public boolean insertCategory(String category_id,String category_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v("TAG", "database can be accessed to write");
        ContentValues contentValues = new ContentValues();
        contentValues.put("category_name", category_name.toString());
        contentValues.put("category_id", category_id.toString());
        db.insert("subcategory", null, contentValues);
        db.close();
        return true;
    }

    public boolean insertSubSubCategory(String sub_subcategory_name , String subcategory_id ,String sub_subcategory_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v("TAG", "database can be accessed to write");
        ContentValues contentValues = new ContentValues();
        contentValues.put("sub_subcategory_name", sub_subcategory_name.toString());
        contentValues.put("subcategory_id", subcategory_id.toString());
        contentValues.put("sub_subcategory_id", sub_subcategory_id.toString());
        db.insert("subsubcategory", null, contentValues);
        db.close();
        return true;
    }

    public boolean insertSubCategory(String category_id ,String subcategory_id, String subcategory_name,String cover){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v("TAG", "database can be accessed to write");
        ContentValues contentValues = new ContentValues();
        contentValues.put("subcategory_id", subcategory_id.toString());
        contentValues.put("category_id", category_id.toString());
        contentValues.put("subcategory_name", subcategory_name.toString());
        contentValues.put("cover", cover.toString());
        db.insert("subcategory", null, contentValues);
        db.close();
        return true;
    }

}
