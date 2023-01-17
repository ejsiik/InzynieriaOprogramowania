package com.example.ktogdziekiedy.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import backendconnection.BackendClient
import com.example.ktogdziekiedy.ItemsViewModel
import com.example.ktogdziekiedy.PanelActivity
import com.example.ktogdziekiedy.R
import com.example.ktogdziekiedy.model.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemsAdapter(
    private val context: Context,
    private var items: List<Item>,
    private val categoryName: String
) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemValue = items[position].content
        if (itemValue != null) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener {
                Toast.makeText(context, "Start " + itemValue + " task", Toast.LENGTH_LONG).show()
                val intent = Intent(context, PanelActivity::class.java)
                GlobalScope.launch {
                    BackendClient.addTask(categoryName, itemValue)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(item: Item) {
            view.findViewById<TextView>(R.id.textViewContent).text = item.content
        }
    }
}
