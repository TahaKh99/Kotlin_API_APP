package com.example.mobilecodingchallenge.firebaseAuth

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilecodingchallenge.databinding.ActivitySignUpBinding
import com.example.mobilecodingchallenge.isEmailValid
import com.example.mobilecodingchallenge.isPasswordValid
import com.google.firebase.auth.FirebaseAuth


class SignUpFirebase : AppCompatActivity(){

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            // set the color of the action bar to red
            setBackgroundDrawable(ColorDrawable(Color.BLACK))
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInFirebase::class.java)
            startActivity(intent)
        }
        binding.signUpButton.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val confirmPass = binding.confirmPassword.text.toString()

//            if (isEmailValid(email) && isPasswordValid(pass) && pass == confirmPass) {
                if (email !="" && pass!= "" && pass == confirmPass) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, SignInFirebase::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}