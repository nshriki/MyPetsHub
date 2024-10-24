package com.example.mypetshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserProfile extends AppCompatActivity {
    private ImageButton backBtnProfile;
    private Button accInfoProfile, termsCondiProfile, logoutProfile;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Check if the user is logged in
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        int userId = sharedPreferences.getInt("id", -1);

        if (!isLoggedIn) {
            // Redirect to SignIn Activity
            Intent intent = new Intent(UserProfile.this, SignIn.class);
            startActivity(intent);
            finish();
            return;
        }

        String userName = sharedPreferences.getString("usersName", "User");
        String userLastName = sharedPreferences.getString("last_name", "Surname");
        TextView usersNameTextView = findViewById(R.id.userNameProfile);
        usersNameTextView.setText(userName + " " + userLastName);

        backBtnProfile = findViewById(R.id.backBtnProfile);
        backBtnProfile.setOnClickListener(view -> {
                Intent backBtnProfileIntent = new Intent(UserProfile.this, UserDashboard.class);
                startActivity(backBtnProfileIntent);
        });

        accInfoProfile = findViewById(R.id.accInfoProfile);
        accInfoProfile.setOnClickListener(view -> {
                Intent accInfoProfileIntent = new Intent(UserProfile.this, UserProfileDisplay1.class);
                startActivity(accInfoProfileIntent);
        });

        termsCondiProfile = findViewById(R.id.termsCondiProfile);
        termsCondiProfile.setOnClickListener(view -> {
                Intent termsCondiProfileIntent = new Intent(UserProfile.this, TermsCondition.class);
                startActivity(termsCondiProfileIntent);
        });
        
        // TEMPORARY Set up the logout functionality for the checkavailbtn
        logoutProfile = findViewById(R.id.logoutProfile);
        logoutProfile.setOnClickListener(v -> {
            // Clear user session
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Toast.makeText(getApplicationContext(), "Log out success", Toast.LENGTH_SHORT).show();

            // Redirect to SignIn Activity
            Intent intent = new Intent(UserProfile.this, SignIn.class);
            startActivity(intent);
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserProfile.this, UserDashboard.class);
        startActivity(intent);
        finish();  // Optional: Call finish if you want to close the current activity

        super.onBackPressed();
    }

}