package com.example.mobilecodingchallenge.localauth

import android.content.Context

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)

    fun saveUser(email: String, pass: String) {
        val editor = sharedPreferences.edit()
        editor.putString("USER_EMAIL", email)
        editor.putString("USER_PASSWORD", pass)
        editor.apply()
    }

    fun getUser(email: String, pass: String): Boolean  {
        val SPEmail = sharedPreferences.getString("USER_EMAIL", null)
        val SPPassword = sharedPreferences.getString("USER_PASSWORD", null)
        if (SPEmail != null && SPPassword != null) {
            if(email == SPEmail && pass == SPPassword) {
                return true
            }
        }
        return false
    }
}
data class User(val email: String, val password: String)
