package com.example.mypetshub;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.codesgood.views.JustifiedTextView;

public class TermsCondition extends AppCompatActivity {
    private ImageButton backBtnTermsCondi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_terms_condition);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backBtnTermsCondi = findViewById(R.id.backBtnTermsCondi);
        backBtnTermsCondi.setOnClickListener(v -> {
            super.onBackPressed();
        });

//        JustifiedTextView justifiedTextView_4a = findViewById(R.id.tc_4a_desc);
//        String htmlText_4a = getString(R.string.tc_4a);
//        justifiedTextView_4a.setText(Html.fromHtml(htmlText_4a));
//
//        JustifiedTextView justifiedTextView_4b = findViewById(R.id.tc_4b_desc);
//        String htmlText_4b = getString(R.string.tc_4b);
//        justifiedTextView_4b.setText(Html.fromHtml(htmlText_4b));
//
//        JustifiedTextView justifiedTextView_4c = findViewById(R.id.tc_4c_desc);
//        String htmlText_4c = getString(R.string.tc_4c);
//        justifiedTextView_4c.setText(Html.fromHtml(htmlText_4c));
//
//        JustifiedTextView justifiedTextView_4d = findViewById(R.id.tc_4d_desc);
//        String htmlText_4d = getString(R.string.tc_4d);
//        justifiedTextView_4d.setText(Html.fromHtml(htmlText_4d));
//
//        JustifiedTextView justifiedTextView_5a = findViewById(R.id.tc_5a_desc);
//        String htmlText_5a = getString(R.string.tc_5a);
//        justifiedTextView_5a.setText(Html.fromHtml(htmlText_5a));
//
//        JustifiedTextView justifiedTextView_5b = findViewById(R.id.tc_5b_desc);
//        String htmlText_5b = getString(R.string.tc_5b);
//        justifiedTextView_5b.setText(Html.fromHtml(htmlText_5b));

    }

    @Override
    public void onBackPressed() {
        Log.d("TermsCondition", "Back button pressed");
        super.onBackPressed(); // Ensure the default behavior is called
    }


}