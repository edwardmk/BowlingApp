package com.bowling.edward.bowling;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText mail, pword;
    Button login, goToRegister;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mail = findViewById(R.id.email);
        pword = findViewById(R.id.password);
        login = findViewById(R.id.loginButton);
        goToRegister = findViewById(R.id.toRegister);

        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SignIn();
                }
                catch (IllegalArgumentException e){
                    NoSignIn();
                }
            }
        });

        goToRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                GoToRegister();
            }

        });

    }

    public void GoToRegister(){
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }

    public void SignIn() {
        String email = mail.getText().toString();
        String password = pword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent i = new Intent(Login.this, HomePage.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }

                }
        });
    }
    public void NoSignIn(){
        Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
    }





}
