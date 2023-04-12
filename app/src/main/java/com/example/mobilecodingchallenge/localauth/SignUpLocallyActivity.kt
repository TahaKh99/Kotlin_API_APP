package com.example.mobilecodingchallenge.localauth

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilecodingchallenge.databinding.ActivitySignUpLocallyBinding


class SignUpLocallyActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySignUpLocallyBinding
    private lateinit var sharedPreferencesManager: SharedPreferencesManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpLocallyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferencesManager = SharedPreferencesManager(this)
        supportActionBar?.apply {
            // set the color of the action bar to red
            setBackgroundDrawable(ColorDrawable(Color.BLACK))
        }


        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInLocallyActivity::class.java)
            startActivity(intent)
        }
        binding.signUpButton.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val confirmPass = binding.confirmPassword.text.toString()

            if (pass == confirmPass) {
                sharedPreferencesManager.saveUser(email, pass)
                val intent = Intent(this, SignInLocallyActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}