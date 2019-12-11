package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    EditText username,password;
    FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username=findViewById(R.id.Lusername);
        password=findViewById(R.id.Lpassword);
        login=findViewById(R.id.button);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

    }

    public void loginmethod(View view) {

        {
            String email = username.getText().toString().trim();
            String Password = password.getText().toString().trim();
            if(TextUtils.isEmpty(email)){
                Intent intent=new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
            if(TextUtils.isEmpty(Password))
            {
                Toast.makeText(Login.this, "please enter password", Toast.LENGTH_SHORT).show();
                return;
            }



            firebaseAuth.signInWithEmailAndPassword(email, Password)
                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                            } else {
                                Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }

    }


}
