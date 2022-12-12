package com.m8.exercicis.bible.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.m8.exercicis.bible.R


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        /*
         * The Shared Preferences gets checked before loading anything from the Login Activity, and
         * if the user was logged before, it skips directly to the Bottom Navigation Activity.
         */
        if (getSharedPreferences("BIBLE_APP_CONFIGURATION", Context.MODE_PRIVATE)
                .getBoolean("logged", false)) {
            val intent = Intent(this, BottomNavigationActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        setContentView(R.layout.activity_login)

        val txtUsername: EditText = findViewById(R.id.txtUsername)
        val txtPassword: EditText = findViewById(R.id.txtPassword)
        val btnSignIn: Button = findViewById(R.id.btnSignIn)

        /*
         * Username and password must be "admin"-"admin" to login successfully, and after clicking
         * the button, both fields are cleared, and a toast is shown indicating if it was
         * successful. If logged, that will be saved so next time user doesn't have to log in again.
         */
        btnSignIn.setOnClickListener {
            if (txtUsername.text.toString() == getString(R.string.correct_login) &&
                txtPassword.text.toString() == getString(R.string.correct_login)
            ) {
                Toast.makeText(this, getString(R.string.login_succ), Toast.LENGTH_SHORT).show()
                txtUsername.text.clear()
                txtPassword.text.clear()
                getSharedPreferences("BIBLE_APP_CONFIGURATION", Context.MODE_PRIVATE)
                    .edit().putBoolean("logged", true).apply()
                val intent = Intent(this, BottomNavigationActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.login_unsucc), Toast.LENGTH_SHORT).show()
                txtUsername.text.clear()
                txtPassword.text.clear()
            }
        }
    }

}
