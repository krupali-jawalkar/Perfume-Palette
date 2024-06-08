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
import com.example.perfume_palette.databinding.ActivityAllFruityBinding;

import java.util.ArrayList;

public class All_Fruity extends AppCompatActivity {

    ActivityAllFruityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllFruityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.raspberry, "Raspberry", "Sweet, Fruity, Vibrant","71"));
        list.add(new MainModel(R.drawable.cherry, "Cherry", "Juicy, Luscious, Tempting","79"));
        list.add(new MainModel(R.drawable.apricot, "Apricot", "Fresh, Delicate, Sunny","80"));
        list.add(new MainModel(R.drawable.plum, "Plum", "Rich, Velvety, Sensual","92"));
        list.add(new MainModel(R.drawable.peach, "Peach", "Summery, Peachy, Refreshing","94"));
        list.add(new MainModel(R.drawable.blackcurrant, "Blackcurrant", "Tart, Berrylicious, Bold","93"));

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
                startActivity(new Intent(All_Fruity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
