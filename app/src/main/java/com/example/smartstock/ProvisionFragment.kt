package com.example.smartstock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class ProvisionFragment : Fragment() {
    lateinit var databaseRef: DatabaseReference
    var databaseReference: DatabaseReference? = null
    var databaseReference02: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    lateinit var count: EditText
    lateinit var date:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_provision, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val spinner01: Spinner = view.findViewById(R.id.spinner01)
        val spinner02: Spinner = view.findViewById(R.id.spinner02)
        val spinner03: Spinner = view.findViewById(R.id.spinner03)
        val spinner04: Spinner = view.findViewById(R.id.spinner04)
        val spinner05: Spinner = view.findViewById(R.id.spinner05)
        count = view.findViewById(R.id.editTextTextPersonName3)
        date = view.findViewById(R.id.textView14)
        date.setText(currentDate)
        var D = 0

        databaseRef = FirebaseDatabase.getInstance().getReference()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Warehouse")
        databaseReference02 = database?.reference!!.child("Warehouse_Accounting_Cancellation")

    ArrayAdapter.createFromResource(
        view.context,
        R.array.spinner01,
        android.R.layout.simple_spinner_item
    ).also { adapter ->
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner01.adapter = adapter
    }
    ArrayAdapter.createFromResource(
        view.context,
        R.array.spinner02,
        android.R.layout.simple_spinner_item
    ).also { adapter ->
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner02.adapter = adapter
    }
    ArrayAdapter.createFromResource(
        view.context,
        R.array.spinner03,
        android.R.layout.simple_spinner_item
    ).also { adapter ->
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner03.adapter = adapter
    }
    ArrayAdapter.createFromResource(
        view.context,
        R.array.spinner04,
        android.R.layout.simple_spinner_item
    ).also { adapter ->
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner04.adapter = adapter
    }
    ArrayAdapter.createFromResource(
        view.context,
        R.array.spinner05,
        android.R.layout.simple_spinner_item
    ).also { adapter ->
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner05.adapter = adapter
    }

    val btn = view.findViewById<Button>(R.id.button)
    btn.setOnClickListener {
        var count2 = " "
        var etcount = count.text.toString()
        if(etcount == ""){
            etcount = 0.toString()
        }
        var etcount01 = etcount
        val selected1 = spinner01.selectedItem.toString()
        val selected2 = spinner02.selectedItem.toString()
        val selected3 = spinner03.selectedItem.toString()
        val selected4 = spinner04.selectedItem.toString()
        val selected5 = spinner05.selectedItem.toString()
        val time = currentDate.replace("/","_")

        databaseRef = FirebaseDatabase.getInstance().getReference()
        databaseRef.child("Warehouse/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/Количество")
            .addListenerForSingleValueEvent(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value == null){
                    count2 = "0"
                    count.setText("")
                    Toast.makeText(context, "На складе нет такого колличества!", Toast.LENGTH_SHORT).show()
                    D = 0

                }
                if(snapshot.value != null && etcount != "0"){
                    count2 = ("${snapshot.value}")
                    if(count2.toInt() - etcount.toInt() >= 0){
                        etcount = (count2.toInt() - etcount.toInt()).toString()
                        count.setText("")
                        Toast.makeText(context, "Списано. Осталось ${etcount} штук", Toast.LENGTH_SHORT).show()
                        D = 1
                        if (etcount.isNotBlank()) {
                            databaseRef.child("Warehouse/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/Количество")
                                .setValue(etcount)
                        }
                    }else{
                        count.setText("")
                        Toast.makeText(context, "На складе нет такого колличества!", Toast.LENGTH_SHORT).show()
                        D = 0
                    }
                }else{
                    Toast.makeText(context, "Нельзя списать 0", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Количество")
            .addListenerForSingleValueEvent(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                println(D)
                if (snapshot.value == null && D == 1){
                    count2 = "0"
                    etcount01 = (etcount01.toInt() + count2.toInt()).toString()
                    if (etcount.isNotBlank() && selected5.isNotBlank() && selected3.isNotBlank() &&
                        selected4.isNotBlank() && selected2.isNotBlank() && selected1.isNotBlank() && etcount != "0") {
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Дата")
                            .setValue(time)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Количество")
                            .setValue(etcount01)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Длина")
                            .setValue(selected5)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Материал")
                            .setValue(selected3)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Назначение")
                            .setValue(selected4)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Тип производства")
                            .setValue(selected2)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Форма поперечного сечения")
                            .setValue(selected1)
                    }else{Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()}
                }
                if(snapshot.value != null && D == 1){
                    count2 = ("${snapshot.value}")
                    etcount01 = (etcount01.toInt() + count2.toInt()).toString()
                    if (etcount.isNotBlank() && selected5.isNotBlank() && selected3.isNotBlank() &&
                        selected4.isNotBlank() && selected2.isNotBlank() && selected1.isNotBlank() && etcount != "0") {
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Дата")
                            .setValue(time)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Количество")
                            .setValue(etcount01)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Длина")
                            .setValue(selected5)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Материал")
                            .setValue(selected3)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Назначение")
                            .setValue(selected4)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Тип производства")
                            .setValue(selected2)
                        databaseRef.child("Warehouse_Accounting_Cancellation/${selected1}/${selected2}/${selected3}/${selected4}/${selected5}/${time}/Форма поперечного сечения")
                            .setValue(selected1)
                    }else{Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()}
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })

    }
}
}