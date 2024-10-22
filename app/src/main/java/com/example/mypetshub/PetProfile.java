package com.example.mypetshub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PetProfile extends AppCompatActivity {
    private ImageButton backBtnPetProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Add pet button
        Button btnAddPet = findViewById(R.id.btnAddPet);
        btnAddPet.setOnClickListener(v -> {
            Intent intent = new Intent(PetProfile.this, PetProfileInput.class);
            startActivity(intent);
        });

        backBtnPetProfile = findViewById(R.id.backBtnPetProfile);
        backBtnPetProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backBtnPetProfileIntent = new Intent(PetProfile.this, UserDashboard.class);
                startActivity(backBtnPetProfileIntent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}