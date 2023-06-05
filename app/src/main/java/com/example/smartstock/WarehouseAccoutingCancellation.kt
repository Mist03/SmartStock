package com.example.smartstock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WarehouseAccoutingCancellation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warehouse_accouting_cancellation)

        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            onBackPressed()
            finish()
        }

        val view = findViewById<RecyclerView>(R.id.recyclerView2)
        view?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = AdapterWarehouse()
        view?.adapter = adapter

        val ref1 = FirebaseDatabase.getInstance().reference.child("Warehouse_Accounting_Cancellation")
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
                    val Datelist: List<String> = datalisting.split(",").map { it -> it.trim()}
                    val datalist1 = Datelist.chunked(7)
                    val datalist2 = datalist1.size
                    for(i in 0..datalist2-1){
                        listText1.add(datalist1[i][0].replace("Дата=", "").replace("-",".")
                            .replace("{", " ").replace("}", " ")
                            .split(" ").last().toString())
                        listText2.add(datalist1[i][1].replace("Длина=", ""))
                        listText3.add(datalist1[i][2].replace("Количество=", ""))
                        listText4.add(datalist1[i][3].replace("Назначение=", ""))
                        listText5.add(datalist1[i][4].replace("Тип производства=", ""))
                        listText6.add(datalist1[i][5].replace("Материал=", ""))
                        listText7.add(datalist1[i][6].replace("Форма поперечного сечения=", "")
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