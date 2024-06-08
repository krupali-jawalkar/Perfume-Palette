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
import com.example.perfume_palette.databinding.ActivityAllAromaticBinding;

import java.util.ArrayList;

public class All_Aromatic extends AppCompatActivity {

    ActivityAllAromaticBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllAromaticBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.rosemary, "Rosemary", "Herbal, Woody, Invigorating", "69"));
        list.add(new MainModel(R.drawable.jasmine_floral, "Cumin", "Spicy, Earthy, Warm", "74"));
        list.add(new MainModel(R.drawable.lily_floral, "Sage", "Herbal, Fresh, Cleansing", "80"));
        list.add(new MainModel(R.drawable.mint, "Mint", "Fresh, Cooling, Refreshing", "80"));
        list.add(new MainModel(R.drawable.thyme, "Thyme", "Herbaceous, Aromatic, Warm", "90"));
        list.add(new MainModel(R.drawable.tuberose_floral, "Mugwort", "Earthy, Herbal, Mystical", "92"));

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
                startActivity(new Intent(All_Aromatic.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
