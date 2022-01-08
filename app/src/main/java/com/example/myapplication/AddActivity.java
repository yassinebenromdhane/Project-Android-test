package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.Article;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    EditText name , prix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = findViewById(R.id.nameArt);
        prix = findViewById(R.id.prix);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference().child("articles");
        Button b1 = findViewById(R.id.add);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = db.push().getKey();
                Article a = new Article(
                        id ,
                        name.getText().toString(),
                        prix.getText().toString()

                );

                Map<String,Object> map=new HashMap<>();
                map.put("id",a.getId());
                map.put("name",a.getNom());
                map.put("desc",a.getPrix());


                db.child(id)
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                name.setText("");
                                prix.setText("");
                                Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                                Intent i= new Intent(AddActivity.this,MainActivity.class);
                                startActivity(i);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}