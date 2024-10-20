package com.example.mypetshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PetProfileInput2 extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText Allergies_EditText, Conditions_EditText, Clinic_EditText,
            Doctor_EditText, Vaccine_EditText, VaccineCard_EditText;
    private CheckBox RabbiesVaccine_CheckBox, FiveOneVaccine_CheckBox, OtherVaccine_CheckBox;
    private ImageView imageView;
    private ImageButton imageButton2;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable Edge-to-Edge and set the layout
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet_profile_input2);

        // Handle window insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Allergies_EditText = findViewById(R.id.Allergies_EditText);
        Conditions_EditText = findViewById(R.id.Conditions_EditText);
        Clinic_EditText = findViewById(R.id.Clinic_EditText);
        Doctor_EditText = findViewById(R.id.Doctor_EditText);
        RabbiesVaccine_CheckBox = findViewById(R.id.RabbiesVaccine_CheckBox);
        FiveOneVaccine_CheckBox = findViewById(R.id.FiveOneVaccine_Checkbox);
        OtherVaccine_CheckBox = findViewById(R.id.OtherVaccine_CheckBox);
        Vaccine_EditText = findViewById(R.id.Vaccine_EditText);
        VaccineCard_EditText = findViewById(R.id.VaccineCard_EditText);
        button2 = findViewById(R.id.button2);

        // Initialize ImageView and ImageButton from the layout
        imageView = findViewById(R.id.imageView);
//        imageButton2 = findViewById(R.id.imageButton2);

        // Set click listener on ImageButton to open image picker
//        imageButton2.setOnClickListener(v -> openImageChooser());

        button2.setOnClickListener(v -> addNewPetProfile());
    }

    // Method to open the gallery for image selection
    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Handling the result when the user selects an image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                // Convert selected image to Bitmap and set it to the ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addNewPetProfile() {
        // Data from page 1
        Intent intent = getIntent();
        // Retrieve user_id from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("id", -1);

        Log.d("UserProfile", "User Profile ID: " + userId);
        if (userId == -1) {
            Toast.makeText(this, "User ID not found. Please login again.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create JSON object with user data
        JSONObject json = new JSONObject();
        try {
            json.put("user_id", userId);
            json.put("pet_name", intent.getStringExtra("pet_name"));
            json.put("sex", intent.getStringExtra("pet_sex"));
            json.put("species", intent.getStringExtra("pet_species"));
            json.put("birthday", intent.getStringExtra("pet_birthday"));
            json.put("weight", intent.getStringExtra("pet_weight"));
            json.put("allergies", Allergies_EditText.getText().toString());
            json.put("pre_existing_condition", Conditions_EditText.getText().toString());
            json.put("veterinary_clinic", Clinic_EditText.getText().toString()); // Now formatted as YYYY-MM-DD
            json.put("veterinary_doctor", Doctor_EditText.getText().toString());
            json.put("rabbies_vaccine", RabbiesVaccine_CheckBox.isChecked() ? "1" : "0");
            json.put("five_on_one_vaccine", FiveOneVaccine_CheckBox.isChecked() ? "1" : "0");
            json.put("other_vaccine", OtherVaccine_CheckBox.isChecked() ? Vaccine_EditText.getText().toString() : "");
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating JSON data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpClient client = new OkHttpClient();
        String url = "https://hamugaway.scarlet2.io/save_pet_profile.php";

        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(PetProfileInput2.this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(() -> {
                        Toast.makeText(PetProfileInput2.this, "Pet saved successfully!", Toast.LENGTH_SHORT).show();

                        // Redirect to EditUserProfile and pass the data
                        Intent intent = new Intent(PetProfileInput2.this, PetProfileDisplay.class);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(PetProfileInput2.this, "Failed to save data: " + response.message(), Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}
