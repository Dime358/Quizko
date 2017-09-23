package com.example.dime.quizz;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Dime on 26-Mar-17.
 */

public class tab_adapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public tab_adapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position) {
            case 0:
                results_fragment_1 tab1 = new results_fragment_1();
                return tab1;
            case 1:
                results_fragment_2 tab2 = new results_fragment_2();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
