package com.example.ktogdziekiedy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktogdziekiedy.R
import com.example.ktogdziekiedy.databinding.LayoutRaportMainItemBinding
import com.example.ktogdziekiedy.model.MainModel

class MainItemAdapter (
    private val context: Context,
    private val collections: List<MainModel>
    ): RecyclerView.Adapter<MainItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_raport_main_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {
            val collection = collections[position]
            val subItemAdapter = SubItemAdapter(context, collection.subItemModel, collection.mainCategory)
            mainItemText.text = collection.mainCategory
            subItemRv.adapter = subItemAdapter

            mainItemText.setOnClickListener {
                subItemRv.visibility = if (subItemRv.isShown) View.GONE else View.VISIBLE

            }
        }
    }

    override fun getItemCount() = collections.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = LayoutRaportMainItemBinding.bind(itemView)
    }
}