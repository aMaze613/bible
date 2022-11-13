package com.m8.exercicis.bible.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.m8.exercicis.bible.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtUsername: EditText = findViewById(R.id.txtUsername)
        val txtPassword: EditText = findViewById(R.id.txtPassword)
        val btnSignIn: Button = findViewById(R.id.btnSignIn)
        val lblLoginResult: TextView = findViewById(R.id.lblLoginResult)

        btnSignIn.setOnClickListener {
            if (txtUsername.text.toString() == getString(R.string.correct_login) &&
                txtPassword.text.toString() == getString(R.string.correct_login)
            ) {
                Log.i("LoginSucc", getString(R.string.login_succ))
                lblLoginResult.text = getString(R.string.login_succ)
                val intent = Intent(this, BottomNavigationActivity::class.java)
                startActivity(intent)
            } else {
                Log.i("LoginUnsucc", getString(R.string.login_unsucc))
                lblLoginResult.text = getString(R.string.login_unsucc)
            }
        }
    }
}