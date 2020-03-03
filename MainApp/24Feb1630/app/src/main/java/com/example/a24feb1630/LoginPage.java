package com.example.a24feb1630;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

public class LoginPage extends AppCompatActivity {

    private Button loginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        mAuth = FirebaseAuth.getInstance();
        loginButton = (Button) findViewById(R.id.loginbutton);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText emailInput = (EditText) findViewById(R.id.username);
                EditText passwordInput = (EditText) findViewById(R.id.password);

                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    openMainPage();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                   Toast.makeText(LoginPage.this, "Authentication failed.",
                                     Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
                //if email and password matches database, go to main page

                //else print on screen wrong email or password
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void sign_up(View v)
    {
        startActivity(new Intent(this, SignUpPage.class));
    }

    public void openMainPage(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}
