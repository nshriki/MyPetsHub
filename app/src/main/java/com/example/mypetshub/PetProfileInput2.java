package com.example.mypetshub;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PetProfileInput2 extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;

    private EditText input_pet_Name, input_pet_weight, input_pet_Breed, input_pet_Birthday, input_pet_Age,
            input_pet_Allergies, input_pet_PreExistConditions, input_pet_VeterinaryClinic, input_pet_VeterinaryDoctor, input_pet_OtherVaccine2;
    private Spinner input_pet_Gender, input_pet_Species;
    private CheckBox input_pet_RabbiesVaccine, input_pet_FiveinOneVaccine, input_pet_OtherVaccine1;
    private ImageView vaccineCardImageView, input_pet_ProfileView;
    private ImageButton confirmBtn, backBtnPPI2;
    private SharedPreferences sharedPreferences;
    private int userId, petId;
    private OkHttpClient client;

    private Uri petImageUri;
    private Uri vaccineCardImageUri;
//    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile_input2);

        // Initialize views
        initializeFields();

        // Load user ID from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id", -1);

        // Check if userId is valid
        if (userId == -1) {
            Toast.makeText(this, "User ID not found. Please log in again.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get petId if editing existing profile
        Intent intent = getIntent();
//        petId = intent.getIntExtra("ped_id", -1);

        if (intent.hasExtra("pet_id")) {
            petId = intent.getIntExtra("pet_id", -1);
        }

        backBtnPPI2 = findViewById(R.id.backBtnUPI1);
        backBtnPPI2.setOnClickListener(view -> {
            Intent backBtnPPI_Intent = new Intent(PetProfileInput2.this, PetProfile.class);
            startActivity(backBtnPPI_Intent);
        });

        loadPetData(userId, petId);

        // Date picker for birthday
        input_pet_Birthday.setOnClickListener(v -> showDatePickerDialog(input_pet_Birthday));

        // Set click listener for confirm button
        confirmBtn.setOnClickListener(v -> savePetDataToDatabase());

        // Set click listener for pet image
        input_pet_ProfileView.setOnClickListener(v -> pickImage(PICK_IMAGE));

        // Set click listener for vaccine card image
        vaccineCardImageView.setOnClickListener(v -> pickImage(PICK_IMAGE + 1));

        input_pet_OtherVaccine1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            input_pet_OtherVaccine2.setEnabled(isChecked);
            if (!isChecked) {
                input_pet_OtherVaccine2.setText("");
            }
        });
    }

    private void initializeFields() {
        input_pet_Name = findViewById(R.id.input_pet_Name);
        input_pet_Gender = findViewById(R.id.input_pet_Gender);
        input_pet_weight = findViewById(R.id.input_pet_weight);
        input_pet_Species = findViewById(R.id.input_pet_Species);
        input_pet_Breed = findViewById(R.id.input_pet_Breed);
        input_pet_Birthday = findViewById(R.id.input_pet_Birthday);
        input_pet_Age = findViewById(R.id.input_pet_Age);
        input_pet_Allergies = findViewById(R.id.input_pet_Allergies);
        input_pet_PreExistConditions = findViewById(R.id.input_pet_PreExistConditions);
        input_pet_VeterinaryClinic = findViewById(R.id.input_pet_VeterinaryClinic);
        input_pet_VeterinaryDoctor = findViewById(R.id.input_pet_VeterinaryDoctor);
        input_pet_RabbiesVaccine = findViewById(R.id.input_pet_RabbiesVaccine);
        input_pet_FiveinOneVaccine = findViewById(R.id.input_pet_FiveinOneVaccine);
        input_pet_OtherVaccine1 = findViewById(R.id.input_pet_OtherVaccine1);
        input_pet_OtherVaccine2 = findViewById(R.id.input_pet_OtherVaccine2);
        confirmBtn = findViewById(R.id.confirmBtn);
        input_pet_ProfileView = findViewById(R.id.input_pet_ProfileView);
        vaccineCardImageView = findViewById(R.id.vaccineCardImageView);

        setFieldsNonEditable();

        // Set up Gender Spinner
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(
                this, R.array.array_gender_pet, R.layout.spinner_color_layout);
        genderAdapter.setDropDownViewResource(R.layout.spinner_layout_textview);
        input_pet_Gender.setAdapter(genderAdapter);

        // Set up Species Spinner
        ArrayAdapter<CharSequence> speciesAdapter = ArrayAdapter.createFromResource(
                this, R.array.array_species, R.layout.spinner_color_layout);
        speciesAdapter.setDropDownViewResource(R.layout.spinner_layout_textview);
        input_pet_Species.setAdapter(speciesAdapter);

        // Initialize OkHttpClient
        client = new OkHttpClient();
    }

    private void setFieldsNonEditable() {
        input_pet_Age.setFocusable(false);
    }

    private void pickImage(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (requestCode == PICK_IMAGE) {
                petImageUri = imageUri; // Save pet image URI
                input_pet_ProfileView.setImageURI(petImageUri); // Set pet image
            } else if (requestCode == PICK_IMAGE + 1) {
                vaccineCardImageUri = imageUri; // Save vaccine card image URI
                vaccineCardImageView.setImageURI(vaccineCardImageUri); // Set vaccine card image
            }
        }
    }


    private void loadPetData(int userId, int petId) {
        String url = "https://hamugaway.scarlet2.io/get_pet_profile.php?user_id=" + userId + "&pet_id=" + petId;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(PetProfileInput2.this, "Failed to fetch pet profile" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("PetProfileInput2", "Failed to load data", e);

                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    runOnUiThread(() -> {
                        Toast.makeText(PetProfileInput2.this, "No response from server.", Toast.LENGTH_SHORT).show();
                    });
                    return;
                }

                String responseData = response.body().string();

                if (responseData.trim().isEmpty()) {
                    runOnUiThread(() -> {
                        Toast.makeText(PetProfileInput2.this, "Empty response from server.", Toast.LENGTH_SHORT).show();
                    });
                    return;
                }

                try {
                    JSONObject jsonObject = new JSONObject(responseData);

                    // Populate data in EditTexts
                    runOnUiThread(() -> populatePetData(jsonObject));
                } catch (JSONException e) {
                    Log.e("PetProfileInput2", "JSON Parsing error: " + responseData, e);
                    runOnUiThread(() -> Toast.makeText(PetProfileInput2.this, "Error parsing server response.", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    private void populatePetData(JSONObject jsonObject) {
        try {
            input_pet_Name.setText(jsonObject.getString("pet_name"));
            setGenderSpinnerSelection(jsonObject.getString("sex"));
            input_pet_weight.setText(jsonObject.getString("weight"));
            setSpeciesSpinnerSelection(jsonObject.getString("species"));
            input_pet_Breed.setText(jsonObject.getString("breed"));

            // Convert birthday to "MMM. dd, yyyy" format
            String birthday = jsonObject.getString("birthday");
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM. dd, yyyy", Locale.getDefault());
            Date birthDate = inputFormat.parse(birthday);
            String formattedBirthday = outputFormat.format(birthDate);

            // Set formatted birthday in the EditText
            input_pet_Birthday.setText(formattedBirthday);

            calculateAndSetAge(jsonObject.getString("birthday"));


            input_pet_Allergies.setText(jsonObject.getString("allergies"));
            input_pet_PreExistConditions.setText(jsonObject.getString("pre_existing_condition"));
            input_pet_VeterinaryClinic.setText(jsonObject.getString("veterinary_clinic"));
            input_pet_VeterinaryDoctor.setText(jsonObject.getString("veterinary_doctor"));


            // Set vaccine checkboxes
            input_pet_RabbiesVaccine.setChecked(jsonObject.getInt("rabbies_vaccine") == 1);
            input_pet_FiveinOneVaccine.setChecked(jsonObject.getInt("five_on_one_vaccine") == 1);

            String otherVaccine = jsonObject.optString("other_vaccine", "");
            if (!otherVaccine.isEmpty()) {
                input_pet_OtherVaccine1.setChecked(true);
                input_pet_OtherVaccine2.setText(otherVaccine);
                input_pet_OtherVaccine2.setEnabled(true);
            } else {
                input_pet_OtherVaccine1.setChecked(false);
                input_pet_OtherVaccine2.setText("");
                input_pet_OtherVaccine2.setEnabled(false);
            }

        } catch (JSONException | ParseException e) {
            Log.e("PetProfileInput2", "Error populating pet data", e);
            Toast.makeText(this, "Error populating pet data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setGenderSpinnerSelection(String gender) {
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) input_pet_Gender.getAdapter();
        int genderPosition = adapter.getPosition(gender);
        if (genderPosition >= 0) {
            input_pet_Gender.setSelection(genderPosition);
        } else {
            Log.e("PetProfileInput2", "Gender not found in spinner: " + gender);
        }
    }

    private void setSpeciesSpinnerSelection(String species) {
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) input_pet_Species.getAdapter();
        int speciesPosition = adapter.getPosition(species);
        if (speciesPosition >= 0) {
            input_pet_Species.setSelection(speciesPosition);
        } else {
            Log.e("PetProfileInput2", "Species not found in spinner: " + species);
        }
    }

    private void calculateAndSetAge(String birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date birthDate = sdf.parse(birthday);
            Calendar birthCal = Calendar.getInstance();
            if (birthDate != null) {
                birthCal.setTime(birthDate);
                int age = Calendar.getInstance().get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
                if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
                    age--;
                }
                input_pet_Age.setText(String.valueOf(age));
            }
        } catch (ParseException e) {
            Log.e("PetProfileInput2", "Date parsing error", e);
        }
    }

//    private int getSpinnerIndex(Spinner spinner, String myString) {
//        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spinner.getAdapter();
//        return adapter.getPosition(myString);
//    }

    private void savePetDataToDatabase() {
        String petName = input_pet_Name.getText().toString().trim();
        String petGender = input_pet_Gender.getSelectedItem().toString();
        String weight = input_pet_weight.getText().toString().trim();
        String petSpecies = input_pet_Species.getSelectedItem().toString();
        String breed = input_pet_Breed.getText().toString().trim();
        String birthday = input_pet_Birthday.getText().toString().trim();
        String age = input_pet_Age.getText().toString().trim().trim();
        String allergies = input_pet_Allergies.getText().toString().trim();
        String preExistingConditions = input_pet_PreExistConditions.getText().toString().trim();
        String veterinaryClinic = input_pet_VeterinaryClinic.getText().toString().trim();
        String veterinaryDoctor = input_pet_VeterinaryDoctor.getText().toString().trim();
        boolean rabbiesVaccine = input_pet_RabbiesVaccine.isChecked();
        boolean fiveInOneVaccine = input_pet_FiveinOneVaccine.isChecked();

        String otherVaccine = input_pet_OtherVaccine1.isChecked() ? input_pet_OtherVaccine2.getText().toString().trim() : "";

        if (petName.isEmpty() || petGender.isEmpty() || weight.isEmpty() || petSpecies.isEmpty() || breed.isEmpty() || birthday.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert the birthday to "yyyy-MM-dd" format if not already in that format
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM. dd, yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date date = inputFormat.parse(birthday);  // Parse the user-entered birthday
            birthday = outputFormat.format(date);  // Format the date for database
        } catch (ParseException e) {
            Log.e("SavePetData", "Error formatting birthday", e);
            Toast.makeText(this, "Error with the birthday format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create JSON object with updated data
        JSONObject json = new JSONObject();
        try {
            json.put("user_id", userId);
            json.put("pet_id", petId); // Send 0 for new profiles

            json.put("pet_name", petName);
            json.put("sex", petGender);
            json.put("species", petSpecies);
            json.put("breed", breed);
            json.put("birthday", birthday);
            json.put("pet_age", age);
            json.put("weight", weight);
            json.put("allergies", allergies);
            json.put("pre_existing_condition", preExistingConditions);
            json.put("veterinary_clinic", veterinaryClinic);
            json.put("veterinary_doctor", veterinaryDoctor);
            json.put("rabbies_vaccine", rabbiesVaccine ? "1" : "0");
            json.put("five_on_one_vaccine", fiveInOneVaccine ? "1" : "0");
            json.put("other_vaccine", otherVaccine);



            // For images:
//            if (petImageUri != null) {
//                File petImageFile = new File(getPathFromUri(petImageUri));
//                builder.addFormDataPart("pet_image", petImageFile.getName(),
//                        RequestBody.create(petImageFile, MediaType.parse("image/*")));
//            }
//            if (vaccineCardImageUri != null) {
//                File vaccineCardFile = new File(getPathFromUri(vaccineCardImageUri));
//                builder.addFormDataPart("vaccine_card_image", vaccineCardFile.getName(),
//                        RequestBody.create(vaccineCardFile, MediaType.parse("image/*")));
//            }

        } catch (JSONException e) {
            Log.e("UpdateProfile", "Error creating JSON: ", e);
            Toast.makeText(this, "Error preparing data.", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("UpdateProfile", "Sending JSON: " + json.toString());

        // Send data to server to update user profile
        String url = "https://hamugaway.scarlet2.io/update_pet_profile.php?user_id=" + userId + "&pet_id=" + petId;

        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("UpdateProfile", "Failed to save data", e);
                runOnUiThread(() -> {
                    Toast.makeText(PetProfileInput2.this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String responseData = response.body().string();
                    Log.d("UpdateProfile", "Server Response: " + responseData);

                    if (response.isSuccessful()) {
                        runOnUiThread(() -> Toast.makeText(PetProfileInput2.this, "Data updated successfully", Toast.LENGTH_SHORT).show());
                        // changed from petprofiledisplay.class to petprofile.class
                        Intent intent = new Intent(PetProfileInput2.this, PetProfile.class);
                        startActivity(intent);
                    } else {
                        runOnUiThread(() -> Toast.makeText(PetProfileInput2.this, "Failed to update data: " + responseData, Toast.LENGTH_SHORT).show());
                    }
                } finally {
                    response.close();
                }
            }
        });


//        // Add pet image
//        if (petImageUri != null) {
//            File petImageFile = getFileFromUri(petImageUri);
//            if (petImageFile != null) {
//                builder.addFormDataPart("pet_image", petImageFile.getName(),
//                        RequestBody.create(MediaType.parse("image/*"), petImageFile));
//            }
//        }
//
//        // Add vaccine card image
//        if (vaccineCardImageUri != null) {
//            File vaccineCardFile = getFileFromUri(vaccineCardImageUri);
//            if (vaccineCardFile != null) {
//                builder.addFormDataPart("vaccine_card_image", vaccineCardFile.getName(),
//                        RequestBody.create(MediaType.parse("image/*"), vaccineCardFile));
//            }
//        }
//
//        return builder.build();
    }

//    private File getFileFromUri(Uri uri) {
//        // Convert URI to File
//        String[] projection = {MediaStore.Images.Media.DATA};
//        try (Cursor cursor = getContentResolver().query(uri, projection, null, null, null)) {
//            if (cursor != null && cursor.moveToFirst()) {
//                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                String filePath = cursor.getString(columnIndex);
//                return new File(filePath);
//            }
//        } catch (Exception e) {
//            Log.e("FileError", "Error getting file from URI: " + e.getMessage());
//        }
//        return null;
//    }

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

                    // Format the date as "YYYY-MM-DD"
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM. dd, yyyy", Locale.getDefault());
                    editText.setText(dateFormat.format(selectedCalendar.getTime()));

                    // Format "yyyy-MM-dd" for age calculation
                    SimpleDateFormat internalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    calculateAndSetAge(internalFormat.format(selectedCalendar.getTime()));
                },
                year, month, day);
        datePickerDialog.show();
    }
}
