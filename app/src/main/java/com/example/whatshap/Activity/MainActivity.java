package com.example.whatshap.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.whatshap.Adapter.MainActivityViewPagerAdapter;
import com.example.whatshap.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0); // Remove elevation/shadow from the ActionBar
        }

        TabLayout tab = findViewById(R.id.MainActivityTabLayout);
        ViewPager viewPager = findViewById(R.id.MainActivityViewPager);
        MainActivityViewPagerAdapter adapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    // First fragment is visible
                    fab.setImageResource(R.drawable.search_icon);
                } else if (position == 1) {
                    // Second fragment is visible
                    fab.setImageResource(R.drawable.camera_icon);
                } else {
                    fab.setImageResource(R.drawable.videocall_icon);
                }
            }

            @Override
            public void onPageSelected(int position) {
                // You can add any additional logic here if needed when a page is selected
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // You can add any additional logic here based on page scroll state
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.Logout) {
            // Handle logout
            return true;
        } else if (itemId == R.id.Search) {
            // Handle search
            return true;
        } else if (itemId == R.id.Setting) {
            // Handle settings
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Menu getMenu() {
        return menu;
    }
}