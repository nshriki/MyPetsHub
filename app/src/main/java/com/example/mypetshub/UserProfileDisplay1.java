//package com.example.mypetshub;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.media.Image;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class UserProfileDisplay1 extends AppCompatActivity {
//
//    private TextView userNameTextView; // To display first name
//    private EditText userName2EditText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_profile_display1);
//        EdgeToEdge.enable(this);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        // Initialize the views
//        userNameTextView = findViewById(R.id.userName_textView);
//        userName2EditText = findViewById(R.id.userName2_editText);
//
//
//        // Load user info from SharedPreferences
//        loadUserInfo();
//
//
//        // Back Button -> UserDashboard page
//        ImageButton Back_btn = findViewById(R.id.Back_btn);
//        Back_btn.setOnClickListener(v -> {
//            Intent intent = new Intent(UserProfileDisplay1.this, UserDashboard.class);
//            startActivity(intent);
//        });
//
//        // Edit Button -> UPI1 page
//        ImageButton editbtn = findViewById(R.id.Edit_btnUPD1);
//        editbtn.setOnClickListener(v -> {
//            Intent intent = new Intent(UserProfileDisplay1.this, UserProfileInput1.class);
//            startActivity(intent);
//        });
//
//        // Next Button -> UPD2 page
//        Button upd1_nextbtn = findViewById(R.id.nextBtn_UPD1);
//        upd1_nextbtn.setOnClickListener(v -> {
//            Intent intent = new Intent(UserProfileDisplay1.this, UserProfileDisplay2.class);
//            startActivity(intent);
//        });
//
//    }
//
//    private void loadUserInfo() {
//        // Access SharedPreferences to get user details
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        String firstName = sharedPreferences.getString("usersName", "User"); // Default to "User"
//        String lastName = sharedPreferences.getString("last_name", "LastName"); // Default to "LastName"
//
//        // Display first name in TextView
//        userNameTextView.setText(firstName);
//
//        // Display full name in EditText
//        userName2EditText.setText(firstName + " " + lastName);
//    }
//}


//package com.example.mypetshub;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.ImageButton;
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
//public class UserProfileDisplay1 extends AppCompatActivity {
//
//    private TextView userName_textView, userName2_editText, userNationality2_editText,
//            userGender2_editText, userBirthday2_editText, userAge2_editText, userAddress2_editText, petBirthday3_textView, userNName2_editText;
//    private Button edituserprofilepage1_next_btn;
//    private ImageButton Back_btn, Edit_btn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_user_profile_display1);
//
//        // Initialize EditText fields
//
//        // commented on 10/20/2024 11:30pm
//
//        userName_textView = findViewById(R.id.user_Name1);
//        userName2_editText = findViewById(R.id.user_Name2);
//        userNName2_editText = findViewById(R.id.user_Nname);
//        userNationality2_editText = findViewById(R.id.user_Nationality);
//        userGender2_editText = findViewById(R.id.user_Gender);
//        userBirthday2_editText = findViewById(R.id.user_Birthday);
//        userAge2_editText = findViewById(R.id.user_Age);
//        userAddress2_editText = findViewById(R.id.userAddress2_editText);
//        edituserprofilepage1_next_btn = findViewById(R.id.nextBtn_UPD1);
//        petBirthday3_textView = findViewById(R.id.userBirthday_textView);
//
//        Back_btn = findViewById(R.id.Back_btn);
//        Edit_btn = findViewById(R.id.Edit_btnUPD1);
//
//        // Get the email from Intent
//        Intent intent = getIntent();
//        String email = intent.getStringExtra("email"); // Ensure you retrieve it using the same key
//
//        // Fetch user profile data
//        fetchUserProfile();
//
//        // Set the Back button click listener
//        Back_btn.setOnClickListener(v -> {
//            Intent backIntent = new Intent(UserProfileDisplay1.this, UserDashboard.class);
//            startActivity(backIntent);
//            finish();
//        });
//
//        // Set the Edit button click listener
//        Edit_btn.setOnClickListener(v -> {
//            Intent editIntent = new Intent(UserProfileDisplay1.this, UserProfileInput1.class);
//            startActivity(editIntent);
//        });
//
//        // Set the next button click listener
//        edituserprofilepage1_next_btn.setOnClickListener(v -> {
//            Intent nextIntent = new Intent(UserProfileDisplay1.this, UserProfileDisplay2.class);
//            nextIntent.putExtra("email", email); // Pass the email to page 2
//            startActivityForResult(nextIntent, 1); // Use request code 1 to get the result
//        });
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
//                Log.d("EditUserProfile", "Response: " + responseData);
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
//                    String firstName = jsonResponse.optString("first_name", "");
//                    String fullName = jsonResponse.optString("first_name", "") + " " + jsonResponse.optString("last_name", "");
//                    String nationality = jsonResponse.optString("nationality", "");
//                    String gender = jsonResponse.optString("gender", "");
//                    String age = jsonResponse.optString("age", "");
//                    String address = jsonResponse.optString("address", "");
//                    String nickname = jsonResponse.optString("nickname", "");
//                    String birthday = jsonResponse.optString("birthday", "");
//
//                    // Reformat birthday to "MMM. dd, yyyy"
//                    SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()); // Assuming the original format is "yyyy-MM-dd"
//                    SimpleDateFormat targetFormat = new SimpleDateFormat("MMM. dd, yyyy", Locale.getDefault()); // Desired format "MMM. dd, yyyy"
//                    String formattedBirthday = birthday;
//
//                    try {
//                        Date date = originalFormat.parse(birthday);
//                        if (date != null) {
//                            formattedBirthday = targetFormat.format(date);
//                        }
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                        Log.e("BirthdayFormatError", "Error parsing date: " + birthday, e);
//                    }
//
//                    // Update UI on the main thread
//                    final String finalFormattedBirthday = formattedBirthday;
//                    runOnUiThread(() -> {
//                        userName_textView.setText(firstName);
//                        userName2_editText.setText(fullName);
//                        userNName2_editText.setText(nickname.toUpperCase());
//                        userNationality2_editText.setText(nationality);
//                        userGender2_editText.setText(gender);
//                        userBirthday2_editText.setText(finalFormattedBirthday);
//                        userAge2_editText.setText(age);
//                        userAddress2_editText.setText(address);
//                        petBirthday3_textView.setText(finalFormattedBirthday);
//                    });
//                } catch (JSONException e) {
//                    Log.e("EditUserProfile", "JSON Parsing error: " + responseData, e);
//                    runOnUiThread(() ->
//                            Toast.makeText(UserProfileDisplay1.this, "Error parsing server response.", Toast.LENGTH_SHORT).show());
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
//            // Get the updated user data from the returned Intent
//            String nickname = data.getStringExtra("nickname");
//            String birthday = data.getStringExtra("birthday");
//            String phoneNumber = data.getStringExtra("phone_number");
//
//            // Update the UI with the new data
//            userNName2_editText.setText(nickname.toUpperCase());
//            userBirthday2_editText.setText(birthday);
//            petBirthday3_textView.setText(birthday);
//        }
//    }
//}


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

public class UserProfileDisplay1 extends AppCompatActivity {

    private TextView user_Name1, user_Name2, user_Nname, user_Nationality,
            user_Gender, user_Birthday, user_Age, user_Email, user_ContactNumber,
            user_ecName, user_Address, user_ecRelationship, user_ecContactNumber;
    private ImageView user_ProfileView;
    private ImageButton Back_btn, Edit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile_display1);

        // Initialize views
        user_ProfileView = findViewById(R.id.user_ProfileView);
        user_Name1 = findViewById(R.id.user_Name1);
        user_Name2 = findViewById(R.id.user_Name2);
        user_Nname = findViewById(R.id.user_Nname);
        user_Nationality = findViewById(R.id.user_Nationality);
        user_Gender = findViewById(R.id.user_Gender);
        user_Birthday = findViewById(R.id.user_Birthday);
        user_Age = findViewById(R.id.user_Age);
        user_Email = findViewById(R.id.user_Email);
        user_ContactNumber = findViewById(R.id.user_ContactNumber);
        user_ecName = findViewById(R.id.user_ecName);
        user_ecRelationship = findViewById(R.id.user_ecRelationship);
        user_ecContactNumber = findViewById(R.id.user_ecContactNumber);
        user_Address = findViewById(R.id.user_Address);

        Back_btn = findViewById(R.id.Back_btn);
        Edit_btn = findViewById(R.id.Edit_btnUPD1);

        // Get the email from Intent
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        // Fetch user profile data
        fetchUserProfile();

        // Back button logic
        Back_btn.setOnClickListener(v -> {
            Intent backIntent = new Intent(UserProfileDisplay1.this, UserDashboard.class);
            startActivity(backIntent);
            finish();
        });

        // Edit button logic
        Edit_btn.setOnClickListener(v -> {
            Intent editIntent = new Intent(UserProfileDisplay1.this, UserProfileInput1.class);
            startActivity(editIntent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fetchUserProfile() {
        // Retrieve user_id from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("id", -1);
        Log.d("FetchUserProfile", "userID: " + userId);

        if (userId == -1) {
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
            return; // Early exit if user ID is invalid
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://hamugaway.scarlet2.io/get_user_profile.php?user_id=" + userId)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(UserProfileDisplay1.this, "Failed to retrieve profile: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() == null) {
                    runOnUiThread(() ->
                            Toast.makeText(UserProfileDisplay1.this, "No response from server.", Toast.LENGTH_SHORT).show());
                    return;
                }

                String responseData = response.body().string();
                Log.d("UserProfileDisplay1", "Response: " + responseData);

                if (responseData.trim().isEmpty()) {
                    runOnUiThread(() ->
                            Toast.makeText(UserProfileDisplay1.this, "Empty response from server.", Toast.LENGTH_SHORT).show());
                    return;
                }

                try {
                    JSONObject jsonResponse = new JSONObject(responseData);
                    if (jsonResponse.has("error")) {
                        runOnUiThread(() ->
                                Toast.makeText(UserProfileDisplay1.this, jsonResponse.optString("error"), Toast.LENGTH_SHORT).show());
                        return;
                    }

                    // Extract data from the JSON response
                    String firstName = jsonResponse.optString("first_name", "");
                    String lastName = jsonResponse.optString("last_name", "");
                    String nickname = jsonResponse.optString("nickname", "");
                    String gender = jsonResponse.optString("gender", "");
                    String birthday = jsonResponse.optString("birthday", "");
                    String age = jsonResponse.optString("age", "");
                    String nationality = jsonResponse.optString("nationality", "");
                    String email = jsonResponse.optString("email", "");
                    String contactNumber = jsonResponse.optString("phone_number", "");
                    String ecName = jsonResponse.optString("ec_name", "");
                    String ecRelationship = jsonResponse.optString("ec_relationship", "");
                    String ecContactNumber = jsonResponse.optString("ec_mobilenum", "");
                    String address = jsonResponse.optString("address", "");



                    // Update UI on the main thread
                    runOnUiThread(() -> {
                        user_Name1.setText(firstName);
                        user_Name2.setText(firstName + " " + lastName);
                        user_Nname.setText(nickname);
                        user_Gender.setText(gender);

                        String formattedBirthday = formatBirthday(birthday);
                        user_Birthday.setText(formattedBirthday);

                        user_Age.setText(age);
                        user_Nationality.setText(nationality);
                        user_Email.setText(email);
                        user_ContactNumber.setText(contactNumber);
                        user_ecName.setText(ecName);
                        user_ecRelationship.setText(ecRelationship);
                        user_ecContactNumber.setText(ecContactNumber);
                        user_Address.setText(address);

                    });
                } catch (JSONException e) {
                    Log.e("UserProfileDisplay1", "JSON Parsing error: " + responseData, e);
                    runOnUiThread(() ->
                            Toast.makeText(UserProfileDisplay1.this, "Error parsing server response.", Toast.LENGTH_SHORT).show());
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
                runOnUiThread(() -> user_ProfileView.setImageBitmap(myBitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
