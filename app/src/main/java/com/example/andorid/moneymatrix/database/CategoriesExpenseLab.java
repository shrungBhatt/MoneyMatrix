package com.example.andorid.moneymatrix.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.andorid.moneymatrix.models.CategoriesExpense;

//This class is used to feed the categories database table with values.Getting content values.
//This class also contains the method to addExpense in the addExpense tab.

public class CategoriesExpenseLab {
    private static CategoriesExpenseLab sCategoriesExpenseLab;
    private SQLiteDatabase mDatabase;
    private CategoriesExpenseBaseHelper mHelper;
    private Context mContext;


    public static CategoriesExpenseLab get(Context context) {
        if (sCategoriesExpenseLab == null) {
            sCategoriesExpenseLab = new CategoriesExpenseLab(context);
        }
        return sCategoriesExpenseLab;
    }

    //Method to create instance of this class.
    private CategoriesExpenseLab (Context context) {
        mContext = context.getApplicationContext();
        mHelper = new CategoriesExpenseBaseHelper(mContext);
        mDatabase = new CategoriesExpenseBaseHelper(mContext).getWritableDatabase();

    }

    //Method used in the addExpense tab to add expense.
    public void addExpense(CategoriesExpense categoriesExpense){
        ContentValues values = getContentValues(categoriesExpense);
        mDatabase.insert(CategoriesExpenseDbSchema.NAME, null, values);
    }


    //Method used to get the values for storing it in the database of categoriesExpense.
    public static ContentValues getContentValues (CategoriesExpense categoriesExpense) {
        ContentValues values = new ContentValues();

        values.put(CategoriesExpenseDbSchema.Cols.UUID, categoriesExpense.getId().toString());
        values.put(CategoriesExpenseDbSchema.Cols.CATEGORY, categoriesExpense.getCategoryNameId());
        values.put(CategoriesExpenseDbSchema.Cols.EXPENSE, categoriesExpense.getExpense());

        return values;
    }

}
