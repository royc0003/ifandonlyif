package com.example.a24feb1630;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpPage extends AppCompatActivity {
    private EditText emailAddress;
    private EditText password;
    private EditText username;
    private TextView warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("1Pair");

        emailAddress = findViewById(R.id.enteremail);
        password = findViewById(R.id.enterpassword);
        username = findViewById(R.id.enterpassword);
        warning = findViewById(R.id.blank);
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
}
