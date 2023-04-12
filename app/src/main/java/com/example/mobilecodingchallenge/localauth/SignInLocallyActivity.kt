package com.example.mobilecodingchallenge.localauth

import com.example.mobilecodingchallenge.images.ImagesViewer
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilecodingchallenge.databinding.ActivitySignInLocallyBinding


class SignInLocallyActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySignInLocallyBinding
    private lateinit var sharedPreferencesManager: SharedPreferencesManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInLocallyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferencesManager = SharedPreferencesManager(this)
        supportActionBar?.apply {
            // set the color of the action bar to red
            setBackgroundDrawable(ColorDrawable(Color.BLACK))
        }


        binding.signUp.setOnClickListener {
            val intent = Intent(this, SignUpLocallyActivity::class.java)
            startActivity(intent)
        }
        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()

            if (email!= "" && pass!="" ) {
                if (sharedPreferencesManager.getUser(email, pass)) {
                    val intent = Intent(this, ImagesViewer::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}