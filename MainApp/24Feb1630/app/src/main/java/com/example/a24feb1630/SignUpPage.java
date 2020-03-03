package com.example.a24feb1630;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPage extends AppCompatActivity {
    private EditText emailAddress;
    private EditText password;
    private EditText username;
    private TextView warning;
    private FirebaseAuth mAuth;
    private Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);


        mAuth = FirebaseAuth.getInstance();
        signUpButton = (Button) findViewById(R.id.signup);

        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText emailInput = (EditText) findViewById(R.id.enteremail);
                EditText fullnameInput = (EditText) findViewById(R.id.enterusername);
                EditText passwordInput = (EditText) findViewById(R.id.enterpassword);

                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    openMainPage();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpPage.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    // updateUI(null);
                                }
                                // If the new account was created, the user is also signed in. In the callback, you can use the getCurrentUser method to get the user's account data.
                            }
                        });

                //if email and password matches database, go to main page

                //else print on screen wrong email or password
            }
        });
    }

    public void createAccount(View v){
        emailAddress.getText();
        password.getText();
        username.getText();

        if(TextUtils.isEmpty(emailAddress.toString()) || TextUtils.isEmpty(password.toString()))
        {
            warning.setText("Please fill in all fields.");
        }
        else
        {
            warning.setText("");
        }

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if(imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void openMainPage(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}
