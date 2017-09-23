package com.example.dime.quizz;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class categories extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_categories);
        setSupportActionBar(mActionBarToolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_categories);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        //tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.getTabAt(0).setIcon(R.drawable.tab_movie);
        tabLayout.getTabAt(1).setIcon(R.drawable.tab_sport);
        //tabLayout.getTabAt(2).setIcon(R.drawable.tab_music);
        tabLayout.getTabAt(2).setIcon(R.drawable.tab_tv);
        tabLayout.getTabAt(3).setIcon(R.drawable.tab_geo);
        tabLayout.getTabAt(4).setIcon(R.drawable.tab_history);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager_categories);
        final category_adapter adapter = new category_adapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onBackPressed() {
    //nema nazad
    }
}
