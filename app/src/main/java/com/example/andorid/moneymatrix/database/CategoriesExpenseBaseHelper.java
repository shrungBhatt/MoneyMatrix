package com.example.andorid.moneymatrix.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//Class that helps to access the database of categories.Using Queries,creating table of categories,
//Updating the database.

public class CategoriesExpenseBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "categories.db";
    private SQLiteDatabase mDatabase;

    public CategoriesExpenseBaseHelper (Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    //Overiden method of SQLiteOpenHelper to create the database.
    @Override
    public void onCreate (SQLiteDatabase db) {
        //SQL code used to create the table.
        db.execSQL("create table " + CategoriesExpenseDbSchema.NAME + "(" +
                " _id integer primary key autoincrement, " +
                CategoriesExpenseDbSchema.Cols.UUID + ", " +
                CategoriesExpenseDbSchema.Cols.CATEGORY + ", " +
                CategoriesExpenseDbSchema.Cols.EXPENSE +
                ")"
        );

    }

    //Overiden method to upgrade the table if any new columns are added or the table is edited.
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
