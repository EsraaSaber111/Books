package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,confirm,email;
    Button newAccount;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        username=findViewById(R.id.Rusername);
        password=findViewById(R.id.Rpassword);
        confirm=findViewById(R.id.Rconfirmpassword);
        email=findViewById(R.id.Remail);
        newAccount=findViewById(R.id.button);
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }
    public void Register(View view) {
        registerUser();
    }

    private void registerUser() {
        final String name = username.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        String confirmpass = confirm.getText().toString().trim();
        final String mail = email.getText().toString().trim();


        if (name.isEmpty() || (pass.isEmpty()) || (mail.isEmpty()) || (confirmpass.isEmpty())) {
            Toast.makeText(this, "please enter all information", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            Toast.makeText(this, "e-mail is not accepted", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!confirmpass.equals(pass)) {
            Toast.makeText(this, "password confirmation failed", Toast.LENGTH_SHORT).show();
        }

        mAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Map<Object,String> userdata = new HashMap<>();
                            userdata.put("fullname",name);


                            firebaseFirestore.collection("users")
                                    .add(userdata)
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            if (task.isSuccessful()) {
                                                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                                startActivity(intent);

                                            }else {
                                                Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "failed register", Toast.LENGTH_SHORT).show();

                        }
                    }});
                }}