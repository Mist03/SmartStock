package com.example.smartstock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class ReceiptFragment : Fragment() {
    lateinit var databaseRef: DatabaseReference
    var databaseReference: DatabaseReference? = null
    var databaseReference01: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    lateinit var count:EditText
    lateinit var date:TextView

    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_receipt, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val spinner1: Spinner = view.findViewById(R.id.spinner1)
        val spinner: Spinner = view.findViewById(R.id.spinner)
        val spinner2: Spinner = view.findViewById(R.id.spinner2)
        val spinner3: Spinner = view.findViewById(R.id.spinner3)
        val spinner4: Spinner = view.findViewById(R.id.spinner4)
        count = view.findViewById(R.id.editTextTextPersonName3)
        date = view.findViewById(R.id.textView10)
        date.setText(currentDate)
        println(currentDate)



    databaseRef = FirebaseDatabase.getInstance().getReference()
    database = FirebaseDatabase.getInstance()
    databaseReference = database?.reference!!.child("Warehouse")
    databaseReference01 = database?.reference!!.child("Warehouse_Accounting_Reception")




    ArrayAdapter.createFromResource(
        view.context,
        R.array.spinner1,
        android.R.layout.simple_spinner_item
    ).also { adapter ->
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
    }
    ArrayAdapter.createFromResource(
        view.context,
        R.array.spinner,
        android.R.layout.simple_spinner_item
    ).also { adapter ->
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
    ArrayAdapter.createFromResource(
        view.context,
        R.array.spinner2,
        android.R.layout.simple_spinner_item
    ).also { adapter ->
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter
    }
    ArrayAdapter.createFromResource(
        view.context,
        R.array.spinner3,
        android.R.layout.simple_spinner_item
    ).also { adapter ->
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = adapter
    }
    ArrayAdapter.createFromResource(
        view.context,
        R.array.spinner4,
        android.R.layout.simple_spinner_item
    ).also { adapter ->
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner4.adapter = adapter
    }

    val btn = view.findViewById<Button>(R.id.button)
    btn.setOnClickListener {
        var count2 = " "
        var etcount = count.text.toString()
        if(etcount == ""){
            etcount = 0.toString()
        }
        val etcount01 = etcount
        val selected1 = spinner1.selectedItem.toString()
        val selected = spinner.selectedItem.toString()
        val selected2 = spinner2.selectedItem.toString()
        val selected3 = spinner3.selectedItem.toString()
        val selected4 = spinner4.selectedItem.toString()
        val time = currentDate.replace("/","_")

        databaseRef = FirebaseDatabase.getInstance().getReference()
        databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Количесво").addListenerForSingleValueEvent(object :
            ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
//                    count2 = ("${snapshot.value}")
                if (snapshot.value == null){
//                    println(snapshot.value)
                    count2 = "0"
//                    println(count2)
                }
                if(snapshot.value != null){count2 = ("${snapshot.value}")}
//                println(etcount)
                etcount = (etcount.toInt() + count2.toInt()).toString()
//                println(etcount)
                if (etcount.isNotBlank() && selected4.isNotBlank() && selected2.isNotBlank() && selected3.isNotBlank()
                    && selected.isNotBlank() && selected1.isNotBlank() && etcount != "0") {
                    databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Количество")
                        .setValue(etcount)
                    databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Количество")
                        .setValue(etcount01)
                    databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Дата")
                        .setValue(time)
                    databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Длина")
                        .setValue(selected4)
                    databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Длина")
                        .setValue(selected4)
                    databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Материал")
                        .setValue(selected2)
                    databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Материал")
                        .setValue(selected2)
                    databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Назначение")
                        .setValue(selected3)
                    databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Назначение")
                        .setValue(selected3)
                    databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Тип производства")
                        .setValue(selected)
                    databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Тип производства")
                        .setValue(selected)
                    databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Форма поперечного сечения")
                        .setValue(selected1)
                    databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Форма поперечного сечения")
                        .setValue(selected1)
                    Toast.makeText(context, "Добавлено", Toast.LENGTH_SHORT).show()
                }else{Toast.makeText(context, "Заполните все поля и количество не должно быть 0", Toast.LENGTH_SHORT).show()}
            }
            override fun onCancelled(error: DatabaseError) {}
        })
//        if (etcount.isNotBlank() && selected4.isNotBlank() && selected2.isNotBlank() && selected3.isNotBlank()
//            && selected.isNotBlank() && selected1.isNotBlank()) {
//            databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Длина")
//                .setValue(selected4)
//            databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Длина")
//                .setValue(selected4)
//            databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Материал")
//                .setValue(selected2)
//            databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Материал")
//                .setValue(selected2)
//            databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Назначение")
//                .setValue(selected3)
//            databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Назначение")
//                .setValue(selected3)
//            databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Тип производства")
//                .setValue(selected)
//            databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Тип производства")
//                .setValue(selected)
//            databaseRef.child("Warehouse/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/Форма поперечного сечения")
//                .setValue(selected1)
//            databaseRef.child("Warehouse_Accounting_Reception/${selected1}/${selected}/${selected2}/${selected3}/${selected4}/${time}/Форма поперечного сечения")
//                .setValue(selected1)
//            Toast.makeText(context, "Добавлено", Toast.LENGTH_SHORT).show()
//        }else{Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()}

        spinner1.setSelection(0)
        spinner.setSelection(0)
        spinner2.setSelection(0)
        spinner3.setSelection(0)
        spinner4.setSelection(0)
        count.setText("")
    }
}
}


