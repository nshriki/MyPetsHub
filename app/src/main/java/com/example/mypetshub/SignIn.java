//package com.example.mypetshub;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.textfield.TextInputEditText;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class SignIn extends AppCompatActivity {
//
//    private static final String TAG = "SignIn";
//    private TextInputEditText inputEmailSignIn, inputPassword;
//    private Button btnLogin, btnForgotPassword;
//    private TextView txtSignUpClickable;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_in);
//
//        // Initialize views
//        inputEmailSignIn = findViewById(R.id.inputEmailSignIn);
//        inputPassword = findViewById(R.id.inputPassword);
//        btnLogin = findViewById(R.id.btnLogin);
//        btnForgotPassword = findViewById(R.id.btnForgotPassword);
//        txtSignUpClickable = findViewById(R.id.txtSignUpClickable);
//
//        // Set up click listeners
//        btnLogin.setOnClickListener(v -> performLogin());
//        btnForgotPassword.setOnClickListener(v -> {
//            Intent intent = new Intent(SignIn.this, ForgotPassword.class);
//            startActivity(intent);
//        });
//        txtSignUpClickable.setOnClickListener(v -> {
//            Intent intent = new Intent(SignIn.this, SignUp.class);
//            startActivity(intent);
//        });
//    }
//
//    private void performLogin() {
//        String email = inputEmailSignIn.getText().toString().trim();
//        String password = inputPassword.getText().toString().trim();
//
//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(SignIn.this, "Please enter both email and password.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("https://hamugaway.scarlet2.io/signin.php")  // URL for the server endpoint
//                .post(new FormBody.Builder()
//                        .add("email", email)
//                        .add("password", password)
//                        .build())
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                runOnUiThread(() -> {
//                    Toast.makeText(SignIn.this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    Log.e(TAG, "Login failed", e);
//                });
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if (response.body() == null) {
//                    runOnUiThread(() -> Toast.makeText(SignIn.this, "No response from server.", Toast.LENGTH_SHORT).show());
//                    return;
//                }
//
//                String responseData = response.body().string();
//                Log.d(TAG, "Response: " + responseData);
//
//                if (responseData.trim().isEmpty()) {
//                    Log.e(TAG, "Empty response from server.");
//                    runOnUiThread(() -> Toast.makeText(SignIn.this, "Empty response from server.", Toast.LENGTH_SHORT).show());
//                    return;
//                }
//
//                try {
//                    JSONObject jsonResponse = new JSONObject(responseData);
//                    String status = jsonResponse.optString("status", "error");
//                    String message = jsonResponse.optString("message", "Unknown error occurred.");
//
//                    runOnUiThread(() -> {
//                        if ("success".equals(status)) {
//                            Intent intent = new Intent(SignIn.this, UserDashboard.class);
//                            startActivity(intent);
//                            finish();
//                        } else if ("unverified".equals(status)) {
//                            Toast.makeText(SignIn.this, "Please verify your email before logging in.", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(SignIn.this, message, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } catch (JSONException e) {
//                    Log.e(TAG, "JSON Parsing error: " + responseData, e);
//                    runOnUiThread(() -> Toast.makeText(SignIn.this, "Error parsing server response.", Toast.LENGTH_SHORT).show());
//                }
//            }
//        });
//    }
//}



//package com.example.mypetshub;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.textfield.TextInputEditText;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class SignIn extends AppCompatActivity {
//
//    private static final String TAG = "SignIn";
//    private TextInputEditText inputEmailSignIn, inputPassword;
//    private Button btnLogin, btnForgotPassword;
//    private TextView txtSignUpClickable;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_in);
//
//        // Initialize views
//        inputEmailSignIn = findViewById(R.id.inputEmailSignIn);
//        inputPassword = findViewById(R.id.inputPassword);
//        btnLogin = findViewById(R.id.btnLogin);
//        btnForgotPassword = findViewById(R.id.btnForgotPassword);
//        txtSignUpClickable = findViewById(R.id.txtSignUpClickable);
//
//        // Set up click listeners
//        btnLogin.setOnClickListener(v -> performLogin());
//        btnForgotPassword.setOnClickListener(v -> {
//            Intent intent = new Intent(SignIn.this, ForgotPassword.class);
//            startActivity(intent);
//        });
//        txtSignUpClickable.setOnClickListener(v -> {
//            Intent intent = new Intent(SignIn.this, SignUp.class);
//            startActivity(intent);
//        });
//    }
//
//    private void performLogin() {
//        String email = inputEmailSignIn.getText().toString().trim();
//        String password = inputPassword.getText().toString().trim();
//
//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(SignIn.this, "Please enter both email and password.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("https://hamugaway.scarlet2.io/signin.php")  // URL for the server endpoint
//                .post(new FormBody.Builder()
//                        .add("email", email)
//                        .add("password", password)
//                        .build())
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                runOnUiThread(() -> {
//                    Toast.makeText(SignIn.this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    Log.e(TAG, "Login failed", e);
//                });
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if (response.body() == null) {
//                    runOnUiThread(() -> Toast.makeText(SignIn.this, "No response from server.", Toast.LENGTH_SHORT).show());
//                    return;
//                }
//
//                String responseData = response.body().string();
//                Log.d(TAG, "Response: " + responseData);
//
//                if (responseData.trim().isEmpty()) {
//                    Log.e(TAG, "Empty response from server.");
//                    runOnUiThread(() -> Toast.makeText(SignIn.this, "Empty response from server.", Toast.LENGTH_SHORT).show());
//                    return;
//                }
//
//                try {
//                    JSONObject jsonResponse = new JSONObject(responseData);
//                    String status = jsonResponse.optString("status", "error");
//                    String message = jsonResponse.optString("message", "Unknown error occurred.");
//
//                    runOnUiThread(() -> {
//                        if ("success".equals(status)) {
//                            // Save login state
//                            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putBoolean("isLoggedIn", true); // Save the login state
//                            editor.apply();
//
//                            Intent intent = new Intent(SignIn.this, UserDashboard.class);
//                            startActivity(intent);
//                            finish();
//                        } else if ("unverified".equals(status)) {
//                            Toast.makeText(SignIn.this, "Please verify your email before logging in.", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(SignIn.this, message, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } catch (JSONException e) {
//                    Log.e(TAG, "JSON Parsing error: " + responseData, e);
//                    runOnUiThread(() -> Toast.makeText(SignIn.this, "Error parsing server response.", Toast.LENGTH_SHORT).show());
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
    private TextInputEditText inputEmailSignIn, inputPassword;
    private Button btnLogin, btnForgotPassword;
    private TextView txtSignUpClickable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize views
        inputEmailSignIn = findViewById(R.id.inputEmailSignIn);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        txtSignUpClickable = findViewById(R.id.txtSignUpClickable);

        // Set up click listeners
        btnLogin.setOnClickListener(v -> performLogin());
        btnForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(SignIn.this, ForgotPassword.class);
            startActivity(intent);
        });
        txtSignUpClickable.setOnClickListener(v -> {
            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);
        });
    }

    private void performLogin() {
        String email = inputEmailSignIn.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignIn.this, "Please enter both email and password.", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://hamugaway.scarlet2.io/signin.php")  // URL for the server endpoint
                .post(new FormBody.Builder()
                        .add("email", email)
                        .add("password", password)
                        .build())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(SignIn.this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Login failed", e);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() == null) {
                    runOnUiThread(() -> Toast.makeText(SignIn.this, "No response from server.", Toast.LENGTH_SHORT).show());
                    return;
                }

                String responseData = response.body().string();
                Log.d(TAG, "Response: " + responseData);

                if (responseData.trim().isEmpty()) {
                    Log.e(TAG, "Empty response from server.");
                    runOnUiThread(() -> Toast.makeText(SignIn.this, "Empty response from server.", Toast.LENGTH_SHORT).show());
                    return;
                }

                try {
                    JSONObject jsonResponse = new JSONObject(responseData);
                    String status = jsonResponse.optString("status", "error");
                    String message = jsonResponse.optString("message", "Unknown error occurred.");

                    // Get the user's name from the response (assuming it is sent as "name")
                    String userName = jsonResponse.optString("first_name", "User");
                    String lastName = jsonResponse.optString("last_name", "LastName");
                    int userId = jsonResponse.optInt("id");

                    runOnUiThread(() -> {
                        if ("success".equals(status)) {
                            // Save login state and user's name
                            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("isLoggedIn", true); // Save the login state
                            editor.putString("usersName", userName); // Save the user's name
                            editor.putString("last_name", lastName); // Save the user's last name
                            editor.putInt("id", userId);

                            editor.apply();

                            Log.d(TAG, "Saving user name: " + userName);
                            Log.d(TAG, "Saving user last name: " + lastName);
                            Log.d(TAG, "Response: " + responseData);
                            Log.d(TAG, "Saving user ID: " + userId); // Log the user ID

                            Intent intent = new Intent(SignIn.this, UserDashboard.class);
                            startActivity(intent);
                            finish();
                        } else if ("unverified".equals(status)) {
                            Toast.makeText(SignIn.this, "Please verify your email before logging in.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SignIn.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    Log.e(TAG, "JSON Parsing error: " + responseData, e);
                    runOnUiThread(() -> Toast.makeText(SignIn.this, "Error parsing server response.", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}
