package com.example.mypetshub;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

    private EditText checkInDate, checkInTime, checkOutDate, checkOutTime;
    private TextView smallTxt, mediumTxt, largeTxt, xlargeTxt, catsTxt;
    private Spinner spinnerChoosePet;
    private ArrayList<String> petNames = new ArrayList<>();
    private ImageButton btnContinueBoarding, Back_btn_boarding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_boarding);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnContinueBoarding = findViewById(R.id.btnContinueBoarding);
        btnContinueBoarding.setOnClickListener(v -> {
            Intent intent = new Intent(PetBoarding.this, PetBoardingConfirmation.class);
            startActivity(intent);
        });

        checkInDate = findViewById(R.id.editTextText1);
        checkInTime = findViewById(R.id.editTextText3);
        checkOutDate = findViewById(R.id.editTextText2);
        checkOutTime = findViewById(R.id.editTextText4);

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
        findViewById(R.id.smallService).setOnClickListener(v -> selectServiceType((LinearLayout) v));
        findViewById(R.id.mediumService).setOnClickListener(v -> selectServiceType((LinearLayout) v));
        findViewById(R.id.largeService).setOnClickListener(v -> selectServiceType((LinearLayout) v));
        findViewById(R.id.xlargeService).setOnClickListener(v -> selectServiceType((LinearLayout) v));
        findViewById(R.id.catsService).setOnClickListener(v -> selectServiceType((LinearLayout) v));

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

        // Validate inputs with specific error messages
        if (checkInString.isEmpty()) {
            checkInDate.setError("Check-in date is required.");
            return;
        }
        if (checkOutString.isEmpty()) {
            checkOutDate.setError("Check-out date is required.");
            return;
        }
        if (timeInString.isEmpty()) {
            checkInTime.setError("Check-in time is required.");
            return;
        }
        if (timeOutString.isEmpty()) {
            checkOutTime.setError("Check-out time is required.");
            return;
        }

        btnContinueBoarding.setEnabled(false);
        saveBoarding(checkInString, checkOutString, timeInString, timeOutString);

    }

    private void saveBoarding(String checkInString, String checkOutString, String timeInString, String timeOutString) {
    }

    private void fetchPetNames() {
        // Fetch pet names from the server and populate the spinner
        new Thread(this::run).start();
    }

    private void selectServiceType(LinearLayout selectedService) {
        // Reset all text colors to white
        resetServiceColors();

        // Change text colors of all TextViews in the selected service to gold
        for (int i = 0; i < selectedService.getChildCount(); i++) {
            View child = selectedService.getChildAt(i);
            if (child instanceof TextView) {
                ((TextView) child).setTextColor(Color.parseColor("#FFD700")); // gold color
            }
        }
    }

    private void resetServiceColors() {
        resetColorForService(R.id.smalltxt1);
        resetColorForService(R.id.mediumtxt1);
        resetColorForService(R.id.largetxt1);
        resetColorForService(R.id.xlargetxt1);
        resetColorForService(R.id.catstxt1);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
