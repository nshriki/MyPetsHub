//package com.example.mypetshub;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class PetProfileDisplay extends AppCompatActivity {
//
//    private TextView pet_Name1, user_Name2, user_Nname, user_Nationality,
//            user_Gender, user_Birthday, user_Age, user_Email, user_ContactNumber,
//            user_ecName, user_Address, user_ecRelationship, user_ecContactNumber;
//    private ImageView user_ProfileView;
//    private ImageButton Back_btn, Edit_btnPPD;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_pet_profile_display);
//
//        // Initialize views
//        user_ProfileView = findViewById(R.id.user_ProfileView);
//        pet_Name1 = findViewById(R.id.pet_Name1);
//        user_Name2 = findViewById(R.id.user_Name2);
//        user_Nname = findViewById(R.id.user_Nname);
//        user_Nationality = findViewById(R.id.user_Nationality);
//        user_Gender = findViewById(R.id.user_Gender);
//        user_Birthday = findViewById(R.id.user_Birthday);
//        user_Age = findViewById(R.id.user_Age);
//        user_Email = findViewById(R.id.user_Email);
//        user_ContactNumber = findViewById(R.id.user_ContactNumber);
//        user_ecName = findViewById(R.id.user_ecName);
//        user_ecRelationship = findViewById(R.id.user_ecRelationship);
//        user_ecContactNumber = findViewById(R.id.user_ecContactNumber);
//        user_Address = findViewById(R.id.user_Address);
//
//        Back_btn = findViewById(R.id.Back_btn);
//        Edit_btnPPD = findViewById(R.id.Edit_btnPPD);
//
//        // Get the email from Intent
//        Intent intent = getIntent();
//        String email = intent.getStringExtra("email");
//
//        // Fetch user profile data
//        fetchUserProfile();
//
//        // Back button logic
//        Back_btn.setOnClickListener(v -> {
//            Intent backIntent = new Intent(UserProfileDisplay1.this, UserDashboard.class);
//            startActivity(backIntent);
//            finish();
//        });
//
//        // Edit button logic
//        Edit_btn.setOnClickListener(v -> {
//            Intent editIntent = new Intent(UserProfileDisplay1.this, UserProfileInput1.class);
//            startActivity(editIntent);
//        });
//
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//
//    private void fetchUserProfile() {
//        // Retrieve user_id from SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        int userId = sharedPreferences.getInt("id", -1);
//        Log.d("FetchUserProfile", "userID: " + userId);
//
//        if (userId == -1) {
//            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
//            return; // Early exit if user ID is invalid
//        }
//
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("https://hamugaway.scarlet2.io/get_user_profile.php?user_id=" + userId)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                runOnUiThread(() ->
//                        Toast.makeText(UserProfileDisplay1.this, "Failed to retrieve profile: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if (response.body() == null) {
//                    runOnUiThread(() ->
//                            Toast.makeText(UserProfileDisplay1.this, "No response from server.", Toast.LENGTH_SHORT).show());
//                    return;
//                }
//
//                String responseData = response.body().string();
//                Log.d("UserProfileDisplay1", "Response: " + responseData);
//
//                if (responseData.trim().isEmpty()) {
//                    runOnUiThread(() ->
//                            Toast.makeText(UserProfileDisplay1.this, "Empty response from server.", Toast.LENGTH_SHORT).show());
//                    return;
//                }
//
//                try {
//                    JSONObject jsonResponse = new JSONObject(responseData);
//                    if (jsonResponse.has("error")) {
//                        runOnUiThread(() ->
//                                Toast.makeText(UserProfileDisplay1.this, jsonResponse.optString("error"), Toast.LENGTH_SHORT).show());
//                        return;
//                    }
//
//                    // Extract data from the JSON response
//                    String firstName = jsonResponse.optString("first_name", "");
//                    String lastName = jsonResponse.optString("last_name", "");
//                    String nickname = jsonResponse.optString("nickname", "");
//                    String gender = jsonResponse.optString("gender", "");
//                    String birthday = jsonResponse.optString("birthday", "");
//                    String age = jsonResponse.optString("age", "");
//                    String nationality = jsonResponse.optString("nationality", "");
//                    String email = jsonResponse.optString("email", "");
//                    String contactNumber = jsonResponse.optString("phone_number", "");
//                    String ecName = jsonResponse.optString("ec_name", "");
//                    String ecRelationship = jsonResponse.optString("ec_relationship", "");
//                    String ecContactNumber = jsonResponse.optString("ec_mobilenum", "");
//                    String address = jsonResponse.optString("address", "");
//
//
//
//                    // Update UI on the main thread
//                    runOnUiThread(() -> {
//                        pet_Name1.setText(firstName);
//                        user_Name2.setText(firstName + " " + lastName);
//                        user_Nname.setText(nickname);
//                        user_Gender.setText(gender);
//
//                        String formattedBirthday = formatBirthday(birthday);
//                        user_Birthday.setText(formattedBirthday);
//
//                        user_Age.setText(age);
//                        user_Nationality.setText(nationality);
//                        user_Email.setText(email);
//                        user_ContactNumber.setText(contactNumber);
//                        user_ecName.setText(ecName);
//                        user_ecRelationship.setText(ecRelationship);
//                        user_ecContactNumber.setText(ecContactNumber);
//                        user_Address.setText(address);
//
//                    });
//                } catch (JSONException e) {
//                    Log.e("UserProfileDisplay1", "JSON Parsing error: " + responseData, e);
//                    runOnUiThread(() ->
//                            Toast.makeText(UserProfileDisplay1.this, "Error parsing server response.", Toast.LENGTH_SHORT).show());
//                }
//            }
//        });
//    }
//
//    private String formatBirthday(String birthday) {
//        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()); // Adjust format based on the API response
//        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM. dd, yyyy", Locale.getDefault());
//
//        try {
//            // Parse the birthday string to Date object
//            Date date = inputFormat.parse(birthday);
//            // Return the formatted date
//            return outputFormat.format(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return birthday; // Return original string if there's a parsing error
//        }
//    }
//
//
//    private void loadProfileImage(String imageUrl) {
//        // Assuming imageUrl is a valid URL, load the image using a separate thread
//        new Thread(() -> {
//            try {
//                URL url = new URL(imageUrl);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream input = connection.getInputStream();
//                Bitmap myBitmap = BitmapFactory.decodeStream(input);
//                runOnUiThread(() -> user_ProfileView.setImageBitmap(myBitmap));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
//}
