package com.example.mypetshub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignIn extends AppCompatActivity {

    private static final String TAG = "SignIn";
    private TextInputEditText inputUsername, inputPassword;
    private Button btnLogin, btnForgotPassword;
    private TextView txtSignUpClickable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize views
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        txtSignUpClickable = findViewById(R.id.txtSignUpClickable);

        // Set up click listeners
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ForgotPassword activity
                Intent intent = new Intent(SignIn.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        txtSignUpClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SignUp activity
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void performLogin() {
        String username = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignIn.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://hamugaway.scarlet2.io/signin.php")
                .post(new FormBody.Builder()
                        .add("username", username)
                        .add("password", password)
                        .build())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> Toast.makeText(SignIn.this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    // Log status code and headers
                    Log.d(TAG, "Status Code: " + response.code());
                    Log.d(TAG, "Headers: " + response.headers());

                    // Check if the response body is null or empty
                    String responseData = response.body() != null ? response.body().string() : "";
                    Log.d(TAG, "Response Body: " + responseData);

                    if (responseData.isEmpty()) {
                        runOnUiThread(() -> Toast.makeText(SignIn.this, "Empty response from server", Toast.LENGTH_SHORT).show());
                        return;
                    }

                    // Parse the response data
                    JSONObject jsonResponse = new JSONObject(responseData);
                    String status = jsonResponse.getString("status");

                    runOnUiThread(() -> {
                        if ("success".equals(status)) {
                            Intent intent = new Intent(SignIn.this, UserDashboard.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String message = jsonResponse.optString("message", "Unknown error");
                            Toast.makeText(SignIn.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (JSONException e) {
                    runOnUiThread(() -> Toast.makeText(SignIn.this, "Error parsing response: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(SignIn.this, "Unexpected error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}