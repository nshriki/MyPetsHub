package com.example.mypetshub;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    public static String getResponseFromUrl(String urlString) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000); // 10 seconds
            urlConnection.setReadTimeout(10000);    // 10 seconds
            urlConnection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
