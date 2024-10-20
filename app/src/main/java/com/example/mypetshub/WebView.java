package com.example.mypetshub;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class WebView extends AppCompatActivity {

    private android.webkit.WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webView);

        // Enable JavaScript for WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Open URLs in the WebView instead of a browser
        webView.setWebViewClient(new WebViewClient());

        // Get the URL from the Intent
        String url = getIntent().getStringExtra("url");

        // Load the URL
        if (url != null) {
            webView.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed() {
        // If the user presses the back button, go back in the web view's history if possible
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
