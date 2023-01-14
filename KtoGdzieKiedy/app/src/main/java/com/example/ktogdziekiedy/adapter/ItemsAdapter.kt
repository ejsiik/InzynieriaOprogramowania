package com.example.ktogdziekiedy.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ktogdziekiedy.*
import com.example.ktogdziekiedy.model.Item

class ItemsAdapter(
    private val context: Context,
    private var items: List<Item>
) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemValue = items[position].content!! //force to be non-null
        val viewModel = ItemsViewModel(itemValue)
        if (itemValue != null) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener {
                Toast.makeText(context, "Start " + itemValue + " task", Toast.LENGTH_LONG).show()
                val intent = Intent(context, PanelActivity::class.java)
                intent.putExtra(
                    "itemViewModel",
                    viewModel
                ) //pass the viewModel object to the next activity
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
