package com.example.perfume_palette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

// Databasehelper mydb;
    Button loginButton;
    Button signupButton;
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      Databasehelper  mydb= new Databasehelper(this);

        loginButton = findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v)
            {
                // Intent to navigate to the Login Activity
                Intent intent = new Intent(MainActivity.this, login_activity.class);
                startActivity(intent);
            }

        });

        signupButton = findViewById(R.id.signup_btn);
        signupButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Intent to navigate to the Signup Activity
                Intent intent = new Intent(MainActivity.this, signup_activity.class);
                startActivity(intent);
            }
        });


    }

        private void navigateToBottomNavigationPage() {
        // Navigate to the bottom navigation page
        Intent intent = new Intent(MainActivity.this, home.class);
        startActivity(intent);
        finish(); //
    }
}

