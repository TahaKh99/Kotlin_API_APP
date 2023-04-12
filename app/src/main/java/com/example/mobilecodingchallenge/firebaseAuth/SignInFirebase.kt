package com.example.mobilecodingchallenge.firebaseAuth

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilecodingchallenge.*
import com.example.mobilecodingchallenge.databinding.ActivitySignInBinding
import com.example.mobilecodingchallenge.images.ImagesViewer

import com.google.firebase.auth.FirebaseAuth

class SignInFirebase : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(Color.BLACK))
        }


        firebaseAuth = FirebaseAuth.getInstance()
        binding.signUp.setOnClickListener {
            val intent = Intent(this, SignUpFirebase::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()

            if (isEmailValid(email) && isPasswordValid(pass)) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
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

    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, ImagesViewer::class.java)
            startActivity(intent)
        }
    }


}