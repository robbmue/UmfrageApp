package com.example.dsgvobestandsaufnahme;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private NoSwipePager viewPager;
    private BottomBarAdapter pagerAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Log.d(MainActivity.class.getSimpleName(), item + "");

            switch (item.getItemId()) {
                case R.id.navigation_new:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_open:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_sync:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Set up View Pager
        viewPager = findViewById(R.id.viewPager);
        viewPager.setPagingEnabled(false);

        //Initialize PagerAdapter
        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        //Create Fragments and add them to the Adapter
        NewFragment fragmentNew = new NewFragment();
        OpenFragment fragmentOpen = new OpenFragment();
        SyncFragment fragmentSync = new SyncFragment();
        pagerAdapter.addFragements(fragmentNew);
        pagerAdapter.addFragements(fragmentOpen);
        pagerAdapter.addFragements(fragmentSync);

        //Set View Pager Adapter
        viewPager.setAdapter(pagerAdapter);

    }

}
