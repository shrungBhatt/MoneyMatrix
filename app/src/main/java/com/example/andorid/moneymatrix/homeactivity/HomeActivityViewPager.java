package com.example.andorid.moneymatrix.homeactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.andorid.moneymatrix.tabs.AddExpense;
import com.example.andorid.moneymatrix.tabs.Income;
import com.example.andorid.moneymatrix.tabs.MonthlyExpense;
import com.example.andorid.moneymatrix.tabs.SavingsDebts;


//This class is the ViewPager of the tabLayouts.

public class HomeActivityViewPager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
     private int mTabCount;

    //Constructor to the class
    public HomeActivityViewPager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.mTabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                return new AddExpense();
            case 1:
                return new Income();
            case 2:
                return new MonthlyExpense();
            case 3:
                return new SavingsDebts();
            default:
                return null;
        }
    }

    //Overiden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return mTabCount;
    }
}
