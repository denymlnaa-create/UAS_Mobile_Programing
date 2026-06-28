package com.dika.uas_mobileprograming;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setItemIconTintList(null);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, EndemikFragment.newInstance("Hewan"), "CURRENT_FRAG")
                    .commit();
            if (getSupportActionBar() != null) getSupportActionBar().setTitle("Endemik Hewan");
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            
            if (itemId == R.id.nav_hewan) {
                selectedFragment = EndemikFragment.newInstance("Hewan");
                if (getSupportActionBar() != null) getSupportActionBar().setTitle("Endemik Hewan");
                setMenuVisibility(true);
            } else if (itemId == R.id.nav_tumbuhan) {
                selectedFragment = EndemikFragment.newInstance("Tumbuhan");
                if (getSupportActionBar() != null) getSupportActionBar().setTitle("Endemik Tumbuhan");
                setMenuVisibility(true);
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
                if (getSupportActionBar() != null) getSupportActionBar().setTitle("Profil Saya");
                setMenuVisibility(false);
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment, "CURRENT_FRAG")
                        .commit();
                return true;
            }
            return false;
        });
    }

    private void setMenuVisibility(boolean visible) {
        if (menu != null) {
            MenuItem searchItem = menu.findItem(R.id.action_search);
            MenuItem favoriteItem = menu.findItem(R.id.action_favorite);
            MenuItem themeItem = menu.findItem(R.id.action_theme);
            if (searchItem != null) searchItem.setVisible(visible);
            if (favoriteItem != null) favoriteItem.setVisible(visible);
            if (themeItem != null) themeItem.setVisible(visible);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        
        // Update theme icon based on current mode
        MenuItem themeItem = menu.findItem(R.id.action_theme);
        if (themeItem != null) {
            int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                themeItem.setIcon(R.drawable.ic_sun);
            } else {
                themeItem.setIcon(R.drawable.ic_moon);
            }
        }

        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("CURRENT_FRAG");
        if (currentFragment instanceof ProfileFragment) {
            setMenuVisibility(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        } else if (id == R.id.action_favorite) {
            startActivity(new Intent(this, FavoriteActivity.class));
            return true;
        } else if (id == R.id.action_theme) {
            toggleTheme();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleTheme() {
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}
