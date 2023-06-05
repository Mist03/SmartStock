package com.example.smartstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Registration : AppCompatActivity() {
    lateinit var Email: EditText
    lateinit var ConfPass: EditText
    private lateinit var Pass: EditText
    lateinit var firstname: EditText
    lateinit var lastname: EditText

    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        firstname = findViewById(R.id.editTextTextPersonName)
        lastname = findViewById(R.id.editTextTextPersonName2)
        Email = findViewById(R.id.editTextTextEmailAddress)
        ConfPass = findViewById(R.id.editTextTextPassword2)
        Pass = findViewById(R.id.editTextTextPassword)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Profile")


        val btn = findViewById<Button>(R.id.buttonRegistration)
        btn.setOnClickListener {
            signUpUser()
        }

    }
    private fun signUpUser() {
        val firstName = firstname.text.toString()
        val lastName = lastname.text.toString()
        val email = Email.text.toString()
        val pass = Pass.text.toString()
        val confirmPassword = ConfPass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank() || firstName.isBlank() || lastName.isBlank()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }
        if (pass != confirmPassword) {
            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT)
                .show()
            return
        }
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener() {
            if (it.isSuccessful) {

                val currentUser = auth.currentUser
                val currentUSerDB = databaseReference?.child(currentUser?.uid!!)
                currentUSerDB?.child("email")?.setValue(email)
                currentUSerDB?.child("password")?.setValue(pass)
                currentUSerDB?.child("firstname")?.setValue(firstname.text.toString())
                currentUSerDB?.child("lastname")?.setValue(lastname.text.toString())
                Toast.makeText(this, "Успешная регистрация", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Authorization::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Ошибка регистрации!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}