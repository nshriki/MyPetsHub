package com.example.mypetshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PetProfileDisplay extends AppCompatActivity {

    private TextView pet_Name1, pet_Name2, pet_Gender, pet_Birthday,
            pet_Age, pet_Species, pet_Breed, pet_Weight, pet_Vet_Clinic_txt,
            pet_VetDoctor, pet_Allergies, pet_PE_Conditions, pet_Vaccines;

    private ImageView pet_ProfileView, pet_Vax_Card_imageView;
    private ImageButton Back_btn, Edit_btnPPD;
    private int userId, petId;

    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet_profile_display);

        // Initialize views
        pet_ProfileView = findViewById(R.id.pet_ProfileView);
        pet_Name1 = findViewById(R.id.pet_Name1);
        pet_Name2 = findViewById(R.id.pet_Name2);
        pet_Gender = findViewById(R.id.pet_Gender);
        pet_Birthday = findViewById(R.id.pet_Birthday);
        pet_Age = findViewById(R.id.pet_Age);
        pet_Species = findViewById(R.id.pet_Species);
        pet_Breed = findViewById(R.id.pet_Breed);
        pet_Weight = findViewById(R.id.pet_Weight);
        pet_Vet_Clinic_txt = findViewById(R.id.pet_Vet_Clinic_txt);
        pet_VetDoctor = findViewById(R.id.pet_VetDoctor);
        pet_Allergies = findViewById(R.id.pet_Allergies);
        pet_PE_Conditions = findViewById(R.id.pet_PE_Conditions);
        pet_Vaccines = findViewById(R.id.pet_Vaccines);

        Back_btn = findViewById(R.id.Back_btn);
        Edit_btnPPD = findViewById(R.id.Edit_btnPPD);

        // Retrieve user_id from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id", -1);
        Log.d("FetchPetProfile", "userID: " + userId);

        if (userId == -1) {
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
            return; // Early exit if user ID is invalid
        }

        // Get petId if editing existing profile
        Intent intent = getIntent();
        if (intent.hasExtra("pet_id")) {
            petId = intent.getIntExtra("pet_id", -1);
            isEditing = true;
        }
        // Fetch user profile data
        fetchPetProfile(userId, petId);

        // Back button logic
        Back_btn.setOnClickListener(v -> {
            Intent backIntent = new Intent(PetProfileDisplay.this, PetProfile.class);
            startActivity(backIntent);
            finish();
        });

        // Edit button logic
        Edit_btnPPD.setOnClickListener(v -> {
            Intent editIntent = new Intent(PetProfileDisplay.this, PetProfileInput2.class);
            editIntent.putExtra("pet_id", petId);
            startActivity(editIntent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fetchPetProfile(int userId, int petId) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://hamugaway.scarlet2.io/get_pet_profile.php?user_id=" + userId + "&pet_id=" + petId)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(PetProfileDisplay.this, "Failed to retrieve profile: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() == null) {
                    runOnUiThread(() ->
                            Toast.makeText(PetProfileDisplay.this, "No response from server.", Toast.LENGTH_SHORT).show());
                    return;
                }

                String responseData = response.body().string();
                Log.d("PetProfileDisplay", "Response: " + responseData);

                if (responseData.trim().isEmpty()) {
                    runOnUiThread(() ->
                            Toast.makeText(PetProfileDisplay.this, "Empty response from server.", Toast.LENGTH_SHORT).show());
                    return;
                }

                try {
                    JSONObject jsonResponse = new JSONObject(responseData);
                    if (jsonResponse.has("error")) {
                        runOnUiThread(() ->
                                Toast.makeText(PetProfileDisplay.this, jsonResponse.optString("error"), Toast.LENGTH_SHORT).show());
                        return;
                    }

                    // Extract data from the JSON response
                    String pet_name = jsonResponse.optString("pet_name", "");
                    String sex = jsonResponse.optString("sex", "");
                    String species = jsonResponse.optString("species", "");
                    String breed = jsonResponse.optString("breed", "");
                    String birthday = jsonResponse.optString("birthday", "");
                    String pet_age = jsonResponse.optString("pet_age", "");
                    String weight = jsonResponse.optString("weight", "");
                    String allergies = jsonResponse.optString("allergies", "");
                    String pre_existing_condition = jsonResponse.optString("pre_existing_condition", "");
                    String veterinary_clinic = jsonResponse.optString("veterinary_clinic", "");
                    String veterinary_doctor = jsonResponse.optString("veterinary_doctor", "");
                    int rabbies_vaccine = jsonResponse.optInt("rabbies_vaccine", -1);
                    int five_on_one_vaccine = jsonResponse.optInt("five_on_one_vaccine", -1);
                    String other_vaccine = jsonResponse.optString("other_vaccine", "");



                    // Update UI on the main thread
                    runOnUiThread(() -> {
                        pet_Name1.setText(pet_name);
                        pet_Name2.setText(pet_name);
                        pet_Gender.setText(sex);

                        String formattedBirthday = formatBirthday(birthday);
                        pet_Birthday.setText(formattedBirthday);

                        pet_Age.setText(pet_age);

                        pet_Species.setText(species);
                        pet_Breed.setText(breed);
                        pet_Weight.setText(weight);
                        pet_Vet_Clinic_txt.setText(veterinary_clinic);
                        pet_VetDoctor.setText(veterinary_doctor);
                        pet_Allergies.setText(allergies);
                        pet_PE_Conditions.setText(pre_existing_condition);

                        StringBuilder vaccineList = new StringBuilder();

                        if (rabbies_vaccine == 1) {
                            vaccineList.append("Rabbies Vaccine");
                        }
                        if (five_on_one_vaccine == 1) {
                            if (vaccineList.length() > 0) vaccineList.append(", ");
                            vaccineList.append("5-in-1 Vaccine");
                        }

                        if (!other_vaccine.isEmpty()) {
                            if (vaccineList.length() > 0) vaccineList.append(", ");
                            vaccineList.append(other_vaccine);
                        }

                        // Set vaccines to the TextView
                        pet_Vaccines.setText(vaccineList.toString());
                    });
                } catch (JSONException e) {
                    Log.e("PetProfileDisplay", "JSON Parsing error: " + responseData, e);
                    runOnUiThread(() ->
                            Toast.makeText(PetProfileDisplay.this, "Error parsing server response.", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    private String formatBirthday(String birthday) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()); // Adjust format based on the API response
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM. dd, yyyy", Locale.getDefault());

        try {
            // Parse the birthday string to Date object
            Date date = inputFormat.parse(birthday);
            // Return the formatted date
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return birthday; // Return original string if there's a parsing error
        }
    }


    private void loadProfileImage(String imageUrl) {
        // Assuming imageUrl is a valid URL, load the image using a separate thread
        new Thread(() -> {
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                runOnUiThread(() -> pet_ProfileView.setImageBitmap(myBitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
