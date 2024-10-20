package com.example.mypetshub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WelcomeImages extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    int position = 0;
    ImageButton btnGetStarted;
    TextView txtGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_images);

        // when this activity is about to be launched, we need to check if its opened before or not
        if (restorePrefData()) {
            Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
            startActivity(intent);
            finish();
        }

        // ini views
        tabIndicator = findViewById(R.id.tab_indicator);
        btnGetStarted = findViewById(R.id.btnGetStarted);
        btnGetStarted.setVisibility(View.INVISIBLE);
        txtGetStarted = findViewById(R.id.txtGetStarted);
        txtGetStarted.setVisibility(View.INVISIBLE);

        // fill list screen
        List<OnboardScreenItem> mList = new ArrayList<>();
        mList.add(new OnboardScreenItem("DAILY WALKS\nAND EXERCISE", "Our dedicated staff ensures your pet receives\nregular daily walks and exercise, promoting their\nphysical and mental well-being.", R.drawable.onboardimg1));
        mList.add(new OnboardScreenItem("24/7 CARE AND\nSUPERVISION", "Constant supervision and care are provided to\nguarantee your pet's safety and comfort. Our staff is\nalways available to ensure your pet's needs are met.", R.drawable.onboardimg2));
        mList.add(new OnboardScreenItem("BOOKING MADE EASY", "Experience the convenience of our simplified booking\nsystem. Book your pet's stay with just a few clicks.", R.drawable.onboardimg3));

        // setup viewpager
        screenPager = findViewById(R.id.screen_viewPager2);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setup tab layout with viewpager
        tabIndicator.setupWithViewPager(screenPager);

        // reach to the last img / screen
        screenPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // do nothing
            }

            @Override
            public void onPageSelected(int position) {
                // when last page is reached
                if (position == mList.size() - 1) {
                    // TODO : show the GET STARTED Button and hide the indicator
                    loadLastScreen();
                } else {
                    // for other pages, show the tab indicator and hide GET STARTED button
                    tabIndicator.setVisibility(View.VISIBLE);
                    txtGetStarted.setVisibility(View.INVISIBLE);
                    btnGetStarted.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // do nothing
            }
        });

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);

                savePrefsData();
                finish();
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }

    //  show the GET STARTED Button and hide the indicator on the last screen / img / page
    private void loadLastScreen() {
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        txtGetStarted.setVisibility(View.VISIBLE);
    }
}