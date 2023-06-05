package com.example.smartstock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference

class AdapterWarehouse:RecyclerView.Adapter<AdapterWarehouse.ViewHolder>() {

    data class Data(val value1: String, val value2: String, val value3: String, val value4: String,
                    val value5: String, val value6: String, val value7: String)
    val list1 = mutableListOf<String>()
    val list2 = mutableListOf<String>()
    val list3 = mutableListOf<String>()
    val list4 = mutableListOf<String>()
    val list5 = mutableListOf<String>()
    val list6 = mutableListOf<String>()
    val list7 = mutableListOf<String>()
    private val allData = mutableListOf<Data>()
    private var isFiltered = false

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
    fun setData7(list7: List<String>) {
        this.list7.clear()
        this.list7.addAll(list7)
        notifyDataSetChanged()
    }
    fun sortLists() {
        val dataList = mutableListOf<Data>()
        for (i in list1.indices) {
            dataList.add(Data(list1[i], list2[i], list3[i], list4[i], list5[i], list6[i], list7[i]))
        }
        dataList.sortByDescending { it.value1 }
        for (i in dataList.indices) {
            list1[i] = dataList[i].value1
            list2[i] = dataList[i].value2
            list3[i] = dataList[i].value3
            list4[i] = dataList[i].value4
            list5[i] = dataList[i].value5
            list6[i] = dataList[i].value6
            list7[i] = dataList[i].value7
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        lateinit var textText1: TextView
        lateinit var textText2: TextView
        lateinit var textText3: TextView
        lateinit var textText4: TextView
        lateinit var textText5: TextView
        lateinit var textText6: TextView
        lateinit var textText7: TextView



        init {

            textText1 = itemView.findViewById(R.id.textView23)
            textText2 = itemView.findViewById(R.id.textView24)
            textText3 = itemView.findViewById(R.id.textView25)
            textText4 = itemView.findViewById(R.id.textView26)
            textText5 = itemView.findViewById(R.id.textView27)
            textText6 = itemView.findViewById(R.id.textView28)
            textText7 = itemView.findViewById(R.id.textView13)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_item_2, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textText1.text = ("Дата: "+list1[position].replace("-","."))
        holder.textText2.text = ("Длина: "+list2[position])
        holder.textText3.text = ("Количество: "+list3[position])
        holder.textText4.text = ("Назначение: "+list4[position])
        holder.textText5.text = ("Тип: "+list5[position])
        holder.textText6.text = ("Материал: "+list6[position])
        holder.textText7.text = ("Форма: "+list7[position])

    }
    override fun getItemCount(): Int {
        return if (isFiltered) allData.size else list1.size
    }
}