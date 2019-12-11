package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    Button loginpage,registerpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        loginpage=findViewById(R.id.Loginchoise);
        registerpage=findViewById(R.id.registerchoise);
    }


    public void LoginPage(View view) {
        Intent intent = new Intent(StartActivity.this,Login.class);
        startActivity(intent);
    }

    public void RegisterPage(View view) {
        Intent intent = new Intent(StartActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}
