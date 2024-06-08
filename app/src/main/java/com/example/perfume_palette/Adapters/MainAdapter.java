package com.example.perfume_palette.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume_palette.DetailedActivity;
import com.example.perfume_palette.Models.MainModel;
import com.example.perfume_palette.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewholder> {
    ArrayList<MainModel> list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        final MainModel model= list.get(position);
        holder.perfumeimage.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.price.setText(model.getPrice());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("name", model.getName());
                intent.putExtra("image",model.getImage());
                intent.putExtra("desc",model.getDescription());
                intent.putExtra("price",model.getPrice());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView perfumeimage;
        TextView name, description, price;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            perfumeimage= itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.PerfumeName);
          description=itemView.findViewById(R.id.description);
            price=itemView.findViewById(R.id.perfumePrice);

        }
    }
}
