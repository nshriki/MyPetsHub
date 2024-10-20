package com.example.mypetshub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PetBoardingConfirmation extends AppCompatActivity {

    private Button B_Confirm_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet_boarding_confirmation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnGetStarted = findViewById(R.id.B_Confirm_Btn);
        btnGetStarted.setOnClickListener(v -> {
            // Create an Intent to start the SignUpActivity
            Intent intent = new Intent(PetBoardingConfirmation.this, PetAppointments.class);
            startActivity(intent);
        });
    }
}