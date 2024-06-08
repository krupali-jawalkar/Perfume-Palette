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
import com.example.perfume_palette.databinding.ActivityAllAquaticBinding;

import java.util.ArrayList;

public class All_Aquatic extends AppCompatActivity {

    ActivityAllAquaticBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllAquaticBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();


        list.add(new MainModel(R.drawable.seaweed, "Sea Salt", "Fresh, Salty, Oceanic", "70"));
        list.add(new MainModel(R.drawable.seaweed, "SeaWeed", "Marine, Green, Briny", "82"));
        list.add(new MainModel(R.drawable.cucumber, "Cucumber", "Crisp, Refreshing, Green", "71"));
        list.add(new MainModel(R.drawable.wetstone, "Wet Stone", "Earthy, Mineral, Clean", "89"));
        list.add(new MainModel(R.drawable.coralreef, "Coral Reef", "Tropical, Aquatic, Vibrant", "73"));

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
                startActivity(new Intent(All_Aquatic.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
