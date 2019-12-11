package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BuyActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    private RecycleAdapter adapter;
    List<book> books=new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore mDatabase = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = mDatabase.collection("books");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ShowData();
    }

    private void ShowData() {


        mDatabase.collection("books")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        for (DocumentSnapshot doc : task.getResult()) {
                            book it = new book(doc.getString("Name"), doc.getString("price"));
                            books.add(it);

                        }

                        adapter = new RecycleAdapter(BuyActivity.this,books);
                        recyclerView.setAdapter(adapter);


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(BuyActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
