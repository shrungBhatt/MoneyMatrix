package com.example.andorid.moneymatrix.database;


//This class is the schema of the AddExpenseDatabase.

public class CategoriesExpenseDbSchema {
    public static final String NAME = "categories";

    public static final class Cols{
        public static final String UUID ="uuid";
        public static final String EXPENSE = "expense";
        public static final String CATEGORY = "category";
    }
}
