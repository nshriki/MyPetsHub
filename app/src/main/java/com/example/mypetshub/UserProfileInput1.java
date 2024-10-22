//package com.example.mypetshub;
//
//import android.app.DatePickerDialog;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.Spinner;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.github.dhaval2404.imagepicker.ImagePicker;
//
//import java.util.Calendar;
//
//public class UserProfileInput1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//
//    ImageView imageView; // For profile picture
//    ImageButton putImageBtn1; // Button to select profile picture
//    Uri profileImageUri; // To store the selected profile image URI
//
//    ImageView userValidGovIDUPI1; // For valid government ID image
//    ImageButton putIDImgBtnUPI1; // Button to select government ID image
//
//    private EditText userFNameUPI1;
//    private EditText userLNameUPI1;
//    private EditText userBirthdayUPI1;
//
//    Spinner userGenderUPI1;
//
//    private static final int PICK_PROFILE_IMAGE = 1; // Request code for profile image
//    private static final int PICK_ID_IMAGE = 2;      // Request code for government ID image
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_user_profile_input1);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        userFNameUPI1 = findViewById(R.id.userFNameUPI1);
//        userLNameUPI1 = findViewById(R.id.userLNameUPI1);
//
//        loadUserInfo();
//
//        imageView = findViewById(R.id.uploadImageUPI1);
//        putImageBtn1 = findViewById(R.id.putImageBtnUPI1);
//
//        userValidGovIDUPI1 = findViewById(R.id.userValidGovIDUPI1);
//        putIDImgBtnUPI1 = findViewById(R.id.putIDImgBtnUPI1);
//
//        // Profile image button listener
//        putImageBtn1.setOnClickListener(view -> ImagePicker.with(UserProfileInput1.this)
//                .crop()
//                .compress(1024)
//                .maxResultSize(1080, 1080)
//                .start(PICK_PROFILE_IMAGE));
//
//        // ID image button listener
//        putIDImgBtnUPI1.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(intent, PICK_ID_IMAGE);
//        });
//
//        ImageView backBtnUPI1 = findViewById(R.id.backBtnUPI1);
//        backBtnUPI1.setOnClickListener(v -> {
//            Intent intent = new Intent(UserProfileInput1.this, UserProfileDisplay1.class);
//            startActivity(intent);
//        });
//
//        Button saveBtnUPI1 = findViewById(R.id.saveBtnUPI1);
//        saveBtnUPI1.setOnClickListener(v -> {
//
//        });
//
//        userBirthdayUPI1 = findViewById(R.id.userBirthdayUPI1);
//        userBirthdayUPI1.setOnClickListener(v -> showDatePickerDialog(userBirthdayUPI1));
//
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        userValidGovIDUPI1.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//        userGenderUPI1 = findViewById(R.id.userGenderUPI1);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_gender, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        userGenderUPI1.setAdapter(adapter);
//        userGenderUPI1.setOnItemSelectedListener(this);
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri uri = data.getData();
//
//            if (requestCode == PICK_PROFILE_IMAGE) {
//                profileImageUri = uri; // Store the selected profile image URI
//                imageView.setImageURI(uri);
//            } else if (requestCode == PICK_ID_IMAGE) {
//                userValidGovIDUPI1.setImageURI(uri);
//            }
//        }
//    }
//
//    private void loadUserInfo() {
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        String firstName = sharedPreferences.getString("usersName", "User");
//        String lastName = sharedPreferences.getString("last_name", "LastName");
//
//        userFNameUPI1.setText(firstName);
//        userLNameUPI1.setText(lastName);
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
//                    String selectedDate = (selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear;
//                    editText.setText(selectedDate);
//                },
//                year, month, day
//        );
//        datePickerDialog.show();
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        String selectedGender = adapterView.getItemAtPosition(i).toString();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
//}




//package com.example.mypetshub;
//
//import android.app.DatePickerDialog;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.github.dhaval2404.imagepicker.ImagePicker;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class UserProfileInput1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//
//    // UI Elements
//    private EditText userFNameUPI1, userLNameUPI1, userNicknameUPI1, userNationalityUPI1,
//            userBirthdayUPI1, userAgeUPI1, userAddressUPI1, userEmailUPI1, userMobileNumUPI1;
//    private Spinner userGenderUPI1;
//    private ImageView uploadImageUPI1, userValidGovIDUPI1;
//    private Button saveButton;
//    private int userId; // Store the user ID as an integer
//    private Uri profileImageUri, govIDImageUri; // For storing the image URIs
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_profile_input1);
//
//        // Retrieve userId from shared preferences after login
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        userId = sharedPreferences.getInt("user_id", -1); // Retrieve user_id as an integer
//
//        // Initialize UI elements
//        initializeUI();
//        loadUserInfo(); // Load existing user info if applicable
//        setupDatePicker(); // Set up the Date Picker for birthday
//    }
//
//    private void initializeUI() {
//        userFNameUPI1 = findViewById(R.id.userFNameUPI1);
//        userLNameUPI1 = findViewById(R.id.userLNameUPI1);
//        userNicknameUPI1 = findViewById(R.id.userNicknameUPI1);
//        userNationalityUPI1 = findViewById(R.id.userNationalityUPI1);
//        userBirthdayUPI1 = findViewById(R.id.userBirthdayUPI1);
//        userAgeUPI1 = findViewById(R.id.userAgeUPI1);
//        userAddressUPI1 = findViewById(R.id.userAddressUPI1);
//        userEmailUPI1 = findViewById(R.id.userEmailUPI1);
//        userMobileNumUPI1 = findViewById(R.id.userMobileNumUPI1);
//        userGenderUPI1 = findViewById(R.id.userGenderUPI1);
//        uploadImageUPI1 = findViewById(R.id.uploadImageUPI1);
//        userValidGovIDUPI1 = findViewById(R.id.userValidGovIDUPI1);
//        saveButton = findViewById(R.id.saveButton);
//
//        saveButton.setOnClickListener(v -> saveUserInfo());
//
//        // Image upload listeners (if needed)
//        uploadImageUPI1.setOnClickListener(v -> selectProfileImage());
//        userValidGovIDUPI1.setOnClickListener(v -> selectGovIDImage());
//    }
//
//    private void setupDatePicker() {
//        userBirthdayUPI1.setOnClickListener(v -> showDatePickerDialog(userBirthdayUPI1));
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
//                    String selectedDate = (selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear;
//                    editText.setText(selectedDate);
//                },
//                year, month, day
//        );
//        datePickerDialog.show();
//    }
//
//    private void saveUserInfo() {
//        String firstName = userFNameUPI1.getText().toString().trim();
//        String lastName = userLNameUPI1.getText().toString().trim();
//        String nickname = userNicknameUPI1.getText().toString().trim();
//        String nationality = userNationalityUPI1.getText().toString().trim();
//        String gender = userGenderUPI1.getSelectedItem().toString();
//        String birthday = userBirthdayUPI1.getText().toString().trim();
//        String ageString = userAgeUPI1.getText().toString().trim();
//        String address = userAddressUPI1.getText().toString().trim();
//        String email = userEmailUPI1.getText().toString().trim();
//        String mobileNum = userMobileNumUPI1.getText().toString().trim();
//
//        // Ensure all fields are filled
//        if (firstName.isEmpty() || lastName.isEmpty() || nickname.isEmpty() || nationality.isEmpty() ||
//                gender.isEmpty() || birthday.isEmpty() || ageString.isEmpty() || address.isEmpty() ||
//                email.isEmpty() || mobileNum.isEmpty() || profileImageUri == null || govIDImageUri == null) {
//            Toast.makeText(this, "Please fill all fields and upload images", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Convert age to integer
//        int age = Integer.parseInt(ageString);
//
//        // Convert images to base64 strings or handle upload to server as needed
//        String profileImageBase64 = convertImageToBase64(profileImageUri);
//        String govIDImageBase64 = convertImageToBase64(govIDImageUri);
//
//        // URL of your PHP script
//        String url = "http://your-server-url.com/save_user_info.php";
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                response -> {
//                    // Handle response
//                    Toast.makeText(UserProfileInput1.this, "Profile Saved Successfully!", Toast.LENGTH_SHORT).show();
//                    // Optionally, save user info in SharedPreferences
//                    saveUserToPreferences(firstName, lastName, nickname, nationality, gender, birthday, age, address, email, mobileNum);
//                },
//                error -> {
//                    // Handle error
//                    Toast.makeText(UserProfileInput1.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("user_id", String.valueOf(userId)); // Include user_id as a string
//                params.put("first_name", firstName);
//                params.put("last_name", lastName);
//                params.put("nickname", nickname);
//                params.put("nationality", nationality);
//                params.put("gender", gender);
//                params.put("birthday", birthday);
//                params.put("age", String.valueOf(age)); // Send age as a string
//                params.put("address", address);
//                params.put("email", email);
//                params.put("mobile_num", mobileNum);
//                params.put("profile_image", profileImageBase64);
//                params.put("gov_id_image", govIDImageBase64);
//                return params;
//            }
//        };
//
//        requestQueue.add(stringRequest);
//    }
//
//    // Add methods for image selection and conversion
//    private void selectProfileImage() {
//        // Logic to select profile image from gallery or camera
//    }
//
//    private void selectGovIDImage() {
//        // Logic to select government ID image from gallery or camera
//    }
//
//    private String convertImageToBase64(Uri imageUri) {
//        // Logic to convert image URI to Base64 string
//        return ""; // Placeholder, implement the actual conversion
//    }
//
//    private void loadUserInfo() {
//        // Logic to load existing user info if applicable
//    }
//
//    private void saveUserToPreferences(String firstName, String lastName, String nickname, String nationality, String gender, String birthday, int age, String address, String email, String mobileNum) {
//        // Logic to save user info to SharedPreferences if necessary
//    }
//}



//package com.example.mypetshub;
//
//import android.app.DatePickerDialog;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.github.dhaval2404.imagepicker.ImagePicker;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Calendar;
//
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okhttp3.logging.HttpLoggingInterceptor;
//
//public class UserProfileInput1 extends AppCompatActivity {
//
//    ImageView imageView, userValidGovIDUPI1;
//    ImageButton putImageBtn1, putIDImgBtnUPI1;
//    Uri profileImageUri, idImageUri;
//
//    private EditText userFNameUPI1, userLNameUPI1, userNicknameUPI1, userNationalityUPI1,
//            userBirthdayUPI1, userAgeUPI1, userAddressUPI1;
//
//    private Spinner userGenderUPI1;
//    private Button nextBtn;
//
//    private static final int PICK_PROFILE_IMAGE = 1;
//    private static final int PICK_ID_IMAGE = 2;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_profile_input1);
//
//        userFNameUPI1 = findViewById(R.id.userFNameUPI1);
//        userLNameUPI1 = findViewById(R.id.userLNameUPI1);
//        userNicknameUPI1 = findViewById(R.id.userNicknameUPI1);
//        userNationalityUPI1 = findViewById(R.id.userNationalityUPI1);
//        userBirthdayUPI1 = findViewById(R.id.userBirthdayUPI1);
//        userAgeUPI1 = findViewById(R.id.userAgeUPI1);
//        userAddressUPI1 = findViewById(R.id.userAddressUPI1);
//
//        imageView = findViewById(R.id.uploadImageUPI1);
//        putImageBtn1 = findViewById(R.id.putImageBtnUPI1);
//        userValidGovIDUPI1 = findViewById(R.id.userValidGovIDUPI1);
//        putIDImgBtnUPI1 = findViewById(R.id.putIDImgBtnUPI1);
//
//        userGenderUPI1 = findViewById(R.id.userGenderUPI1);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_gender, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        userGenderUPI1.setAdapter(adapter);
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            userFNameUPI1.setText(intent.getStringExtra("usersName"));
//            userLNameUPI1.setText(intent.getStringExtra("last_name"));
//            userNicknameUPI1.setText(intent.getStringExtra("nickname"));
//            userNationalityUPI1.setText(intent.getStringExtra("nationality"));
//            userBirthdayUPI1.setText(intent.getStringExtra("birthday"));
//            userAgeUPI1.setText(intent.getStringExtra("age"));
//            userAddressUPI1.setText(intent.getStringExtra("address"));
//            String gender = intent.getStringExtra("gender");
//            if (gender != null) {
//                int position = adapter.getPosition(gender);
//                if (position >= 0) {
//                    userGenderUPI1.setSelection(position);
//                }
//            }
//        }
//
//        userBirthdayUPI1.setOnClickListener(v -> showDatePickerDialog(userBirthdayUPI1));
//
//        ImageView backBtnUPI1 = findViewById(R.id.backBtnUPI1);
//        backBtnUPI1.setOnClickListener(v -> {
//            Intent backIntent = new Intent(UserProfileInput1.this, UserDashboard.class);
//            backIntent.putExtra("usersName", userFNameUPI1.getText().toString());
//            backIntent.putExtra("last_name", userLNameUPI1.getText().toString());
//            backIntent.putExtra("nickname", userNicknameUPI1.getText().toString());
//            backIntent.putExtra("nationality", userNationalityUPI1.getText().toString());
//            backIntent.putExtra("birthday", userBirthdayUPI1.getText().toString());
//            backIntent.putExtra("age", userAgeUPI1.getText().toString());
//            backIntent.putExtra("address", userAddressUPI1.getText().toString());
//            backIntent.putExtra("gender", userGenderUPI1.getSelectedItem() != null ? userGenderUPI1.getSelectedItem().toString() : "");
//            startActivity(backIntent);
//            finish();
//        });
//
//        nextBtn.setOnClickListener(v -> {
//            Intent intentNext = new Intent(UserProfileInput1.this, UserProfileInput2.class);
//            intentNext.putExtra("usersName", userFNameUPI1.getText().toString());
//            intentNext.putExtra("last_name", userLNameUPI1.getText().toString());
//            intentNext.putExtra("nickname", userNicknameUPI1.getText().toString());
//            intentNext.putExtra("nationality", userNationalityUPI1.getText().toString());
//            intentNext.putExtra("birthday", userBirthdayUPI1.getText().toString());
//            intentNext.putExtra("age", userAgeUPI1.getText().toString());
//            intentNext.putExtra("address", userAddressUPI1.getText().toString());
//            intentNext.putExtra("gender", userGenderUPI1.getSelectedItem() != null ? userGenderUPI1.getSelectedItem().toString() : "");
//            startActivity(intentNext);
//        });
//
//        // Load user data
//        loadUserData();
//
//        putImageBtn1.setOnClickListener(view -> ImagePicker.with(UserProfileInput1.this)
//                .crop()
//                .compress(1024)
//                .maxResultSize(1080, 1080)
//                .start(PICK_PROFILE_IMAGE));
//
//        putIDImgBtnUPI1.setOnClickListener(v -> {
//            Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(intent1, PICK_ID_IMAGE);
//        });
//
//
//    }
//
//    private void loadUserData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        String firstName = sharedPreferences.getString("usersName", "");
//        String lastName = sharedPreferences.getString("last_name", "");
//
//        userFNameUPI1.setText(firstName);
//        userLNameUPI1.setText(lastName);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri uri = data.getData();
//            if (requestCode == PICK_PROFILE_IMAGE) {
//                profileImageUri = uri;
//                imageView.setImageURI(uri);
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            } else if (requestCode == PICK_ID_IMAGE) {
//                idImageUri = uri;
//                userValidGovIDUPI1.setImageURI(uri);
//                userValidGovIDUPI1.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            }
//        }
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
//
//
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        int userId = sharedPreferences.getInt("user_id", -1); // Assume user_id is stored in SharedPreferences
//
//        String profileImagePath = profileImageUri != null ? profileImageUri.getPath() : null;
//        String idImagePath = idImageUri != null ? idImageUri.getPath() : null;
//
//}



//package com.example.mypetshub;
//
//import static java.lang.Integer.parseInt;
//
//import android.app.DatePickerDialog;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.github.dhaval2404.imagepicker.ImagePicker;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Locale;
//
//public class UserProfileInput1 extends AppCompatActivity {
//
//    private EditText userFName1, userLName1, userNickname1, userNationality1, userBirthday1, userAge1, userAddress1;
//    private Spinner userGenderSpinner;
//    private ImageButton confirmBtn;
//
//    ImageView uploadImageUPI1;
//    Uri profileImageUri, idImageUri;
//
//    private static final int PICK_PROFILE_IMAGE = 1;
//    private int userId;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_user_profile_input1);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        // Initialize fields
//        userFName1 = findViewById(R.id.userFNameUPI1);
//        userLName1 = findViewById(R.id.userLNameUPI1);
//        userNickname1 = findViewById(R.id.userNicknameUPI1);
//        userNationality1 = findViewById(R.id.userNationalityUPI1);
//        userBirthday1 = findViewById(R.id.userBirthdayUPI1);
//        userAge1 = findViewById(R.id.userAgeUPI1);
//        userAddress1 = findViewById(R.id.userAddressUPI1);
//        userGenderSpinner = findViewById(R.id.userGenderUPI1);
//        confirmBtn = findViewById(R.id.confirmBtn);
//
//        // Set up gender spinner
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_gender, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        userGenderSpinner.setAdapter(adapter);
//
//        uploadImageUPI1 = findViewById(R.id.uploadImageUPI1);
//
//        // Handle incoming data from previous page (if any)
////        Intent intent = getIntent();
////        if (intent != null) {
////            userFName1.setText(intent.getStringExtra("usersName"));
////            userLName1.setText(intent.getStringExtra("last_name"));
////            userNickname1.setText(intent.getStringExtra("nickname"));
////            userNationality1.setText(intent.getStringExtra("nationality"));
////            userBirthday1.setText(intent.getStringExtra("birthday"));
////            userAge1.setText(intent.getStringExtra("age"));
////            userAddress1.setText(intent.getStringExtra("address"));
////            String gender = intent.getStringExtra("gender");
////            if (gender != null) {
////                int position = adapter.getPosition(gender);
////                if (position >= 0) {
////                    userGenderSpinner.setSelection(position);
////                }
////            }
////        }
//
//        // Date picker for birthday
//        userBirthday1.setOnClickListener(v -> showDatePickerDialog(userBirthday1));
//
//        // Back button
//        ImageView userProfileBackBtn = findViewById(R.id.backBtnUPI1);
//        userProfileBackBtn.setOnClickListener(v -> {
//            Intent backIntent = new Intent(UserProfileInput1.this, UserDashboard.class);
//            startActivity(backIntent);
//            finish();
//        });
//
//        // Next button
//        confirmBtn.setOnClickListener(v -> {
//            Intent intentNext = new Intent(UserProfileInput1.this, UserProfileDisplay1.class);
//            intentNext.putExtra("usersName", userFName1.getText().toString());
//            intentNext.putExtra("last_name", userLName1.getText().toString());
//            intentNext.putExtra("nickname", userNickname1.getText().toString());
//            intentNext.putExtra("nationality", userNationality1.getText().toString());
//            intentNext.putExtra("birthday", userBirthday1.getText().toString());
//            intentNext.putExtra("age", userAge1.getText().toString());
//            intentNext.putExtra("address", userAddress1.getText().toString());
//            intentNext.putExtra("gender", userGenderSpinner.getSelectedItem() != null ? userGenderSpinner.getSelectedItem().toString() : "");
//            intentNext.putExtra("userId", userId);
//
//            if (profileImageUri != null) {
//                intentNext.putExtra("profileImageUri", profileImageUri.toString());
//            }
//            startActivity(intentNext);
//        });
//
//        // Load user data
//        loadUserData();
//
//        uploadImageUPI1.setOnClickListener(view -> ImagePicker.with(UserProfileInput1.this)
//                .crop()
//                .compress(1024)
//                .maxResultSize(1080, 1080)
//                .start(PICK_PROFILE_IMAGE));
//    }
//
//    private void loadUserData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        String firstName = sharedPreferences.getString("usersName", "");
//        int userId = sharedPreferences.getInt("id", -1); // Use null as a default value
//
//        if (userId == -1) {
//            Toast.makeText(this, "User ID not found. Please login again.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        userFName1.setText(firstName);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri uri = data.getData();
//            if (requestCode == PICK_PROFILE_IMAGE) {
//                profileImageUri = uri;
//                uploadImageUPI1.setImageURI(uri);
//                uploadImageUPI1.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            }
//        }
//    }
//
////    private void showDatePickerDialog(EditText editText) {
////        Calendar calendar = Calendar.getInstance();
////        int year = calendar.get(Calendar.YEAR);
////        int month = calendar.get(Calendar.MONTH);
////        int day = calendar.get(Calendar.DAY_OF_MONTH);
////
////        DatePickerDialog datePickerDialog = new DatePickerDialog(
////                this,
////                (view, selectedYear, selectedMonth, selectedDay) -> {
////                    String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay; // Reformat date to YYYY-MM-DD
////                    editText.setText(selectedDate);
////                },
////                year, month, day
////        );
////        datePickerDialog.show();
////    }
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
//                    // Create a calendar instance and set the selected date
//                    Calendar selectedCalendar = Calendar.getInstance();
//                    selectedCalendar.set(selectedYear, selectedMonth, selectedDay);
//
//                    // Format the date as "MMM. dd, yyyy"
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM. dd, yyyy", Locale.getDefault());
//                    String formattedDate = dateFormat.format(selectedCalendar.getTime());
//
//                    // Set the formatted date to the EditText
//                    editText.setText(formattedDate);
//                },
//                year, month, day
//        );
//        datePickerDialog.show();
//    }
//}
//
////        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
////        int userId = sharedPreferences.getInt("user_id", -1); // Assume user_id is stored in SharedPreferences
////
////        String profileImagePath = profileImageUri != null ? profileImageUri.getPath() : null;
////        String idImagePath = idImageUri != null ? idImageUri.getPath() : null;


package com.example.mypetshub;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserProfileInput1 extends AppCompatActivity {

    private EditText input_user_FName, input_user_LName, input_user_Nickname, input_user_Nationality, input_user_Birthday, input_user_Age, input_user_Address, input_user_Email, input_user_PhoneNumber,
            input_user_ecName, input_user_ecRelationship, input_user_ecPhoneNumber;
    private Spinner input_user_Gender;
    private ImageButton confirmBtn, backBtnUPI1;

    private OkHttpClient client;
    private SharedPreferences sharedPreferences;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_input1);

        // Initialize fields
        initializeFields();

        // Initialize back button
        backBtnUPI1 = findViewById(R.id.backBtnUPI1);
        backBtnUPI1.setOnClickListener(v -> finish());

        // Load user ID from SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id", -1);

        // Check if userId is valid
        if (userId == -1) {
            Toast.makeText(this, "User ID not found. Please log in again.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadUserData(userId);

        // Set up date picker for birthday
        input_user_Birthday.setOnClickListener(v -> showDatePickerDialog(input_user_Birthday));

        // Confirm button to save updated data
        confirmBtn.setOnClickListener(v -> saveUserDataToDatabase());
    }

    private void initializeFields() {
        input_user_FName = findViewById(R.id.input_user_FName);
        input_user_LName = findViewById(R.id.input_user_LName);
        input_user_Nickname = findViewById(R.id.input_user_Nickname);
        input_user_Gender = findViewById(R.id.input_user_Gender);
        input_user_Nationality = findViewById(R.id.input_user_Nationality);
        input_user_Birthday = findViewById(R.id.input_user_Birthday);
        input_user_Age = findViewById(R.id.input_user_Age);
        input_user_Address = findViewById(R.id.input_user_Address);
        input_user_Email = findViewById(R.id.input_user_Email);
        input_user_PhoneNumber = findViewById(R.id.input_user_PhoneNumber);
        input_user_ecName = findViewById(R.id.input_user_ecName);
        input_user_ecRelationship = findViewById(R.id.input_user_ecRelationship);
        input_user_ecPhoneNumber = findViewById(R.id.input_user_ecPhoneNumber);
        confirmBtn = findViewById(R.id.confirmBtn);

        // Set input fields for first name, last name, email, and age to non-editable
        setFieldsNonEditable();

        // Initialize OkHttpClient
        client = new OkHttpClient();

        // Set gender options in the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_user_Gender.setAdapter(adapter);
    }

    private void setFieldsNonEditable() {
        input_user_FName.setFocusable(false);
        input_user_LName.setFocusable(false);
        input_user_Email.setFocusable(false);
        input_user_Age.setFocusable(false);
    }

    private void loadUserData(int userId) {
        String url = "https://hamugaway.scarlet2.io/get_user_profile.php?user_id=" + userId;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(UserProfileInput1.this, "Failed to load data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("UserProfileInput1", "Failed to load data", e);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    runOnUiThread(() -> {
                        Toast.makeText(UserProfileInput1.this, "No response from server.", Toast.LENGTH_SHORT).show();
                    });
                    return;
                }

                String responseData = response.body().string();

                if (responseData.trim().isEmpty()) {
                    runOnUiThread(() -> {
                        Toast.makeText(UserProfileInput1.this, "Empty response from server.", Toast.LENGTH_SHORT).show();
                    });
                    return;
                }

                try {
                    JSONObject jsonObject = new JSONObject(responseData);

                    // Populate data in EditTexts
                    runOnUiThread(() -> populateUserData(jsonObject));
                } catch (JSONException e) {
                    Log.e("UserProfileInput1", "JSON Parsing error: " + responseData, e);
                    runOnUiThread(() -> Toast.makeText(UserProfileInput1.this, "Error parsing server response.", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    private void populateUserData(JSONObject jsonObject) {
        try {
            input_user_FName.setText(jsonObject.getString("first_name"));
            input_user_LName.setText(jsonObject.getString("last_name"));
            input_user_Nickname.setText(jsonObject.getString("nickname"));
            input_user_Nationality.setText(jsonObject.getString("nationality"));
            input_user_Birthday.setText(jsonObject.getString("birthday"));
            calculateAndSetAge(jsonObject.getString("birthday")); // Calculate age based on birthday
            input_user_Address.setText(jsonObject.getString("address"));
            input_user_Email.setText(jsonObject.getString("email"));
            input_user_PhoneNumber.setText(jsonObject.getString("phone_number"));
            input_user_ecName.setText(jsonObject.getString("ec_name"));
            input_user_ecRelationship.setText(jsonObject.getString("ec_relationship"));
            input_user_ecPhoneNumber.setText(jsonObject.getString("ec_mobilenum"));

            // Set the gender in the spinner
            setSpinnerSelection(jsonObject.getString("gender"));
        } catch (JSONException e) {
            Log.e("UserProfileInput1", "Error populating user data", e);
            Toast.makeText(this, "Error populating user data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSpinnerSelection(String gender) {
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) input_user_Gender.getAdapter();
        int genderPosition = adapter.getPosition(gender);
        if (genderPosition >= 0) {
            input_user_Gender.setSelection(genderPosition);
        } else {
            Log.e("UserProfileInput1", "Gender not found in spinner: " + gender);
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
                input_user_Age.setText(String.valueOf(age));
            }
        } catch (ParseException e) {
            Log.e("UserProfileInput1", "Date parsing error", e);
        }
    }

    private void saveUserDataToDatabase() {
        String firstName = input_user_FName.getText().toString().trim();
        String lastName = input_user_LName.getText().toString().trim();
        String nickname = input_user_Nickname.getText().toString().trim();
        String age = input_user_Age.getText().toString().trim();
        String email = input_user_Email.getText().toString().trim();
        String gender = input_user_Gender.getSelectedItem().toString().trim();
        String birthday = input_user_Birthday.getText().toString().trim();
        String nationality = input_user_Nationality.getText().toString().trim();
        String address = input_user_Address.getText().toString().trim();
        String phoneNumber = input_user_PhoneNumber.getText().toString().trim();
        String ecName = input_user_ecName.getText().toString().trim();
        String ecRelationship = input_user_ecRelationship.getText().toString().trim();
        String ecPhoneNumber = input_user_ecPhoneNumber.getText().toString().trim();
        String profileImage = "";

        // Create JSON object with updated data
        JSONObject json = new JSONObject();
        try {
            json.put("user_id", userId);
            json.put("first_name", firstName);
            json.put("last_name", lastName);
            json.put("nickname", nickname);
            json.put("age", age);
            json.put("email", email);
            json.put("gender", gender);
            json.put("birthday", birthday);
            json.put("nationality", nationality);
            json.put("address", address);
            json.put("phone_number", phoneNumber);
            json.put("ec_name", ecName);
            json.put("ec_relationship", ecRelationship);
            json.put("ec_mobilenum", ecPhoneNumber);
            json.put("profile_image", profileImage);

        } catch (JSONException e) {
            Log.e("UpdateProfile", "Error creating JSON: ", e);
            Toast.makeText(this, "Error creating data for update.", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("UpdateProfile", "Sending JSON: " + json.toString());

        // Send data to server to update user profile
        String url = "https://hamugaway.scarlet2.io/save_user_profile.php";
        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("UpdateProfile", "Failed to save data", e);
                runOnUiThread(() -> Toast.makeText(UserProfileInput1.this, "Failed to save data", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String responseData = response.body().string();
                    Log.d("UpdateProfile", "Server Response: " + responseData);

                    if (response.isSuccessful()) {
                        runOnUiThread(() -> Toast.makeText(UserProfileInput1.this, "Data updated successfully", Toast.LENGTH_SHORT).show());
                    } else {
                        runOnUiThread(() -> Toast.makeText(UserProfileInput1.this, "Failed to update data: " + responseData, Toast.LENGTH_SHORT).show());
                    }
                } finally {
                    response.close();
                }
            }
        });
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

                    // Format the date as "YYYY-MM-DD"
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    editText.setText(dateFormat.format(selectedCalendar.getTime()));
                    calculateAndSetAge(dateFormat.format(selectedCalendar.getTime()));
                },
                year, month, day);
        datePickerDialog.show();
    }
}
