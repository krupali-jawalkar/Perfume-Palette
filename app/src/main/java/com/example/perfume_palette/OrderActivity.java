package com.example.perfume_palette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.perfume_palette.Adapters.OrdersAdapter;
import com.example.perfume_palette.Models.OrdersModel;
import com.example.perfume_palette.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;
    TextView textViewSubtotal, textViewDelivery, textViewTax, textViewTotal;
    int subtotal = 0;
    int deliveryPrice = 0;
    int tax = 0;
    int total = 0;
    private Databasehelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new Databasehelper(this);

        textViewSubtotal = findViewById(R.id.textView7);
        textViewDelivery = findViewById(R.id.textView9);
        textViewTax = findViewById(R.id.textView10);
        textViewTotal = findViewById(R.id.textView12);

        // Initialize order summary
        updateOrderSummary();

        binding.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if user has already provided address
                if (!databaseHelper.hasAddress()) {
                    // User has not provided address, navigate to Address activity
                    Intent intent = new Intent(OrderActivity.this, Address.class);
                    startActivity(intent);
                } else {
                    // User has already provided address, proceed with checkout
                    // Implement your checkout logic here
                    // For now, simply display a toast message
                    Intent intent = new Intent(OrderActivity.this, successfull.class);
                    startActivity(intent);
                }
            }
        });


        Button addButton = findViewById(R.id.newadd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdd();
            }
        });

        // Check if cart is empty
        if (databaseHelper.getOrders().isEmpty()) {
            binding.checkout.setEnabled(false);
            binding.checkout.setText("Cart is Empty"); // Optionally, you can provide a message
        } else {
            binding.checkout.setEnabled(true);
        }

        Databasehelper mydb = new Databasehelper(this);
        ArrayList<OrdersModel> list = mydb.getOrders();

        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);

        // Set click listeners on bottle size buttons to update order summary
        binding.size1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update order summary based on selected bottle size
                subtotal = 88; // Example: update subtotal for 30ml bottle
                updateOrderSummary();
            }
        });

        binding.size2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update order summary based on selected bottle size
                subtotal = 164; // Example: update subtotal for 30ml bottle
                updateOrderSummary();
            }
        });

        binding.size3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update order summary based on selected bottle size
                subtotal = 188; // Example: update subtotal for 30ml bottle
                updateOrderSummary();
            }
        });


        binding.size4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update order summary based on selected bottle size
                subtotal = 269; // Example: update subtotal for 30ml bottle
                updateOrderSummary();
            }
        });

    }

    private void openAdd() {
        Intent intent = new Intent(this, Address.class);
        startActivity(intent);
    }

    // Method to update order summary based on selected bottle size
    private void updateOrderSummary() {
        // Calculate tax and delivery price (you can replace these with your actual calculation logic)
        tax = (int) (subtotal * 0.1); // Assuming tax rate is 10%
        deliveryPrice = 50; // Assuming a flat delivery price of ₹50

        total = subtotal + tax + deliveryPrice;

        // Set text to TextViews
        textViewSubtotal.setText("₹" + subtotal);
        textViewDelivery.setText("₹" + deliveryPrice);
        textViewTax.setText("₹" + tax);
        textViewTotal.setText("₹" + total);

        if (subtotal == 0) {
            // Reset prices to 0
            textViewSubtotal.setText("₹0");
            textViewTax.setText("₹0");
            textViewTotal.setText("₹0");
        }
    }



}

