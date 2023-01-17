package com.example.ktogdziekiedy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import backendconnection.Task
import com.example.ktogdziekiedy.R
import com.example.ktogdziekiedy.model.Raport
import kotlinx.android.synthetic.main.layout_raport_item.view.*
import kotlinx.coroutines.NonDisposableHandle.parent

class DanegoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Task> = ArrayList()
    lateinit var onItemClick: (Task) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RaportyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_raport_item, parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is RaportyViewHolder -> {
                holder.bind(items.get(position))
            }
        }

        holder.itemView.setOnClickListener {
            onItemClick.invoke(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(raportyList: List<Task>) {
        items = raportyList
    }

    inner class RaportyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.raport_title

        fun bind(raport: Task) {
            name.setText(raport.name)
        }
    }
}