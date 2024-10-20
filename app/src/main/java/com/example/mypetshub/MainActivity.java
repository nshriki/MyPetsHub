package com.example.mypetshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Check if the user is already logged in
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // Redirect to UserDashboard if logged in
            Intent intent = new Intent(MainActivity.this, UserDashboard.class);
            startActivity(intent);
            finish();
            return; // Exit onCreate
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.backBtn), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the "Get Started" button and set its OnClickListener
//        Button btnGetStarted = findViewById(R.id.btnGetStarted);
//        btnGetStarted.setOnClickListener(v -> {
//            // Create an Intent to start the SignUpActivity
//            Intent intent = new Intent(MainActivity.this, SignUp.class);
//            startActivity(intent);
//        });
    }
}
