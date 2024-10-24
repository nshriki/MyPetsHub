package com.example.mypetshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PetProfile extends AppCompatActivity {

    private RecyclerView petRecyclerView;
    private List<Pet> petList = new ArrayList<>();
    private PetAdapter petAdapter;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);

        // Retrieve userId from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id", -1);

        if (userId == -1) {
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize RecyclerView and fetch pets
        petRecyclerView = findViewById(R.id.pet_RecyclerView);
        petRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        petAdapter = new PetAdapter(petList, this);
        petRecyclerView.setAdapter(petAdapter);

        // Fetch pets for the logged-in user
        fetchPetsFromDatabase(userId);

        // Add pet button logic
        Button btnAddPet = findViewById(R.id.btnAddPet);
        btnAddPet.setOnClickListener(v -> {
            Intent intent = new Intent(PetProfile.this, PetProfileInput.class);
            startActivity(intent);
        });

        ImageButton backBtnPetProfile = findViewById(R.id.backBtnPetProfile);
        backBtnPetProfile.setOnClickListener(view -> {
            Intent backIntent = new Intent(PetProfile.this, UserDashboard.class);
            startActivity(backIntent);
            finish();
        });
    }

    // Method to fetch pets from the database based on user ID
    private void fetchPetsFromDatabase(int userId) {

        OkHttpClient client = new OkHttpClient();
        String url = "https://hamugaway.scarlet2.io/get_pets.php?user_id=" + userId;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(PetProfile.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();
                    try {
                        JSONArray jsonArray = new JSONArray(jsonResponse);
                        petList.clear();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject petObject = jsonArray.getJSONObject(i);

                            int petId = petObject.getInt("pet_id");
                            String petName = petObject.getString("pet_name");
                            String petImage = petObject.getString("pet_image");

                            Pet pet = new Pet(petId, petName, petImage);
                            petList.add(pet);
                        }

                        runOnUiThread(() -> petAdapter.notifyDataSetChanged());

                    } catch (JSONException e) {
                        runOnUiThread(() -> {
                            Toast.makeText(PetProfile.this, "Failed to parse data", Toast.LENGTH_SHORT).show();
                        });
                    }
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(PetProfile.this, "Failed to fetch pets", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}



