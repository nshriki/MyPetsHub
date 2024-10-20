package com.example.mypetshub;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PetProfileInput extends AppCompatActivity {

    private EditText petName_editText, petBirthday_EditText, petWeight_EditText;
    private RadioGroup radioPetSex_radioGroup, radioPetSpecies_radioGroup;
    ImageView imageView;
    ImageButton putImageBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        queue.add(stringRequest);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet_profile_input);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        petName_editText = findViewById(R.id.petName_editText);
        petBirthday_EditText = findViewById(R.id.petBirthday_EditText);
        petWeight_EditText = findViewById(R.id.petWeight_EditText);
        radioPetSex_radioGroup = findViewById(R.id.radioPetSex_radioGroup);
        radioPetSpecies_radioGroup = findViewById(R.id.radioPetSpecies_radioGroup);
        Button btnPetProfileInput = findViewById(R.id.btnNext);

        // Date picker for birthday
        petBirthday_EditText.setOnClickListener(v -> showDatePickerDialog(petBirthday_EditText));

        btnPetProfileInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPetSexId = radioPetSex_radioGroup.getCheckedRadioButtonId();
                int selectedPetSpeciesId = radioPetSpecies_radioGroup.getCheckedRadioButtonId();
                String petSex = "";
                String petSpecies = "";

                if(selectedPetSexId != -1) {
                    RadioButton petSex_radioButton = findViewById(selectedPetSexId);
                    petSex = petSex_radioButton.getText().toString();
                }

                if(selectedPetSpeciesId != -1) {
                    RadioButton petSpecies_radioButton = findViewById(selectedPetSpeciesId);
                    petSpecies = petSpecies_radioButton.getText().toString();
                }


                RadioButton petSpecies_radioButton = findViewById(selectedPetSpeciesId);

                // Create an Intent to start the SignUpActivity
                Intent nextIntent = new Intent(PetProfileInput.this, PetProfileInput2.class);
                nextIntent.putExtra("pet_name", petName_editText.getText().toString());
                nextIntent.putExtra("pet_sex", petSex);
                nextIntent.putExtra("pet_species", petSpecies);
                nextIntent.putExtra("pet_birthday", petBirthday_EditText.getText().toString());
                nextIntent.putExtra("pet_weight", petWeight_EditText.getText().toString());
                startActivity(nextIntent);
            }
        });

//        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor(R.color.defaultColor)));
        imageView = findViewById(R.id.imageViewPPI1);
        putImageBtn1 = findViewById(R.id.putImageBtnPPI1);

        putImageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(PetProfileInput.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        imageView.setImageURI(uri);
    }

    private void showDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay; // Reformat date to YYYY-MM-DD
                    editText.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}