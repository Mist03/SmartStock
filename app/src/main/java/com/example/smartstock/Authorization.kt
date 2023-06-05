package com.example.smartstock

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Authorization : AppCompatActivity() {
    lateinit var Email: EditText
    private lateinit var Pass: EditText
    lateinit var sp: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        Email = findViewById(R.id.editTextTextEmailAddress2)
        Pass = findViewById(R.id.editTextTextPassword3)
        auth = FirebaseAuth.getInstance()

        val btn = findViewById<Button>(R.id.buttonAuthorization)
        btn.setOnClickListener {
            login()
        }
    }
    private fun login() {
        val email = Email.text.toString()
        val pass = Pass.text.toString()
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Поля не должны быть пустыми", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Вход успешный", Toast.LENGTH_SHORT).show()

                sp = getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE)
                editor = sp.edit()

                editor.putBoolean("is_logged", true).commit()

                val handler = Handler()
                handler.postDelayed({ start() }, 1000)
            } else
                Toast.makeText(this, "Ошибка входа", Toast.LENGTH_SHORT).show()
        }

    }
    fun start() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}