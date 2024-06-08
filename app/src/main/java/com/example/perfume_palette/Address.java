package com.example.perfume_palette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Address extends AppCompatActivity {
    private EditText fullNameEditText, buildingEditText, areaEditText, cityEditText, stateEditText, pincodeEditText;
    private Button submitButton;
    private Databasehelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        // Initialize Databasehelper
        databaseHelper = new Databasehelper(this);

        // Get references to EditText fields and Submit button
        fullNameEditText = findViewById(R.id.fullname);
        buildingEditText = findViewById(R.id.building);
        areaEditText = findViewById(R.id.area);
        cityEditText = findViewById(R.id.city);
        stateEditText = findViewById(R.id.state);
        pincodeEditText = findViewById(R.id.pincode);
        submitButton = findViewById(R.id.submit_button);

        pincodeEditText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);

        // Set OnClickListener on the Submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the text entered by the user
                String fullName = fullNameEditText.getText().toString().trim();
                String building = buildingEditText.getText().toString().trim();
                String area = areaEditText.getText().toString().trim();
                String city = cityEditText.getText().toString().trim();
                String state = stateEditText.getText().toString().trim();
                String pincode = pincodeEditText.getText().toString().trim();

                // Validate user input
                if (fullName.isEmpty() || building.isEmpty() || area.isEmpty() || city.isEmpty() || state.isEmpty() || pincode.isEmpty()) {
                    // Display a toast message if any field is empty
                    Toast.makeText(Address.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else {
                        // Insert the address data into the database
                        boolean inserted = databaseHelper.insertAddressData(fullName, building, area, city, state, pincode);
                        if (inserted) {
                            // If insertion is successful, display success message and navigate to the next activity
                            Toast.makeText(Address.this, "Address saved successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Address.this, successfull.class);
                            startActivity(intent);
                        } else {
                            // If insertion fails, display an error message
                            Toast.makeText(Address.this, "Failed to save address", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

        });
    }
}
