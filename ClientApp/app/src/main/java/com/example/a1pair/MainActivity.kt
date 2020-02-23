package com.example.a1pair

import kotlinx.android.synthetic.main.activity_main.*


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private var visiblePW = false
    private lateinit var auth: FirebaseAuth
    private lateinit var loginButton: Button
    private lateinit var emailField: TextView
    private lateinit var passwordField: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SeeHidePW.setOnClickListener{
            visiblePW = !visiblePW
            showPassword(visiblePW)
        }

        showPassword(visiblePW)
        auth = FirebaseAuth.getInstance()
        emailField = findViewById(R.id.username)
        passwordField = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginbutton)

        // Set onClick listener on loginButton to fire loginRequest when clicked
        loginButton.setOnClickListener{
            var email: String = emailField.text.toString()
            var password: String = passwordField.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val currentUser = auth.currentUser
                        Toast.makeText(baseContext, "Welcome ${currentUser?.email}!",
                            Toast.LENGTH_SHORT).show()

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }

    private fun showPassword(show:Boolean){
        if(show){
            password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            SeeHidePW.setImageResource(R.drawable.see_pw)
        }
        else{
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            SeeHidePW.setImageResource(R.drawable.hide_pw)
        }
        password.setSelection(password.text.toString().length)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //val currentUser = auth.currentUser
        // Update UI accordingly
    }
}
