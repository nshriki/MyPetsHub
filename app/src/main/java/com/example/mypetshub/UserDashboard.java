//package com.example.mypetshub;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class UserDashboard extends AppCompatActivity {
//
//    ImageButton imgBtnPetSitting;
//    ImageButton imgBtnDaycare;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_user_dashboard);
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        imgBtnPetSitting = findViewById(R.id.imgBtnPetSitting);
//        imgBtnDaycare = findViewById(R.id.imgBtnDaycare);
//
//        // services
//        // 1. appointments btn
////        ImageButton imgBtnAppointments = findViewById(R.id.imgBtnAppointments);
////        imgBtnAppointments.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(UserDashboard.this, PetAppointments.class);
////                startActivity(intent);
////            }
////        });
//
//        // 2. boarding btn
//        ImageButton imgBtnBoarding = findViewById(R.id.imgBtnBoarding);
//        imgBtnBoarding.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(UserDashboard.this, PetBoarding.class);
//                startActivity(intent);
//            }
//        });
//
//        // these buttons are disabled lng sa for now
//        // 3. pet sitting btn
//        imgBtnPetSitting.setEnabled(false);
//
//        // 4. daycare btn
//        imgBtnDaycare.setEnabled(false);
//
//
//
//        // add pet btn
//        Button btnAddPetDashboard = findViewById(R.id.btnAddPetDashboard);
//        btnAddPetDashboard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create an Intent to start the SignUpActivity
//                Intent intent = new Intent(UserDashboard.this, PetProfile.class);
//                startActivity(intent);
//            }
//        });
//    }
//}




//package com.example.mypetshub;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class UserDashboard extends AppCompatActivity {
//
//    ImageButton imgBtnPetSitting;
//    ImageButton imgBtnDaycare;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_user_dashboard);
//
//        // Check if the user is logged in
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
//
//        if (!isLoggedIn) {
//            // Redirect to SignIn Activity
//            Intent intent = new Intent(UserDashboard.this, SignIn.class);
//            startActivity(intent);
//            finish(); // Close the current activity
//            return; // Exit onCreate
//        }
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        imgBtnPetSitting = findViewById(R.id.imgBtnPetSitting);
//        imgBtnDaycare = findViewById(R.id.imgBtnDaycare);
//        // services
//        // 1. appointments btn
////        ImageButton imgBtnAppointments = findViewById(R.id.imgBtnAppointments);
////        imgBtnAppointments.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(UserDashboard.this, PetAppointments.class);
////                startActivity(intent);
////            }
////        });
//
//        // 2. boarding btn
//        ImageButton imgBtnBoarding = findViewById(R.id.imgBtnBoarding);
//        imgBtnBoarding.setOnClickListener(v -> {
//            Intent intent = new Intent(UserDashboard.this, PetBoarding.class);
//            startActivity(intent);
//        });
//
//        // These buttons are disabled for now
//        imgBtnPetSitting.setEnabled(false);
//        imgBtnDaycare.setEnabled(false);
//
//        // Add pet btn
//        Button btnAddPetDashboard = findViewById(R.id.btnAddPetDashboard);
//        btnAddPetDashboard.setOnClickListener(v -> {
//            Intent intent = new Intent(UserDashboard.this, PetProfile.class);
//            startActivity(intent);
//        });
//    }
//
//    // Example logout method
////    private void logout() {
////        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
////        SharedPreferences.Editor editor = sharedPreferences.edit();
////        editor.clear(); // Clear all preferences
////        editor.apply();
////
////        // Redirect to SignIn Activity
////        Intent intent = new Intent(UserDashboard.this, SignIn.class);
////        startActivity(intent);
////        finish();
////    }
//}



//package com.example.mypetshub;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.os.Handler;
//import android.view.MenuItem;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.view.GravityCompat;
//
//import com.google.android.material.navigation.NavigationView;
//
//public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
//
//    ImageButton imgBtnPetSitting;
//    ImageButton imgBtnDaycare;
//    private Button btnAddPetDashboard;
//    private TextView headerUsername; // TextView for username in the header
//    private DrawerLayout drawerLayout;
//    private NavigationView navigationView;
//    private Toolbar toolbar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_user_dashboard);
//
//        // Initialize navigation components
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
//        toolbar = findViewById(R.id.toolbar);
//
//        // Set up toolbar
//        setSupportActionBar(toolbar);
//
//        // Set up navigation drawer
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        navigationView.bringToFront();
//        navigationView.setNavigationItemSelectedListener(this);
//
//        // Check if the user is logged in
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
//
//        if (!isLoggedIn) {
//            // Redirect to SignIn Activity
//            Intent intent = new Intent(UserDashboard.this, SignIn.class);
//            startActivity(intent);
//            finish(); // Close the current activity
//            return; // Exit onCreate
//        }
//
//        // For "Welcome, User" in the dashboard
//        String userName = sharedPreferences.getString("usersName", "User");
//        TextView usersNameTextView = findViewById(R.id.usersName);
//        usersNameTextView.setText(userName); // Display just the first name in dashboard
//
//        // Set the first_name and last_name in the Navigation Drawer header
//        String lastName = sharedPreferences.getString("last_name", "LastName");
//        String fullName = userName + " " + lastName;  // Concatenate first and last name
//
//        // For setting the full name in the Navigation Drawer header
//        View headerView = navigationView.getHeaderView(0);
//        TextView headerUsernameTextView = headerView.findViewById(R.id.header_username);
//        headerUsernameTextView.setText(fullName);  // Set full name in the header
//
//        ViewCompat.setOnApplyWindowInsetsListener(drawerLayout, (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        imgBtnPetSitting = findViewById(R.id.imgBtnPetSitting);
//        imgBtnDaycare = findViewById(R.id.imgBtnDaycare);
//
//        ImageButton imgBtnAppointments = findViewById(R.id.imgBtnAppointments);
//        imgBtnAppointments.setOnClickListener(v -> {
//            Intent intent = new Intent(UserDashboard.this, PetAppointments.class);
//            startActivity(intent);
//        });
//
//        // Boarding button
//        ImageButton imgBtnBoarding = findViewById(R.id.imgBtnBoarding);
//        imgBtnBoarding.setOnClickListener(v -> {
//            Intent intent = new Intent(UserDashboard.this, PetBoarding.class);
//            startActivity(intent);
//        });
//
//        // Disable these buttons for now
//        imgBtnPetSitting.setEnabled(false);
//        imgBtnDaycare.setEnabled(false);
//
//        // Add pet button
//        Button btnAddPetDashboard = findViewById(R.id.btnAddPetDashboard);
//        btnAddPetDashboard.setOnClickListener(v -> {
//            Intent intent = new Intent(UserDashboard.this, PetProfile.class);
//            startActivity(intent);
//        });
//
//        // Web View Activity - Learn More Button
//        Button btnLearMore = findViewById(R.id.btnLearnMore);
//        btnLearMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(UserDashboard.this, WebView.class);
//                intent.putExtra("url", "https://hamugawaypethotel.wixsite.com/hamugawaypetboarding");
//                startActivity(intent);
//            }
//        });
//
//        // Check if the user is coming back from InputUserProfile_page1
//        boolean openDrawer = getIntent().getBooleanExtra("openDrawer", false);
//        if (openDrawer) {
//            drawerLayout.openDrawer(GravityCompat.START);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        // Close navigation drawer if open
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        final Intent intent; // Declare intent as final
//
//        // Use if-else structure to handle navigation item selection
//        if (menuItem.getItemId() == R.id.nav_AccInfo) {
//            // Redirect to InputUserProfilePage1
//            intent = new Intent(UserDashboard.this, UserProfileDisplay1.class); // Ensure class name matches
//        } else if (menuItem.getItemId() == R.id.nav_TandConditons) {
//            intent = new Intent(UserDashboard.this, TermsCondition.class);
//        } else if (menuItem.getItemId() == R.id.nav_HandFAQs) {
//            intent = new Intent(UserDashboard.this, HelpAndFAQs.class);
//        } else if (menuItem.getItemId() == R.id.nav_settings) {
//            intent = new Intent(UserDashboard.this, Settings.class);
//        } else if (menuItem.getItemId() == R.id.nav_logout) {
//            // Clear user session and redirect to SignIn
//            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean("isLoggedIn", false); // Update login status
//            editor.apply();
//
//            intent = new Intent(UserDashboard.this, SignIn.class);
//        } else {
//            intent = null; // Assign null if no valid item is selected
//        }
//
//        if (intent != null) {
//            // Delay the transition to give a smooth effect
//            new Handler().postDelayed(() -> {
//                startActivity(intent);
//                finish(); // Close the current activity
//            }, 1000);
//        }
//
//        // Close the navigation drawer
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//}


package com.example.mypetshub;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearSnapHelper;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserDashboard extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<String> dataSource;
    LinearLayoutManager linearLayoutManager;
    RecyclerViewAdapterPetDashboard myRvAdapter;

    private static final String TAG = "UserDashboard";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    private RecyclerView recyclerView;
    private SliderAdapter adapter;
    private Handler autoScrollHandler;
    private Runnable autoScrollRunnable;
    private int currentPosition = 0;
    private EditText checkInCalendar, checkOutCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        // Check if the user is logged in
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            // Redirect to SignIn Activity
            Intent intent = new Intent(UserDashboard.this, SignIn.class);
            startActivity(intent);
            finish();
            return;
        }

        fetchPetData();

        // For "Welcome, User!" in the dashboard
        String userName = sharedPreferences.getString("usersName", "User");
        TextView usersNameTextView = findViewById(R.id.usersName);
        usersNameTextView.setText(userName);

        // check availability calendar
        checkInCalendar = findViewById(R.id.checkIn_editText);
        checkOutCalendar = findViewById(R.id.checkOut_editText);

        checkInCalendar.setOnClickListener(v -> showDatePickerDialog_checkIn(checkInCalendar));
        checkOutCalendar.setOnClickListener(v -> showDatePickerDialog_checkOut(checkOutCalendar));


        ImageButton checkavailbtn = findViewById(R.id.checkavailbtn);
        checkavailbtn.setOnClickListener(view -> {
            Intent intent = new Intent(UserDashboard.this, PetBoarding.class);

            String checkInDate = checkInCalendar.getText().toString();
            String checkOutDate = checkOutCalendar.getText().toString();

            intent.putExtra("CHECK_IN_DATE", checkInDate);
            intent.putExtra("CHECK_OUT_DATE", checkOutDate);

            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recyclerView);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.slide1_v6, ""));
        sliderItems.add(new SliderItem(R.drawable.slide2_v6, ""));
        sliderItems.add(new SliderItem(R.drawable.slide3_v6, ""));

        // Set up RecyclerView and adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new SliderAdapter(sliderItems, this);
        recyclerView.setAdapter(adapter);

        // snap helper for smooth swiping
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        // auto-scrolling
        autoScrollHandler = new Handler();
        autoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                currentPosition = (currentPosition + 1) % sliderItems.size();
                recyclerView.smoothScrollToPosition(currentPosition);
                autoScrollHandler.postDelayed(this, 5000);
            }
        };
        autoScrollHandler.postDelayed(autoScrollRunnable, 3000);

        // Add Pet Button
        Button btnAddPetDashboard = findViewById(R.id.addpetbtndashboard);
        btnAddPetDashboard.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboard.this, PetProfileInput.class);
            startActivity(intent);
        });

        // show rating dialog
        TextView review_Button = findViewById(R.id.review_Button);
        review_Button.setOnClickListener(view -> {
            RateUsDialog rateUsDialog = new RateUsDialog(UserDashboard.this);
            rateUsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            rateUsDialog.setCancelable(false);
            rateUsDialog.show();
        });



        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.bottom_home);

        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                return true;
            } else if (item.getItemId() == R.id.bottom_boarding) {
                startActivity(new Intent(getApplicationContext(), PetBoarding.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_appointments) {
                startActivity(new Intent(getApplicationContext(), PetAppointments.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
                finish();
                return true;
            }
            return false;
        });

        FloatingActionButton fabMyPets = findViewById(R.id.mypets_fab);
        fabMyPets.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), PetProfile.class));
        });
    }

    // commented on October 10, 2024 11:15 PM

    private void fetchPetData() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("id", -1);

        if (userId == -1) {
            // Handle case where user_id is not found (user not logged in)
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        String urlString = "https://hamugaway.scarlet2.io/get_pets.php?user_id=" + userId;

        // Moved to ExecutorService for better thread management
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Set timeouts to avoid long delays in case of slow networks
                connection.setConnectTimeout(5000); // 5 seconds
                connection.setReadTimeout(5000); // 5 seconds
                connection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();

                String finalResult = result.toString();
                handler.post(() -> processPetData(finalResult));

            } catch (Exception e) {
                e.printStackTrace();
                handler.post(() -> Toast.makeText(UserDashboard.this, "Failed to fetch pet data", Toast.LENGTH_SHORT).show());
            }
        });
    }

    // Separate method for processing the JSON data
    private void processPetData(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);

            if (jsonArray.length() > 0) {
                ArrayList<String> petNames = new ArrayList<>();
                ArrayList<String> petImageUrls = new ArrayList<>();

                // Loop through each pet in the response
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject pet = jsonArray.getJSONObject(i);
                    String petName = pet.getString("pet_name");
                    String petImageUrl = pet.getString("pet_image");

                    // Add to lists
                    petNames.add(petName);
                    petImageUrls.add(petImageUrl);
                }

                // Initialize RecyclerView with the pet data
                initRecyclerView(petNames, petImageUrls);
            } else {
                Toast.makeText(UserDashboard.this, "No pets added yet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(UserDashboard.this, "Error processing pet data", Toast.LENGTH_SHORT).show();
        }
    }


    private void initRecyclerView(ArrayList<String> petNames, ArrayList<String> petImageUrls) {
        Log.d(TAG, "initRecyclerView: Initializing RecyclerView");

        RecyclerView recyclerView = findViewById(R.id.petDisplay_dashboard);
        List<Pet2> petList = new ArrayList<>();

        for (int i = 0; i < petNames.size(); i++) {
            // Assuming petId and userId can be set to default values (e.g., 0 or -1)
            Pet2 pet = new Pet2(i, -1, petNames.get(i), petImageUrls.get(i));
            petList.add(pet);
        }

        PetAdapter2 adapter = new PetAdapter2(this, petList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


    private void showDatePickerDialog_checkIn(EditText checkInCalendar) {

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
                    checkInCalendar.setText(formattedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void showDatePickerDialog_checkOut(EditText checkOutCalendar) {

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
                    checkOutCalendar.setText(formattedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        autoScrollHandler.removeCallbacks(autoScrollRunnable);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
