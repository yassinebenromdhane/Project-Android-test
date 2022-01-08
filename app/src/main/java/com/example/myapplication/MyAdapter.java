package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Model.Article;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {

    Context context;

    ArrayList<Article> list;




    public MyAdapter(Context context, ArrayList<Article> list) {
        this.context = context;
        this.list = list;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Article a = list.get(position);

        holder.id.setText(a.getId());
        holder.name.setText(a.getNom());
        holder.prix.setText(a.getPrix());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id, name, prix;

        Button edit , remove;
        private  final  Context context;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            prix = itemView.findViewById(R.id.prix);

            edit = itemView.findViewById(R.id.edit);
            remove = itemView.findViewById(R.id.delete);




        }

    }
}