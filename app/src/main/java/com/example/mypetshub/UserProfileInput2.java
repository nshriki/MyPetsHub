//package com.example.mypetshub;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//
//import android.os.AsyncTask;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//import java.io.BufferedReader;
//import java.io.OutputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.text.Editable;
//import android.widget.EditText;
//import android.text.TextWatcher;
//import android.widget.ImageView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class UserProfileInput2 extends AppCompatActivity {
//
//    ImageView profileImageViewUPI2;
//    private EditText userEmail1;
//    private EditText userContactNumber1;
//    private EditText userEmergencyContactName1;
//    private EditText userEmergencyContactRel1;
//    private EditText userEmergencyContactMobNumber1;
//    private Button Confirm_btn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_user_profile_input2);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        profileImageViewUPI2 = findViewById(R.id.imageViewUserP1); // Initialize ImageView
//        profileImageViewUPI2.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//        userEmail1 = findViewById(R.id.userEmail1);
//        userContactNumber1 = findViewById(R.id.userContactNumber1);
//        userEmergencyContactName1 = findViewById(R.id.userEmergencyContactName1);
//        userEmergencyContactRel1 = findViewById(R.id.userEmergencyContactRel1);
//        userEmergencyContactMobNumber1 = findViewById(R.id.userEmergencyContactMobNumber1);
//
//        Confirm_btn = findViewById(R.id.Confirm_btn);
//        Confirm_btn.setOnClickListener(v -> saveUserDataToDatabase());
//
//        // Receive the profile image URI from the first page
//        String profileImageUriString = getIntent().getStringExtra("profileImageUri");
//        if (profileImageUriString != null) {
//            Uri profileImageUri = Uri.parse(profileImageUriString);
//            profileImageViewUPI2.setImageURI(profileImageUri); // Set the profile image to the ImageView
//        }
//
//        // Back button to navigate to UserProfileInput1 and retain info
//        ImageView backBtnUPI2 = findViewById(R.id.backBtnUPI2);
//        backBtnUPI2.setOnClickListener(v -> {
//            finish(); // Close this activity and return to the previous one
//        });
//    }
//
//
//    private void saveUserDataToDatabase() {
//        String email = userEmail1.getText().toString().trim();
//        String contactNumber = userContactNumber1.getText().toString().trim();
//        String ecName = userEmergencyContactName1.getText().toString().trim();
//        String ecRelationship = userEmergencyContactRel1.getText().toString().trim();
//        String ecMobileNum = userEmergencyContactMobNumber1.getText().toString().trim();
//
//        // Data from page 1
//        Intent intent = getIntent();
//        String firstName = intent.getStringExtra("usersName");
//        String lastName = intent.getStringExtra("last_name");
//        String nickname = intent.getStringExtra("nickname");
//        String nationality = intent.getStringExtra("nationality");
//
//        // Get the birthday formatted as YYYY-MM-DD
//        String birthday = intent.getStringExtra("birthday");
//        String age = intent.getStringExtra("age");
//        String address = intent.getStringExtra("address");
//
//        // Create JSON object with user data
//        JSONObject json = new JSONObject();
//        try {
//            json.put("usersName", firstName);
//            json.put("last_name", lastName);
//            json.put("nickname", nickname);
//            json.put("email", email);
//            json.put("contactNumber", contactNumber);
//            json.put("gender", intent.getStringExtra("gender"));
//            json.put("age", age);
//            json.put("birthday", birthday); // Now formatted as YYYY-MM-DD
//            json.put("nationality", nationality);
//            json.put("address", address);
//            json.put("emergencyContactName", ecName);
//            json.put("emergencyContactRel", ecRelationship);
//            json.put("emergencyContactNum", ecMobileNum);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Error creating JSON data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Log the JSON data
//        Log.d("UserProfileData", json.toString());
//
//        OkHttpClient client = new OkHttpClient();
//        String url = "https://hamugaway.scarlet2.io/save_user_profile.php";
//
//        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json; charset=utf-8"));
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//                runOnUiThread(() -> Toast.makeText(UserProfileInput2.this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    runOnUiThread(() -> {
//                        Toast.makeText(UserProfileInput2.this, "Profile saved successfully!", Toast.LENGTH_SHORT).show();
//                        // Optionally navigate to another activity
//                        Intent mainIntent = new Intent(UserProfileInput2.this, UserProfileDisplay2.class);
//                        startActivity(mainIntent);
//                        finish();
//                    });
//                } else {
//                    runOnUiThread(() -> Toast.makeText(UserProfileInput2.this, "Failed to save data: " + response.message(), Toast.LENGTH_SHORT).show());
//                }
//            }
//        });
//    }
//}



package com.example.mypetshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.net.Uri;
import android.widget.EditText;
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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class UserProfileInput2 extends AppCompatActivity {

    ImageView profileImageViewUPI2;
    private EditText userEmail1, userContactNumber1, userEmergencyContactName1, userEmergencyContactRel1, userEmergencyContactNum1;
    private Button Confirm_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile_input2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        userEmail1 = findViewById(R.id.userEmail1);
        userContactNumber1 = findViewById(R.id.userContactNumber1);
        userEmergencyContactName1 = findViewById(R.id.userEmergencyContactName1);
        userEmergencyContactRel1 = findViewById(R.id.userEmergencyContactRel1);
        userEmergencyContactNum1 = findViewById(R.id.userEmergencyContactMobNumber1);
        Confirm_btn = findViewById(R.id.Confirm_btn);

        Confirm_btn.setOnClickListener(v -> saveUserDataToDatabase());

        profileImageViewUPI2 = findViewById(R.id.imageViewUserP1); // Initialize ImageView
        profileImageViewUPI2.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Receive the profile image URI from the first page
        String profileImageUriString = getIntent().getStringExtra("profileImageUri");
        if (profileImageUriString != null) {
            Uri profileImageUri = Uri.parse(profileImageUriString);
            profileImageViewUPI2.setImageURI(profileImageUri); // Set the profile image to the ImageView
        }

        // Back button to navigate to UserProfileInput1 and retain info
        ImageView backBtnUPI2 = findViewById(R.id.backBtnUPI2);
        backBtnUPI2.setOnClickListener(v -> {
            finish(); // Close this activity and return to the previous one
        });
    }

    private void saveUserDataToDatabase() {

        // Retrieve user_id from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("id", -1);

        Log.d("UserProfile", "User Profile ID: " + userId);
        if (userId == -1) {
            Toast.makeText(this, "User ID not found. Please login again.", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = userEmail1.getText().toString().trim();
        String contactNumber = userContactNumber1.getText().toString().trim();
        String ecName = userEmergencyContactName1.getText().toString().trim();
        String ecRelationship = userEmergencyContactRel1.getText().toString().trim();
        String ecMobileNum = userEmergencyContactNum1.getText().toString().trim();

        // Data from page 1
        Intent intent = getIntent();
        int userId1 = getIntent().getIntExtra("userId", -1);
        String firstName = intent.getStringExtra("usersName");
        String lastName = intent.getStringExtra("last_name");
        String nickname = intent.getStringExtra("nickname");
        String nationality = intent.getStringExtra("nationality");
        String birthday = intent.getStringExtra("birthday"); // Formatted as YYYY-MM-DD
        String age = intent.getStringExtra("age");
        String address = intent.getStringExtra("address");
        String gender = intent.getStringExtra("gender");

        String profileImageUriString = intent.getStringExtra("profileImageUri");
        String idImageUriString = intent.getStringExtra("idImageUri");

        // Convert profile image and ID image to base64
        String profileImageBase64 = convertImageToBase64(Uri.parse(profileImageUriString));
        String idImageBase64 = convertImageToBase64(Uri.parse(idImageUriString));

        sendUserDataToServer(userId, firstName, lastName, nickname, nationality, birthday, age, address, gender, email, contactNumber, ecName, ecRelationship, ecMobileNum, profileImageBase64, idImageBase64);

    }

    private void sendUserDataToServer(int userId, String firstName, String lastName, String nickname, String nationality, String birthday, String age, String address, String gender, String email, String contactNumber, String ecName, String ecRelationship, String ecMobileNum, String profileImageBase64, String idImageBase64) {
        // Create JSON object with user data
        JSONObject json = new JSONObject();
        try {
            json.put("user_id", userId);
            json.put("usersName", firstName);
            json.put("last_name", lastName);
            json.put("nickname", nickname);
            json.put("email", email);
            json.put("contactNumber", contactNumber);
            json.put("gender", gender);
            json.put("age", age);
            json.put("birthday", birthday); // Now formatted as YYYY-MM-DD
            json.put("nationality", nationality);
            json.put("address", address);
            json.put("emergencyContactName", ecName);
            json.put("emergencyContactRel", ecRelationship);
            json.put("emergencyContactNum", ecMobileNum);

            json.put("profile_image", profileImageBase64); // Add profile image data
            json.put("id_image", idImageBase64); // Add ID image data

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating JSON data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        // Log the JSON data
        Log.d("UserProfileData", json.toString());

        OkHttpClient client = new OkHttpClient();
        String url = "https://hamugaway.scarlet2.io/save_user_profile.php";

        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(UserProfileInput2.this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(() -> {
                        Toast.makeText(UserProfileInput2.this, "Profile saved successfully!", Toast.LENGTH_SHORT).show();

                        // Redirect to EditUserProfile and pass the data
                        Intent editProfileIntent = new Intent(UserProfileInput2.this, UserProfileDisplay1.class);
                        editProfileIntent.putExtra("fullName", firstName + " " + lastName);
                        editProfileIntent.putExtra("nickname", nickname);
                        editProfileIntent.putExtra("nationality", nationality);
                        editProfileIntent.putExtra("gender", gender);
                        editProfileIntent.putExtra("birthday", birthday);
                        editProfileIntent.putExtra("age", age);
                        editProfileIntent.putExtra("address", address);

                        startActivity(editProfileIntent);
                        finish(); // Close current activity

                        // Redirect to EditUserProfile and pass the data
                        Intent editProfileIntentPage2 = new Intent(UserProfileInput2.this, UserProfileDisplay1.class);
                        editProfileIntentPage2.putExtra("email", email);
                        editProfileIntentPage2.putExtra("contactNumber", contactNumber);
                        editProfileIntentPage2.putExtra("emergencyContactName", ecName);
                        editProfileIntentPage2.putExtra("emergencyContactRel", ecRelationship);
                        editProfileIntentPage2.putExtra("emergencyContactNum", ecMobileNum);

                        startActivity(editProfileIntentPage2);
                        finish(); // Close current activity
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(UserProfileInput2.this, "Failed to save data: " + response.message(), Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    private String convertImageToBase64(Uri imageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            return Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}