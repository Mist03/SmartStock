package com.example.smartstock

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WarehouseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_warehouse, container, false)
    }

@SuppressLint("MissingInflatedId")
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val btnCircle = view.findViewById<Button>(R.id.buttonCircle)
    btnCircle.setOnClickListener{
        val intent = Intent(view.context, Circle::class.java)
        startActivity(intent)
    }
    val btnSquare = view.findViewById<Button>(R.id.buttonSquare)
    btnSquare.setOnClickListener{
        val intent = Intent(view.context, Square::class.java)
        startActivity(intent)
    }
    val btnOval = view.findViewById<Button>(R.id.buttonOval)
    btnOval.setOnClickListener{
        val intent = Intent(view.context, Oval::class.java)
        startActivity(intent)
    }
    val btnRectangle = view.findViewById<Button>(R.id.buttonRectangle)
    btnRectangle.setOnClickListener{
        val intent = Intent(view.context, Rectangle::class.java)
        startActivity(intent)
    }

    val view = view.findViewById<RecyclerView>(R.id.recyclerView)
    view?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    val adapter = AdapterProject()
    view?.adapter = adapter

    val ref1 = FirebaseDatabase.getInstance().reference.child("Warehouse")
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