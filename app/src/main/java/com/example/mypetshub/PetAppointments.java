package com.example.mypetshub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PetAppointments extends AppCompatActivity {
    private LinearLayout upcomingAppointmentsBoarding;
    private ImageButton Back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet_appointments);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        upcomingAppointmentsBoarding = findViewById(R.id.upcomingAppointmentsBoarding);
        upcomingAppointmentsBoarding.setOnClickListener(v -> {
            Intent intent = new Intent(PetAppointments.this, PetAppointments2.class);
            startActivity(intent);
        });

        Back_btn = findViewById(R.id.Back_btn);
        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backBtnAppointmentsIntent = new Intent(PetAppointments.this, UserDashboard.class);
                startActivity(backBtnAppointmentsIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}