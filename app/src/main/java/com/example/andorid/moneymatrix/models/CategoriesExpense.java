package com.example.andorid.moneymatrix.models;


import java.util.UUID;

//Model class which contains the getter and setters of categories and a constructor of this class
//used to feed the categories into the arrayList of categories.


public class CategoriesExpense {

    private int mCategoryNameId, mCategoryImageId;
    private String mExpense;
    private String mCategoryName;

    public CategoriesExpense (int categoryNameId, int categoryImageId) {
        mCategoryImageId = categoryImageId;
        mCategoryNameId = categoryNameId;
    }

    public UUID getId () {
        return UUID.randomUUID();
    }

    public String getExpense () {
        return mExpense;
    }

    public String getCategoryName () {
        return mCategoryName;
    }

    public void setCategoryName (String categoryName) {
        mCategoryName = categoryName;
    }

    public void setExpense (String expense) {
        mExpense = expense;
    }

    public int getCategoryNameId () {
        return mCategoryNameId;
    }

    public void setCategoryNameId (int categoryNameId) {
        mCategoryNameId = categoryNameId;
    }

    public int getCategoryImageId () {
        return mCategoryImageId;
    }

    public void setCategoryImageId (int categoryImageId) {
        mCategoryImageId = categoryImageId;
    }


}
