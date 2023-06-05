package com.example.smartstock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdapterProject: RecyclerView.Adapter<AdapterProject.ViewHolder>() {


    val list1 = mutableListOf<String>()
    val list2 = mutableListOf<String>()
    val list3 = mutableListOf<String>()
    val list4 = mutableListOf<String>()
    val list5 = mutableListOf<String>()
    val list6 = mutableListOf<String>()

    fun setData1(list1: List<String>) {
        this.list1.clear()
        this.list1.addAll(list1)
        notifyDataSetChanged()
    }
    fun setData2(list2: List<String>) {
        this.list2.clear()
        this.list2.addAll(list2)
        notifyDataSetChanged()
    }
    fun setData3(list3: List<String>) {
        this.list3.clear()
        this.list3.addAll(list3)
        notifyDataSetChanged()
    }
    fun setData4(list4: List<String>) {
        this.list4.clear()
        this.list4.addAll(list4)
        notifyDataSetChanged()
    }
    fun setData5(list5: List<String>) {
        this.list5.clear()
        this.list5.addAll(list5)
        notifyDataSetChanged()
    }
    fun setData6(list6: List<String>) {
        this.list6.clear()
        this.list6.addAll(list6)
        notifyDataSetChanged()
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        lateinit var textText1: TextView
        lateinit var textText2: TextView
        lateinit var textText3: TextView
        lateinit var textText4: TextView
        lateinit var textText5: TextView
        lateinit var textText6: TextView



        init {

            textText1 = itemView.findViewById(R.id.textView23)
            textText2 = itemView.findViewById(R.id.textView24)
            textText3 = itemView.findViewById(R.id.textView25)
            textText4 = itemView.findViewById(R.id.textView26)
            textText5 = itemView.findViewById(R.id.textView27)
            textText6 = itemView.findViewById(R.id.textView28)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textText1.text = ("Длина: " + list1[position])
            holder.textText2.text = ("Количество: " + list2[position])
            holder.textText3.text = ("Назначение: " + list3[position])
            holder.textText4.text = ("Тип: " + list4[position])
            holder.textText5.text = ("Материал: " + list5[position])
            holder.textText6.text = ("Форма: " + list6[position])

    }
    override fun getItemCount(): Int {
        return this.list1.count()

    }
}