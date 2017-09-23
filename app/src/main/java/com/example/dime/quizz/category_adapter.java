package com.example.dime.quizz;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Dime on 27-Mar-17.
 */

public class category_adapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public category_adapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position) {
            case 0:
                movies_fragment tab1 = new movies_fragment();
                return tab1;
            case 1:
                sport_fragment tab2 = new sport_fragment();
                return tab2;
//            case 2:
//                music_fragment tab3 = new music_fragment();
//                return tab3;
            case 2:
                tv_fragment tab4 = new tv_fragment();
                return tab4;
            case 3:
                geography_fragment tab5 = new geography_fragment();
                return tab5;
            case 4:
                history_fragment tab6 = new history_fragment();
                return tab6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
