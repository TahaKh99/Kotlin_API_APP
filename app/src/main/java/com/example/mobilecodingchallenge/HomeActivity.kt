package com.example.mobilecodingchallenge

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mobilecodingchallenge.firebaseAuth.SignInFirebase
import com.example.mobilecodingchallenge.localauth.SignInLocallyActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.apply {
            // set the color of the action bar to red
            setBackgroundDrawable(ColorDrawable(Color.BLACK))
        }

        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun signInWithFirebase(view: View) {
        val intent = Intent(this, SignInFirebase::class.java)
        startActivity(intent)
    }

    fun signInLocally(view: View) {
        val intent = Intent(this, SignInLocallyActivity::class.java)
        startActivity(intent)
    }
}
