package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.CustomViewHolder> {

BuyActivity buyActivity;
List<book> books;
Context context;


    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View myView;

        TextView name,price;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            name=myView.findViewById(R.id.name);
            price=myView.findViewById(R.id.price);

        }
    }

    public RecycleAdapter(BuyActivity buyActivity, List<book> books) {
        this.buyActivity = buyActivity;
        this.books = books;

    }


        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
                return new CustomViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.name.setText(books.get(position).getName());
        holder.price.setText(books.get(position).getPrice());



    }



    @Override
    public int getItemCount() {
        return books.size();
    }



        }



