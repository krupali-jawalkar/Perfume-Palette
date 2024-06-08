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
import com.example.perfume_palette.databinding.ActivityAllFreshBinding;

import java.util.ArrayList;

public class All_Fresh extends AppCompatActivity {

    ActivityAllFreshBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllFreshBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.aloevera, "Aloevera", "Green, Fresh, Soothing","60"));
        list.add(new MainModel(R.drawable.bamboo, "Bamboo", "Woody, Green, Fresh","70"));
        list.add(new MainModel(R.drawable.pine, "Pine", "Foresty, Evergreen, Woody","75"));
        list.add(new MainModel(R.drawable.neroli_floral, "Fresh Cut Grass", "Green, Fresh, Earthy","82"));
        list.add(new MainModel(R.drawable.rain, "Rain", "Petrichor, Fresh, Clean","85"));

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
                startActivity(new Intent(All_Fresh.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
