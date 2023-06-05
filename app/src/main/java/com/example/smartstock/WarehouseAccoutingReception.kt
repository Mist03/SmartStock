package com.example.smartstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDate

class WarehouseAccoutingReception : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warehouse_accouting_reception)

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            onBackPressed()
            finish()
        }

        val view = findViewById<RecyclerView>(R.id.recyclerView1)
        view?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = AdapterWarehouse()
        view?.adapter = adapter

        val ref1 = FirebaseDatabase.getInstance().reference.child("Warehouse_Accounting_Reception")
        ref1.addListenerForSingleValueEvent(object :   //addListenerForSingleValueEvent//addValueEventListener
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listText1.clear()
                listText2.clear()
                listText3.clear()
                listText4.clear()
                listText5.clear()
                listText6.clear()
                listText7.clear()
                val a = dataSnapshot.children
                for (dsp in a) {
                    val datalisting = (java.lang.String.valueOf(dsp.getValue()))
                    val Datelist: List<String> = datalisting.split(",").map { it -> it.trim() }
                    val datalist1 = Datelist.chunked(7)
                    val datalist2 = datalist1.size
                    println(datalist1)
                    val sortedList = datalist1.sortedBy {
                        it[0].replace("Дата=", "").replace("-",".")
                            .replace("{", " ").replace("}", " ")
                            .split(" ").last().toString()
                    }
                    for (i in 0..datalist2 - 1) {
                        listText1.add(sortedList[i][0].replace("Дата=", "")
                            .replace("{", " ").replace("}", " ")
                            .split(" ").last().toString())
                        listText2.add(sortedList[i][1].replace("Длина=", ""))
                        listText3.add(sortedList[i][2].replace("Количество=", ""))
                        listText4.add(sortedList[i][3].replace("Назначение=", ""))
                        listText5.add(sortedList[i][4].replace("Тип производства=", ""))
                        listText6.add(sortedList[i][5].replace("Материал=", ""))
                        listText7.add(sortedList[i][6].replace("Форма поперечного сечения=", "")
                            .replace("}", ""))
                    }
                }
                adapter.setData1(listText1)
                adapter.setData2(listText2)
                adapter.setData3(listText3)
                adapter.setData4(listText4)
                adapter.setData5(listText5)
                adapter.setData6(listText6)
                adapter.setData7(listText7)
                adapter.sortLists()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}