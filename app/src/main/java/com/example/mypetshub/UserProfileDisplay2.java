package com.example.mypetshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserProfileDisplay2 extends AppCompatActivity {

    private TextView petName3_textView, petBirthday3_textView; // To display first name
    private TextView userEmail2_editText, userMNumber2_EditText, userECName2_editText,
            userECRelationship2_EditText, userECContact2_EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile_display2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        petName3_textView = findViewById(R.id.petName3_textView);
        petBirthday3_textView = findViewById(R.id.petBirthday3_textView);
        userEmail2_editText = findViewById(R.id.userEmail2_editText);
        userMNumber2_EditText = findViewById(R.id.userMNumber2_EditText);
        userECName2_editText = findViewById(R.id.userECName2_editText);
        userECRelationship2_EditText = findViewById(R.id.userECRelationship2_EditText);
        userECContact2_EditText = findViewById(R.id.userECContact2_EditText);

        // Fetch user profile data
        fetchUserProfile();

        // Back Button -> UserProfileDisplay1 page
        ImageButton Back_btn = findViewById(R.id.Back_btn);
        Back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileDisplay2.this, UserProfileDisplay1.class);
            startActivity(intent);
        });

        // Edit Button -> UPI1 page
        ImageButton Edit_btn = findViewById(R.id.Edit_btn);
        Edit_btn.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileDisplay2.this, UserProfileInput2.class);
            startActivity(intent);
        });

        // Previous Button -> UPD1 page
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileDisplay2.this, UserProfileDisplay1.class);
            startActivity(intent);
        });

    }

    private void fetchUserProfile() {
        // Retrieve user_id from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("id", -1);
        Log.d("FetchUserProfile", "userID: " + userId);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://hamugaway.scarlet2.io/get_user_profile.php?user_id=" + userId)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(UserProfileDisplay2.this, "Failed to retrieve profile: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() == null) {
                    runOnUiThread(() ->
                            Toast.makeText(UserProfileDisplay2.this, "No response from server.", Toast.LENGTH_SHORT).show());
                    return;
                }

                String responseData = response.body().string();
                Log.d("EditUserProfile", "Response: " + responseData);

                if (responseData.trim().isEmpty()) {
                    runOnUiThread(() ->
                            Toast.makeText(UserProfileDisplay2.this, "Empty response from server.", Toast.LENGTH_SHORT).show());
                    return;
                }

                try {
                    JSONObject jsonResponse = new JSONObject(responseData);
                    if (jsonResponse.has("error")) {
                        runOnUiThread(() ->
                                Toast.makeText(UserProfileDisplay2.this, jsonResponse.optString("error"), Toast.LENGTH_SHORT).show());
                        return;
                    }

                    String firstName = jsonResponse.optString("first_name", "");
                    String email = jsonResponse.optString("email", "");
                    String phone_number = jsonResponse.optString("phone_number", "");
                    String ec_name = jsonResponse.optString("ec_name", "");
                    String ec_relationship = jsonResponse.optString("ec_relationship", "");
                    String ec_mobilenum = jsonResponse.optString("ec_mobilenum", "");
                    String birthday = jsonResponse.optString("birthday", "");


                    // Update UI on the main thread
                    runOnUiThread(() -> {
                        petName3_textView.setText(firstName);
                        userEmail2_editText.setText(email);
                        userMNumber2_EditText.setText(phone_number);
                        userECName2_editText.setText(ec_name);
                        userECRelationship2_EditText.setText(ec_relationship);
                        petBirthday3_textView.setText(birthday);
                        userECContact2_EditText.setText(ec_mobilenum);
                        petBirthday3_textView.setText(birthday);
                    });
                } catch (JSONException e) {
                    Log.e("EditUserProfile", "JSON Parsing error: " + responseData, e);
                    runOnUiThread(() ->
                            Toast.makeText(UserProfileDisplay2.this, "Error parsing server response.", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}
