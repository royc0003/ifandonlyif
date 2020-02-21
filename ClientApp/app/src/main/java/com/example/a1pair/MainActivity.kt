package com.example.a1pair

import kotlinx.android.synthetic.main.activity_main.*


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod

class MainActivity : AppCompatActivity() {
    private var visiblePW = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SeeHidePW.setOnClickListener{
            visiblePW = !visiblePW
            showPassword(visiblePW)
        }

        showPassword(visiblePW)
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
}
