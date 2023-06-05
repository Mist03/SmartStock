package com.example.smartstock

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class AccountingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accounting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val Reception = view.findViewById<Button>(R.id.Reception)
        Reception.setOnClickListener {
            val intent = Intent(view.context, WarehouseAccoutingReception::class.java)
            startActivity(intent)
        }
        val Cancellation = view.findViewById<Button>(R.id.Cancellation)
        Cancellation.setOnClickListener {
            val intent = Intent(view.context, WarehouseAccoutingCancellation::class.java)
            startActivity(intent)
        }
    }
}