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
import com.example.perfume_palette.databinding.ActivityAllFloralBinding;

import java.util.ArrayList;

public class All_Floral extends AppCompatActivity {

    ActivityAllFloralBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllFloralBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.rose_floral, "Rose", "Romantic, Floral, Elegant","80"));
        list.add(new MainModel(R.drawable.jasmine_floral, "Jasmine", "Exotic, Intense, Floral","70"));
        list.add(new MainModel(R.drawable.lily_floral, "Lily", "Delicate, Fresh, Feminine","60"));
        list.add(new MainModel(R.drawable.neroli_floral, "Neroli", "Citrusy, Floral, Refreshing","90"));
        list.add(new MainModel(R.drawable.lavender_floral, "Lavender", "Calming, Herbal, Soothing","65"));
        list.add(new MainModel(R.drawable.tuberose_floral, "Tuberose", "Sensual, Rich, Floral","78"));

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
                startActivity(new Intent(All_Floral.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
