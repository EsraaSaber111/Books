package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SellActivity extends AppCompatActivity {

    EditText bookname,booktype,years,bookprice;
    Button AddBook;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        bookname =findViewById(R.id.bookname);
        booktype =findViewById(R.id.booktype);
        years = findViewById(R.id.bookage);
        bookprice=findViewById(R.id.bookprice);
        AddBook=findViewById(R.id.addbutton);

    }

    public void sell(View view) {
        final String name = bookname.getText().toString().trim();
        final String type = booktype.getText().toString().trim();
        final String year = years.getText().toString().trim();
        final String price = bookprice.getText().toString().trim();


        if (name.isEmpty() || (type.isEmpty()) || (year.isEmpty()) || (price.isEmpty())) {
            Toast.makeText(this, "please enter all information", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> books = new HashMap<>();
        books.put("Name", name);
        books.put("type", type);
        books.put("years", year);
        books.put("price", price);

// Add a new document with a generated ID
        db.collection("books")
                .add(books)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(SellActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(SellActivity.this,MainActivity.class);
                        startActivity(intent);
                       // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       // Log.w(TAG, "Error adding document", e);
                        Toast.makeText(SellActivity.this, "Failed .. please try again", Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(SellActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });

    }
}
