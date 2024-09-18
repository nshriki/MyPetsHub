package com.example.mypetshub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private EditText inputUsername, inputEmail, inputPassword;
    private CheckBox cbTermsCondi;
    private Button btnLogin;
    private TextView txtTermsConditionsBold, txtSignUpClickable;

    private static final String SIGNUP_URL = "https://hamugaway.scarlet2.io/signup.php";

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-_.+]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        inputUsername = findViewById(R.id.inputUsername);
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
        btnLogin.setOnClickListener(view -> handleSignUp());
    }

    private void openTermsConditions() {
        Intent intent = new Intent(SignUp.this, TermsCondition.class);
        startActivity(intent);
    }

    private void openSignInPage() {
        Intent intent = new Intent(SignUp.this, SignIn.class);
        startActivity(intent);
    }

    private void handleSignUp() {
        String username = inputUsername.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        // Validate inputs
        if (!isValidUsername(username)) {
            inputUsername.setError("Username must be at least 6 characters long");
            return;
        }
        if (!isValidEmail(email)) {
            inputEmail.setError("Invalid email address");
            return;
        }
        if (!isValidPassword(password)) {
            inputPassword.setError("Password must be at least 6 characters long");
            return;
        }

        // Send the data to the server
        sendSignUpRequest(username, email, password);
    }

    private boolean isValidUsername(String username) {
        return username.length() >= 6;
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    private void sendSignUpRequest(String username, String email, String password) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SIGNUP_URL,
                response -> {
                    // Handle the response from the server
                    if (response.contains("New record created successfully")) {
                        Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                        // Clear input fields and checkbox
                        inputUsername.setText("");
                        inputEmail.setText("");
                        inputPassword.setText("");
                        cbTermsCondi.setChecked(false);
                        // Navigate to the Sign In page
                        openSignInPage();
                    } else {
                        Toast.makeText(this, "Sign Up Failed: " + response, Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    // Handle any error from the server
                    Toast.makeText(this, "Network Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        queue.add(stringRequest);
    }
}
