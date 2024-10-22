//package com.example.mypetshub;
//
//import android.app.DatePickerDialog;
//import android.content.Intent;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.github.dhaval2404.imagepicker.ImagePicker;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//public class PetProfileInput extends AppCompatActivity {
//
//    private EditText petName_editText, petBirthday_EditText, petWeight_EditText;
//    private RadioGroup radioPetSex_radioGroup, radioPetSpecies_radioGroup;
//    ImageView imageView;
//    ImageButton putImageBtn1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        queue.add(stringRequest);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_pet_profile_input);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        petName_editText = findViewById(R.id.petName_editText);
//        petBirthday_EditText = findViewById(R.id.petBirthday_EditText);
//        petWeight_EditText = findViewById(R.id.petWeight_EditText);
//        radioPetSex_radioGroup = findViewById(R.id.radioPetSex_radioGroup);
//        radioPetSpecies_radioGroup = findViewById(R.id.radioPetSpecies_radioGroup);
//        Button btnPetProfileInput = findViewById(R.id.btnNext);
//
//        // Date picker for birthday
//        petBirthday_EditText.setOnClickListener(v -> showDatePickerDialog(petBirthday_EditText));
//
//        btnPetProfileInput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int selectedPetSexId = radioPetSex_radioGroup.getCheckedRadioButtonId();
//                int selectedPetSpeciesId = radioPetSpecies_radioGroup.getCheckedRadioButtonId();
//                String petSex = "";
//                String petSpecies = "";
//
//                if(selectedPetSexId != -1) {
//                    RadioButton petSex_radioButton = findViewById(selectedPetSexId);
//                    petSex = petSex_radioButton.getText().toString();
//                }
//
//                if(selectedPetSpeciesId != -1) {
//                    RadioButton petSpecies_radioButton = findViewById(selectedPetSpeciesId);
//                    petSpecies = petSpecies_radioButton.getText().toString();
//                }
//
//
//                RadioButton petSpecies_radioButton = findViewById(selectedPetSpeciesId);
//
//                // Create an Intent to start the SignUpActivity
//                Intent nextIntent = new Intent(PetProfileInput.this, PetProfileInput2.class);
//                nextIntent.putExtra("pet_name", petName_editText.getText().toString());
//                nextIntent.putExtra("pet_sex", petSex);
//                nextIntent.putExtra("pet_species", petSpecies);
//                nextIntent.putExtra("pet_birthday", petBirthday_EditText.getText().toString());
//                nextIntent.putExtra("pet_weight", petWeight_EditText.getText().toString());
//                startActivity(nextIntent);
//            }
//        });
//
////        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor(R.color.defaultColor)));
//        imageView = findViewById(R.id.imageViewPPI1);
//        putImageBtn1 = findViewById(R.id.putImageBtnPPI1);
//
//        putImageBtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ImagePicker.with(PetProfileInput.this)
//                        .crop()                    //Crop image(Optional), Check Customization for more option
//                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
//                        .start();
//            }
//
//        });
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Uri uri = data.getData();
//        imageView.setImageURI(uri);
//    }
//
//    private void showDatePickerDialog(EditText editText) {
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                this,
//                (view, selectedYear, selectedMonth, selectedDay) -> {
//                    String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay; // Reformat date to YYYY-MM-DD
//                    editText.setText(selectedDate);
//                },
//                year, month, day
//        );
//        datePickerDialog.show();
//    }
//}



package com.example.mypetshub;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.File;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PetProfileInput extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;

    private EditText input_pet_Name, input_pet_weight, input_pet_Breed, input_pet_Birthday,
            input_pet_Allergies, input_pet_PreExistConditions, input_pet_VeterinaryClinic,
            input_pet_VeterinaryDoctor, input_pet_OtherVaccine2;
    private CheckBox input_pet_RabbiesVaccine, input_pet_FiveinOneVaccine;
    private ImageButton confirmBtn, backBtnUPI1;
    private Spinner input_pet_Gender, input_pet_Species;
    private int userId;
    private OkHttpClient client;
    private ImageView input_pet_ProfileView;
    private ImageView vaccineCardImageView;
    private Uri petImageUri;
    private Uri vaccineCardImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile_input);

        // Initialize views
        initializeFields();

        // Load user ID from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id", -1);

        // Check if userId is valid
        if (userId == -1) {
            Toast.makeText(this, "User ID not found. Please log in again.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        backBtnUPI1 = findViewById(R.id.backBtnUPI1);
        backBtnUPI1.setOnClickListener(view -> {
            Intent backBtnPPI_Intent = new Intent(PetProfileInput.this, UserDashboard.class);
            startActivity(backBtnPPI_Intent);
        });

        // Date picker for birthday
        input_pet_Birthday.setOnClickListener(v -> showDatePickerDialog(input_pet_Birthday));

        // Set click listener for confirm button
        confirmBtn.setOnClickListener(v -> addNewPetProfile());

        // Set click listener for pet image
        input_pet_ProfileView.setOnClickListener(v -> pickImage(PICK_IMAGE));

        // Set click listener for vaccine card image
        vaccineCardImageView.setOnClickListener(v -> pickImage(PICK_IMAGE + 1));
    }

    private void initializeFields() {
        input_pet_Name = findViewById(R.id.input_pet_Name);
        input_pet_Gender = findViewById(R.id.input_pet_Gender);
        input_pet_weight = findViewById(R.id.input_pet_weight);
        input_pet_Species = findViewById(R.id.input_pet_Species);
        input_pet_Breed = findViewById(R.id.input_pet_Breed);
        input_pet_Birthday = findViewById(R.id.input_pet_Birthday);
        input_pet_Allergies = findViewById(R.id.input_pet_Allergies);
        input_pet_PreExistConditions = findViewById(R.id.input_pet_PreExistConditions);
        input_pet_VeterinaryClinic = findViewById(R.id.input_pet_VeterinaryClinic);
        input_pet_VeterinaryDoctor = findViewById(R.id.input_pet_VeterinaryDoctor);
        input_pet_RabbiesVaccine = findViewById(R.id.input_pet_RabbiesVaccine);
        input_pet_FiveinOneVaccine = findViewById(R.id.input_pet_FiveinOneVaccine);
        input_pet_OtherVaccine2 = findViewById(R.id.input_pet_OtherVaccine2);
        confirmBtn = findViewById(R.id.confirmBtn);
        input_pet_ProfileView = findViewById(R.id.input_pet_ProfileView);
        vaccineCardImageView = findViewById(R.id.vaccineCardImageView);

        input_pet_Gender = findViewById(R.id.input_pet_Gender);
        input_pet_Species = findViewById(R.id.input_pet_Species);

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

    private void addNewPetProfile() {
        // Ensure all fields are filled out
        if (input_pet_Name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter pet name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (input_pet_weight.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter pet weight", Toast.LENGTH_SHORT).show();
            return;
        }

        // Collect all input data
        String petName = input_pet_Name.getText().toString();
        String petGender = (input_pet_Gender.getSelectedItem() != null)
                ? input_pet_Gender.getSelectedItem().toString()
                : "Not specified"; // Handle null case
        String petWeight = input_pet_weight.getText().toString();
        String petSpecies = (input_pet_Species.getSelectedItem() != null)
                ? input_pet_Species.getSelectedItem().toString()
                : "Not specified"; // Handle null case
        String petBreed = input_pet_Breed.getText().toString();
        String petBirthday = input_pet_Birthday.getText().toString();
        String petAllergies = input_pet_Allergies.getText().toString();
        String petPreExistConditions = input_pet_PreExistConditions.getText().toString();
        String petVetClinic = input_pet_VeterinaryClinic.getText().toString();
        String petVetDoctor = input_pet_VeterinaryDoctor.getText().toString();
        String otherVaccines = input_pet_OtherVaccine2.getText().toString();
        boolean rabbiesVaccine = input_pet_RabbiesVaccine.isChecked();
        boolean fiveInOneVaccine = input_pet_FiveinOneVaccine.isChecked();

        // Create multipart body for uploading image and data
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("user_id", String.valueOf(userId));
        builder.addFormDataPart("pet_name", petName);
        builder.addFormDataPart("sex", petGender);
        builder.addFormDataPart("species", petSpecies);
        builder.addFormDataPart("breed", petBreed);
        builder.addFormDataPart("birthday", petBirthday);
        builder.addFormDataPart("weight", petWeight);
        builder.addFormDataPart("allergies", petAllergies);
        builder.addFormDataPart("pre_existing_condition", petPreExistConditions);
        builder.addFormDataPart("veterinary_clinic", petVetClinic);
        builder.addFormDataPart("veterinary_doctor", petVetDoctor);
        builder.addFormDataPart("rabbies_vaccine", rabbiesVaccine ? "1" : "0");
        builder.addFormDataPart("five_on_one_vaccine", fiveInOneVaccine ? "1" : "0");
        builder.addFormDataPart("other_vaccine", otherVaccines);

        // For images:
        if (petImageUri != null) {
            File petImageFile = new File(getPathFromUri(petImageUri));
            builder.addFormDataPart("pet_image", petImageFile.getName(),
                    RequestBody.create(petImageFile, MediaType.parse("image/*")));
        }
        if (vaccineCardImageUri != null) {
            File vaccineCardFile = new File(getPathFromUri(vaccineCardImageUri));
            builder.addFormDataPart("vaccine_card_image", vaccineCardFile.getName(),
                    RequestBody.create(vaccineCardFile, MediaType.parse("image/*")));
        }

        // Build request body
        RequestBody requestBody = builder.build();

        // Send pet profile data to server
        String url = "https://hamugaway.scarlet2.io/save_pet_profile.php";
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("PetProfileInput", "Failed to insert pet profile", e);
                runOnUiThread(() -> Toast.makeText(PetProfileInput.this, "Failed to add pet profile. Please try again.", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.d("PetProfileInput", "Server response: " + responseBody);

                if (response.isSuccessful()) {
                    runOnUiThread(() -> Toast.makeText(PetProfileInput.this, "Pet profile added successfully!", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> Toast.makeText(PetProfileInput.this, "Failed to add pet profile. Please try again.", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    // Helper method to get file path from URI
    private String getPathFromUri(Uri uri) {
        // Your implementation to retrieve the real file path from the URI
        // This might involve using a Cursor to query the content provider
        return "";
    }
}