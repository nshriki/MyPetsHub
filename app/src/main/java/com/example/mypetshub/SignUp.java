package com.example.mypetshub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import android.content.SharedPreferences;


public class SignUp extends AppCompatActivity {

    private EditText inputFName, inputLName, inputEmail, inputPassword;
    private CheckBox cbTermsCondi;
    private Button btnLogin;
    private TextView txtTermsConditionsBold, txtSignUpClickable;
    private SharedPreferences sharedPreferences;

    private static final String SIGNUP_URL = "https://hamugaway.scarlet2.io/signup.php";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-_.+]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Initialize views
        inputFName = findViewById(R.id.inputFName);
        inputLName = findViewById(R.id.inputLName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        cbTermsCondi = findViewById(R.id.cbTermsCondi);
        btnLogin = findViewById(R.id.btnLogin);
        txtTermsConditionsBold = findViewById(R.id.txtTermsConditionsBold);
        txtSignUpClickable = findViewById(R.id.txtSignUpClickable);

        // Disable the button initially
        btnLogin.setEnabled(false);

        // Enable the button only if the checkbox is checked
        cbTermsCondi.setOnCheckedChangeListener((buttonView, isChecked) -> btnLogin.setEnabled(isChecked));

        // Set click listener for the "Terms & Conditions" text view
        txtTermsConditionsBold.setOnClickListener(view -> openTermsConditions());

        // Set click listener for the "Sign In" text view
        txtSignUpClickable.setOnClickListener(view -> openSignInPage());

        // Set click listener for the Sign Up button
        btnLogin.setOnClickListener(null);
        btnLogin.setOnClickListener(view -> handleSignUp());
    }

    private void handleSignUp() {
        String firstName = inputFName.getText().toString().trim();
        String lastName = inputLName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        // Validate inputs with specific error messages
        if (firstName.isEmpty()) {
            inputFName.setError("First Name is required.");
            return;
        }
        if (lastName.isEmpty()) {
            inputLName.setError("Last Name is required.");
            return;
        }
        if (!isValidEmail(email)) {
            inputEmail.setError("Invalid email address.");
            return;
        }
        if (!isValidPassword(password)) {
            inputPassword.setError("Password must be at least 6 characters long.");
            return;
        }

        btnLogin.setEnabled(false);

        // Send the data to the server
        Log.d("SignUp", "send signup before request");
        sendSignUpRequest(firstName, lastName, email, password);
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    private void sendSignUpRequest(String firstName, String lastName, String email, String password) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SIGNUP_URL,
                response -> {
                    Log.e("SignUp", "send signup request");
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        int success = jsonResponse.getInt("success");
                        String message = jsonResponse.getString("message");
                        Log.d("SignUp", "success data: " + success);

                        if (success == 1) {
                            // Save user_id in SharedPreferences
                            String userId = jsonResponse.getString("id");
                            saveUserId(userId);

                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                            // Redirect to login page after a short delay
                            // new Handler().postDelayed(this::openSignInPage, 2000);
                        } else {
                            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(this, "An unexpected error occurred. Please try again.", Toast.LENGTH_LONG).show();
                    } finally {
                        btnLogin.setEnabled(true);
                    }


                },
                error -> {
                    Toast.makeText(this, "Network error. Please check your connection.", Toast.LENGTH_LONG).show();
                    Log.e("SignUpError", "Network/Server Error: " + error.getMessage());
                    btnLogin.setEnabled(true);
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("first_name", firstName);
                params.put("last_name", lastName);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        stringRequest.setShouldCache(false);

        queue.add(stringRequest);
    }


    private void openSignInPage() {
        Intent intent = new Intent(SignUp.this, SignIn.class);
        startActivity(intent);
        finish();
    }

    private void openTermsConditions() {
        Intent intent = new Intent(SignUp.this, TermsCondition.class);
        startActivity(intent);
    }

    private void saveUserId(String userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", userId);
        editor.apply();
    }
}
