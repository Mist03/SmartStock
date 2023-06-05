package com.example.smartstock

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragment : Fragment() {
    lateinit var databaseRef: DatabaseReference
    lateinit var fname: TextView
    lateinit var lname: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val currentUser = FirebaseAuth.getInstance().getCurrentUser()?.getUid()
    fname = view.findViewById(R.id.textView7)
    lname = view.findViewById(R.id.textView8)

    databaseRef = FirebaseDatabase.getInstance().getReference()
    databaseRef.child("Profile/${currentUser}/firstname").addValueEventListener(object :
        ValueEventListener
    {
        override fun onDataChange(snapshot: DataSnapshot) {
            fname.text = ("${snapshot.value}")
        }
        override fun onCancelled(error: DatabaseError) {}
    })
    databaseRef.child("Profile/${currentUser}/lastname").addValueEventListener(object :
        ValueEventListener
    {
        override fun onDataChange(snapshot: DataSnapshot) {
            lname.text = ("${snapshot.value}")
        }
        override fun onCancelled(error: DatabaseError) {}
    })
    val btn = view.findViewById<Button>(R.id.buttonBack)
    btn.setOnClickListener {
        val intent = Intent(view.context, Prolog2::class.java)
        intent.putExtra("Exit", "1")
        startActivity(intent)
    }
}
}