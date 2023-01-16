package com.example.ktogdziekiedy.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ktogdziekiedy.*
import com.example.ktogdziekiedy.model.Item

class ItemsAdapter2(
    private val context: Context,
    private var items: List<Item>
) :
    RecyclerView.Adapter<ItemsAdapter2.Item2ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item2ViewHolder {
        return Item2ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Item2ViewHolder, position: Int) {
        val itemValue = items[position].content
        //val viewModel = ItemsViewModel(itemValue)
        //val bundle = Bundle()
        //bundle.putString("name", itemValue)
        if (itemValue != null) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener {
                //Toast.makeText(context, "Start " + itemValue + " task", Toast.LENGTH_LONG).show()
                val intent = Intent(context, RaportSzczegolyActivity::class.java)
                //intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class Item2ViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(item: Item) {
            view.findViewById<TextView>(R.id.textViewContent).text = item.content
        }
    }
}
