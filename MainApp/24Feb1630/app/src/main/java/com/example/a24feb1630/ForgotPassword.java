package com.example.a24feb1630;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity {
    private EditText emailAddress;
    private TextView warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.forgot_password);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("1Pair");

            emailAddress = findViewById(R.id.enteremail);
            warning = findViewById(R.id.blank);
    }


    public void resetPassword(View v){
        emailAddress.getText();

        if(TextUtils.isEmpty(emailAddress.toString()))
        {
            warning.setText("Please fill in your email");
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
