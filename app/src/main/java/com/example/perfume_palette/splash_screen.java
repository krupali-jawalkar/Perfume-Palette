package com.example.perfume_palette;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 1000;
    private Databasehelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Initialize database helper
        databaseHelper = new Databasehelper(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Check if user is already logged in
                if (databaseHelper.isLoggedIn()) {
                    // User is logged in, navigate to home activity
                    Intent homeIntent = new Intent(splash_screen.this, home.class);
                    startActivity(homeIntent);
                } else {
                    // User is not logged in, navigate to main activity (login page)
                    Intent mainIntent = new Intent(splash_screen.this, MainActivity.class);
                    startActivity(mainIntent);
                }
                finish(); // Close the splash screen activity
            }
        }, SPLASH_TIME_OUT);
    }
}
