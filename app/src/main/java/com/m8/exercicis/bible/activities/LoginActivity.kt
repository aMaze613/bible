package com.m8.exercicis.bible.activities

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
        setContentView(R.layout.activity_login)

        val txtUsername: EditText = findViewById(R.id.txtUsername)
        val txtPassword: EditText = findViewById(R.id.txtPassword)
        val btnSignIn: Button = findViewById(R.id.btnSignIn)

        /*
         * Username and password must be "admin"-"admin" to login successfully, and after clicking
         * the button, both fields are cleared, and a toast is shown indicating if it was
         * successful.
         */
        btnSignIn.setOnClickListener {
            if (txtUsername.text.toString() == getString(R.string.correct_login) &&
                txtPassword.text.toString() == getString(R.string.correct_login)
            ) {
                Toast.makeText(this, getString(R.string.login_succ), Toast.LENGTH_SHORT).show()
                txtUsername.text.clear()
                txtPassword.text.clear()
                val intent = Intent(this, BottomNavigationActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.login_unsucc), Toast.LENGTH_SHORT).show()
                txtUsername.text.clear()
                txtPassword.text.clear()
            }
        }
    }

}
