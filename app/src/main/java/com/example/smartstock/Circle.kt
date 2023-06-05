package com.example.smartstock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Circle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle)

        val btn = findViewById<Button>(R.id.button4)
        btn.setOnClickListener{
            onBackPressed()
            finish()
        }

        val view = findViewById<RecyclerView>(R.id.recyclerView)
        view?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = AdapterProject()
        view?.adapter = adapter

        val ref1 = FirebaseDatabase.getInstance().reference.child("Warehouse/Круглая")
        ref1.addListenerForSingleValueEvent(object :   //addListenerForSingleValueEvent//addValueEventListener
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listText1.clear()
                listText2.clear()
                listText3.clear()
                listText4.clear()
                listText5.clear()
                listText6.clear()
                println(dataSnapshot)
                val a = dataSnapshot.children
                for (dsp in a) {
                    println(dsp)
                    val Date = (java.lang.String.valueOf(dsp.getValue()))
                    val Datelist: List<String> = Date.split(",").map { it -> it.trim()}
                    val datalist1 = Datelist.chunked(6)
                    println(Datelist)
                    val datalist2 = datalist1.size
                    for(i in 0..datalist2-1){
                        listText1.add(datalist1[i][0].replace("Длина=", "").replace("{", " ")
                            .replace("}", " ").split(" ").last().toString())
                        listText2.add(datalist1[i][1].replace("Количество=", ""))
                        listText3.add(datalist1[i][2].replace("Назначение=", ""))
                        listText4.add(datalist1[i][3].replace("Тип производства=", ""))
                        listText5.add(datalist1[i][4].replace("Материал=", ""))
                        listText6.add(datalist1[i][5].replace("Форма поперечного сечения=", "")
                            .replace("}", ""))
                    }
                }
                adapter.setData1(listText1)
                adapter.setData2(listText2)
                adapter.setData3(listText3)
                adapter.setData4(listText4)
                adapter.setData5(listText5)
                adapter.setData6(listText6)
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

}