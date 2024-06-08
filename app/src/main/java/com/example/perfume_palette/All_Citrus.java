package com.example.perfume_palette;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.perfume_palette.Adapters.MainAdapter;
import com.example.perfume_palette.Models.MainModel;
import com.example.perfume_palette.databinding.ActivityAllCitrusBinding;

import java.util.ArrayList;

public class All_Citrus extends AppCompatActivity {

    ActivityAllCitrusBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllCitrusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.lemon, "Lemon", "Citrusy, Fresh, Zesty", "67"));
        list.add(new MainModel(R.drawable.orange, "Orange", "Sweet, Citrusy, Juicy","77"));
        list.add(new MainModel(R.drawable.berganot, "Bergamot", "Citrusy, Floral, Refreshing","80"));
        list.add(new MainModel(R.drawable.pomelo, "Pomela", "Citrusy, Tropical, Tangy","85"));
        list.add(new MainModel(R.drawable.grapefruit, "Grapefruit", "Citrusy, Tangy, Energizing","90"));
        list.add(new MainModel(R.drawable.tangerine, "Tangerine", "Citrusy, Sweet, Bright","92"));

        MainAdapter adapter= new MainAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(All_Citrus.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
