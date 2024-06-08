package com.example.perfume_palette.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume_palette.Databasehelper;
import com.example.perfume_palette.Models.OrdersModel;
import com.example.perfume_palette.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private ArrayList<OrdersModel> list;
    private Context context;
    private Databasehelper mydb;

    public OrdersAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
        mydb = new Databasehelper(context); // Initialize Databasehelper
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrdersModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.soldItemName.setText(model.getSoldItemName());
       // holder.orderPrice.setText(model.getOrderPrice());

        // Set click listener for the remove button
        holder.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition(); // Get adapter position
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    removeItem(adapterPosition); // Remove item using adapter position
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Method to remove item from the list and database
    private void removeItem(int position) {
        OrdersModel model = list.get(position);
        boolean isRemoved = mydb.removeOrder(model.getSoldItemName());
        if (isRemoved) {
            list.remove(position); // Remove item from the list
            notifyItemRemoved(position); // Notify adapter about item removal
            notifyItemRangeChanged(position, getItemCount()); // Refresh items after removal
            Toast.makeText(context, "Item removed from cart", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to remove item from cart", Toast.LENGTH_SHORT).show();
        }
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView orderImage;
        TextView soldItemName;
        Button buttonRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.orderImage);
            soldItemName = itemView.findViewById(R.id.soldItemName);
            buttonRemove = itemView.findViewById(R.id.buttonRemove);
        }
    }
}
