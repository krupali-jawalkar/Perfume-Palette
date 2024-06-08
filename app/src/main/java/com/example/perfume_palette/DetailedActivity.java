package com.example.perfume_palette;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Import Log class for logging
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.perfume_palette.databinding.ActivityDetailedBinding;

public class DetailedActivity extends AppCompatActivity {
    ActivityDetailedBinding binding;
    private String[] perfumePrices; // Array to store prices for the current perfume
    private Button selectedButton; // Track the selected button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int image = getIntent().getIntExtra("image", 0);
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("desc");
        int perfumeIndex = getIntent().getIntExtra("perfumeIndex", 0); // Retrieve the index of the current perfume

        // Log the value of perfumeIndex
        Log.d("PerfumeIndex", "Perfume Index: " + perfumeIndex);

        binding.perfumeimage.setImageResource(image);
        binding.detailName.setText(name);
        binding.detailSmell.setText(description);

        // Retrieve the prices for the current perfume
        perfumePrices = getPerfumePrices(perfumeIndex);

        // Set click listeners for the size buttons
        binding.size1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrice(0); // Update price for 30ml bottle
                selectButton(binding.size1); // Change background color of the selected button
            }
        });

        binding.size2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrice(1); // Update price for 50ml bottle
                selectButton(binding.size2); // Change background color of the selected button
            }
        });

        binding.size3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrice(2); // Update price for 70ml bottle
                selectButton(binding.size3); // Change background color of the selected button
            }
        });

        binding.size4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrice(3); // Update price for 80ml bottle
                selectButton(binding.size4); // Change background color of the selected button
            }
        });

        // Initially, update the price for the default bottle size
        updatePrice(0);

        Databasehelper mydb = new Databasehelper(this);


        binding.addTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mydb.isItemInCart(name)) {
                    // Item already exists in the cart, display a message
                    Toast.makeText(DetailedActivity.this, "Item already exists", Toast.LENGTH_SHORT).show();
                } else {

                    boolean isInserted = mydb.insertOrder(name, description, image);

                    if (isInserted)
                        Toast.makeText(DetailedActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailedActivity.this, "Reached Maximum Limit", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to retrieve prices for the current perfume
    private String[] getPerfumePrices(int perfumeIndex) {
        // Implement logic to retrieve prices for the current perfume
        // You can use switch-case or if-else statements to determine prices based on the perfume index
        // For example:
        switch (perfumeIndex) {
            case 0: // Floral Perfume
                return new String[]{
                        getString(R.string.fl_price_30ml),
                        getString(R.string.fl_price_50ml),
                        getString(R.string.fl_price_70ml),
                        getString(R.string.fl_price_80ml)
                };
            case 1: // Aquatic Perfume
                return new String[]{
                        getString(R.string.aq_price_30ml),
                        getString(R.string.aq_price_50ml),
                        getString(R.string.aq_price_70ml),
                        getString(R.string.aq_price_80ml)
                };
            case 2: // Fresh Perfume
                return new String[]{
                        getString(R.string.fre_price_30ml),
                        getString(R.string.fre_price_50ml),
                        getString(R.string.fre_price_70ml),
                        getString(R.string.fre_price_80ml)
                };
            case 3: // Aromatic Perfume
                return new String[]{
                        getString(R.string.aro_price_30ml),
                        getString(R.string.aro_price_50ml),
                        getString(R.string.aro_price_70ml),
                        getString(R.string.aro_price_80ml)
                };
            case 4: // Citrus Perfume
                return new String[]{
                        getString(R.string.cit_price_30ml),
                        getString(R.string.cit_price_50ml),
                        getString(R.string.cit_price_70ml),
                        getString(R.string.cit_price_80ml)
                };
            case 5: // Fruity Perfume
                return new String[]{
                        getString(R.string.fru_price_30ml),
                        getString(R.string.fru_price_50ml),
                        getString(R.string.fru_price_70ml),
                        getString(R.string.fru_price_80ml)
                };
            default:
                // Return default prices or handle other cases
                return new String[0];
        }
    }

    private void updatePrice(int index) {
        // Set the price text view to the selected bottle size price
        binding.detailPrice.setText(perfumePrices[index]);
    }

    private void selectButton(Button button) {
        if (selectedButton != null) {
            // Reset the background color of the previously selected button
            selectedButton.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
        }

        // Change the background color of the selected button
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.brown));

        // Update the reference to the currently selected button
        selectedButton = button;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orders:
                startActivity(new Intent(DetailedActivity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
