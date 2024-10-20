package com.example.mypetshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu); // Ensure this layout contains the correct IDs

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Ensure navigationView is not null
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        } else {
            Log.e("MainMenu", "NavigationView is null. Check your layout file.");
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_AccInfo) {
            startActivity(new Intent(this, UserProfileDisplay1.class));
        } else if (item.getItemId() == R.id.nav_TandConditons) {
            startActivity(new Intent(this, TermsCondition.class));
        } else if (item.getItemId() == R.id.nav_HandFAQs) {
            startActivity(new Intent(this, HelpAndFAQs.class));
        } else if (item.getItemId() == R.id.nav_settings) {
            startActivity(new Intent(this, Settings.class));
        } else if (item.getItemId() == R.id.nav_logout) {
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();
            startActivity(new Intent(this, SignIn.class));
            finish();
        } else {
            Log.e("MainMenu", "Unknown menu item clicked: " + item.getItemId());
            return false;
        }

        drawerLayout.closeDrawers(); // Close the navigation drawer after an item is selected
        return true;
    }
}
