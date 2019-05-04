package com.bowling.edward.bowling;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    Button registerButton;
    EditText pword, email, confirmPword, username;
    private FirebaseAuth mAuth;

    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference myRef;
    static final String TAG = "AddToDatabase";
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton =  findViewById(R.id.registerButton);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });
}

    public void RegisterUser() {
        Log.d(TAG, "Submit pressed.");
        final String rUsername, rPword, rEmail, rConfirmPword;

        username = findViewById(R.id.rUserName);
        email = findViewById(R.id.rEmail);
        pword = findViewById(R.id.rPword);
        confirmPword = findViewById(R.id.rPword2);



        rUsername = username.getText().toString();
        rEmail = email.getText().toString();
        rPword = pword.getText().toString();
        rConfirmPword = confirmPword.getText().toString();
        if (!rUsername.equals("") && !rEmail.equals("") && !rPword.equals("") && !rConfirmPword.equals("")) {
            if (rPword.equals(rConfirmPword)) {
                mAuth.createUserWithEmailAndPassword(rEmail, rPword).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Register Unnsuccessful", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, " Attempting to submit to database: \n" +
                                    "username: " + username + "\n" +
                                    "email: " + email + "\n"
                            );
                            FirebaseUser user = mAuth.getCurrentUser();
                            userID = user.getUid();

                            User userInfo = new User(rUsername, rEmail);
                            myRef.child("users").child(userID).setValue(userInfo);

                            Intent i = new Intent(Register.this, HomePage.class);
                            startActivity(i);
                            finish();
                            toastMessage("Registration Successful.");
                        }
                    }
                });
            }
        }

            else {
            toastMessage("Fill out all the fields");
        }

        }
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


}
