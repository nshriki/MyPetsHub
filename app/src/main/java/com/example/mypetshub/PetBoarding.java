package com.example.mypetshub;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class PetBoarding extends AppCompatActivity {

    private EditText checkInDate, checkInTime, checkOutDate, checkOutTime, specialInstruction;
    private TextView smallTxt, mediumTxt, largeTxt, xlargeTxt, catsTxt;
    private Spinner spinnerChoosePet;
    private ArrayList<String> petNames = new ArrayList<>();
    private ImageButton btnContinueBoarding, Back_btn_boarding;
    private String selectedServiceType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_boarding);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnContinueBoarding = findViewById(R.id.btnContinueBoarding);
        btnContinueBoarding.setOnClickListener(v -> handleContinueButton()
//            Intent intent = new Intent(PetBoarding.this, PetBoardingConfirmation.class);
//            startActivity(intent);
        );

        checkInDate = findViewById(R.id.editTextText1);
        checkInTime = findViewById(R.id.editTextText3);
        checkOutDate = findViewById(R.id.editTextText2);
        checkOutTime = findViewById(R.id.editTextText4);
        specialInstruction = findViewById(R.id.editTextTextMultiLine2);

        checkInDate.setOnClickListener(v -> showDatePickerDialog(checkInDate));
        checkInTime.setOnClickListener(v -> showTimePickerDialog(checkInTime));
        checkOutDate.setOnClickListener(v -> showDatePickerDialog(checkOutDate));
        checkOutTime.setOnClickListener(v -> showTimePickerDialog(checkOutTime));

        // Initialize TextViews
        smallTxt = findViewById(R.id.smalltxt1);
        mediumTxt = findViewById(R.id.mediumtxt1);
        largeTxt = findViewById(R.id.largetxt1);
        xlargeTxt = findViewById(R.id.xlargetxt1);
        catsTxt = findViewById(R.id.catstxt1);

        // Initialize Spinner
        spinnerChoosePet = findViewById(R.id.spinnerChoosePet);
        fetchPetNames(); // Fetch pet names from the server

        // Set OnClickListeners for service type layout
//        findViewById(R.id.smallService).setOnClickListener(v -> selectServiceType((LinearLayout) v));
//        findViewById(R.id.mediumService).setOnClickListener(v -> selectServiceType((LinearLayout) v));
//        findViewById(R.id.largeService).setOnClickListener(v -> selectServiceType((LinearLayout) v));
//        findViewById(R.id.xlargeService).setOnClickListener(v -> selectServiceType((LinearLayout) v));
//        findViewById(R.id.catsService).setOnClickListener(v -> selectServiceType((LinearLayout) v));

        findViewById(R.id.smallService).setOnClickListener(v -> selectServiceType("small"));
        findViewById(R.id.mediumService).setOnClickListener(v -> selectServiceType("medium"));
        findViewById(R.id.largeService).setOnClickListener(v -> selectServiceType("large"));
        findViewById(R.id.xlargeService).setOnClickListener(v -> selectServiceType("x-large"));
        findViewById(R.id.catsService).setOnClickListener(v -> selectServiceType("cats"));


        // Set OnItemSelectedListener for spinner
        spinnerChoosePet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle pet selection
                String selectedPet = petNames.get(position);
                // Do something with the selected pet
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });

        // get check in and out date from user dashboard
        Intent intent = getIntent();
        String checkIn = intent.getStringExtra("CHECK_IN_DATE");
        String checkOut = intent.getStringExtra("CHECK_OUT_DATE");

        // Set the received dates into the EditText fields
        if (checkIn != null) {
            checkInDate.setText(checkIn);
        }
        if (checkOut != null) {
            checkOutDate.setText(checkOut);
        }

        btnContinueBoarding.setOnClickListener(null);
        btnContinueBoarding.setOnClickListener(view -> handleContinueButton());

        Back_btn_boarding = findViewById(R.id.Back_btn);
        Back_btn_boarding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_backbtn_boarding = new Intent(PetBoarding.this, UserDashboard.class);
                startActivity(intent_backbtn_boarding);
            }
        });
    }

    private void handleContinueButton() {
        String checkInString = checkInDate.getText().toString();
        String checkOutString = checkOutDate.getText().toString();
        String timeInString = checkInTime.getText().toString();
        String timeOutString = checkOutTime.getText().toString();
        String specialInstructionString = specialInstruction.getText().toString();
        String selectedPet = spinnerChoosePet.getSelectedItem() != null ? spinnerChoosePet.getSelectedItem().toString() : "";

        if (selectedServiceType.isEmpty() || selectedPet.isEmpty() || checkInString.isEmpty() || checkOutString.isEmpty()
                || timeInString.isEmpty() || timeOutString.isEmpty()) {
            Toast.makeText(this, "All fields are required except special instructions.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(PetBoarding.this, PetBoardingConfirmation.class);
        intent.putExtra("SERVICE_TYPE", selectedServiceType);
        intent.putExtra("PET_NAME", selectedPet);
        intent.putExtra("CHECK_IN_DATE", checkInString);
        intent.putExtra("CHECK_OUT_DATE", checkOutString);
        intent.putExtra("CHECK_IN_TIME", timeInString);
        intent.putExtra("CHECK_OUT_TIME", timeOutString);
        intent.putExtra("SPECIAL_INSTRUCTION", specialInstructionString);


        startActivity(intent);
    }

//        private void saveBoarding(String checkInString, String checkOutString, String timeInString, String timeOutString) {
//    }

    private void fetchPetNames() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("id", -1);

        if (userId == -1) {
            // Handle case where user_id is not found (user not logged in)
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }
        // Create the API URL with user ID as a query parameter
        String apiUrl = "https://hamugaway.scarlet2.io/get_pet_boarding.php?user_id=" + userId;

        new Thread(() -> {
            try {
                // Send the HTTP GET request
                String response = NetworkUtils.getResponseFromUrl(apiUrl);

                // Parse the JSON response
                JSONArray jsonArray = new JSONArray(response);
                petNames.clear(); // Clear the existing list to avoid duplicates

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject pet = jsonArray.getJSONObject(i);
                    String petName = pet.getString("pet_name");
                    petNames.add(petName); // Add the pet name to the list
                }

                // Update the Spinner on the UI thread
                runOnUiThread(() -> {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_color_layout, petNames);
                    adapter.setDropDownViewResource(R.layout.spinner_layout_textview);
                    spinnerChoosePet.setAdapter(adapter);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


//    private void selectServiceType(LinearLayout selectedService) {
//        // Reset all text colors to white
//        resetServiceColors();
//
//        // Change text colors of all TextViews in the selected service to gold
//        for (int i = 0; i < selectedService.getChildCount(); i++) {
//            View child = selectedService.getChildAt(i);
//            if (child instanceof TextView) {
//                ((TextView) child).setTextColor(Color.parseColor("#FFD700")); // gold color
//            }
//        }
//    }
//
//    private void resetServiceColors() {
//        resetColorForService(R.id.smalltxt1);
//        resetColorForService(R.id.mediumtxt1);
//        resetColorForService(R.id.largetxt1);
//        resetColorForService(R.id.xlargetxt1);
//        resetColorForService(R.id.catstxt1);
//    }

    private void selectServiceType(String serviceType) {
        selectedServiceType = serviceType;
        resetServiceColors();
        int selectedId;

        switch (serviceType) {
            case "small":
                selectedId = R.id.smalltxt1;
                break;
            case "medium":
                selectedId = R.id.mediumtxt1;
                break;
            case "large":
                selectedId = R.id.largetxt1;
                break;
            case "x-large":
                selectedId = R.id.xlargetxt1;
                break;
            case "cats":
                selectedId = R.id.catstxt1;
                break;
            default:
                return;
        }

        TextView selectedView = findViewById(selectedId);
        selectedView.setTextColor(Color.parseColor("#FFD700"));
    }

    private void resetServiceColors() {
        int[] ids = {R.id.smalltxt1, R.id.mediumtxt1, R.id.largetxt1, R.id.xlargetxt1, R.id.catstxt1};
        for (int id : ids) {
            TextView textView = findViewById(id);
            textView.setTextColor(Color.WHITE);
        }
    }

    private void resetColorForService(int serviceId) {
        LinearLayout serviceLayout = (LinearLayout) findViewById(serviceId).getParent();
        for (int i = 0; i < serviceLayout.getChildCount(); i++) {
            View child = serviceLayout.getChildAt(i);
            if (child instanceof TextView) {
                ((TextView) child).setTextColor(Color.WHITE);
            }
        }
    }

    private void showDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Create a calendar instance and set the selected date
                    Calendar selectedCalendar = Calendar.getInstance();
                    selectedCalendar.set(selectedYear, selectedMonth, selectedDay);

                    // Format the date as "MMM. dd, yyyy"
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM. dd, yyyy", Locale.getDefault());
                    String formattedDate = dateFormat.format(selectedCalendar.getTime());

                    // Set the formatted date to the EditText
                    editText.setText(formattedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void showTimePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, selectedHour, selectedMinute) -> {
                    String amPm = (selectedHour >= 12) ? "PM" : "AM";
                    selectedHour = (selectedHour > 12) ? selectedHour - 12 : selectedHour;
                    selectedHour = (selectedHour == 0) ? 12 : selectedHour;

                    String selectedTime = String.format("%02d:%02d %s", selectedHour, selectedMinute, amPm);
                    editText.setText(selectedTime);
                },
                hour, minute, false
        );
        timePickerDialog.show();
    }

    private void run() {
        try {
            // Assume we have a method to call your PHP script and get response
            String response;
            response = NetworkUtils.getResponseFromUrl("https://hamugaway.scarlet2.io/fetchingpet.php");
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject pet = jsonArray.getJSONObject(i);
                petNames.add(pet.getString("pet_name"));
            }
            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, petNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerChoosePet.setAdapter(adapter);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PetBoarding.this, UserDashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);  // Optional: This clears the top of the stack
        startActivity(intent);
        finish();  // Optional: Call finish if you want to close the current activity
    }


}
