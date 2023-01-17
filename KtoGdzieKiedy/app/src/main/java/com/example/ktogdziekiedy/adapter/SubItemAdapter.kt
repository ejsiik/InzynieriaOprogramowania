package com.example.ktogdziekiedy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktogdziekiedy.R
import com.example.ktogdziekiedy.databinding.LayoutRaportSubItemBinding
import com.example.ktogdziekiedy.model.SubModel

class SubItemAdapter(private val context: Context, private val subItemModel: List<SubModel>) : RecyclerView.Adapter<SubItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_raport_sub_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val subItemM = subItemModel[position]
            subItemText.text = subItemM.subCategory
            val subSubItemAdapter = SubSubItemAdapter(context, subItemM.tasks)
            subSubItemRv.adapter = subSubItemAdapter

            subItemText.setOnClickListener {
                subSubItemRv.visibility = if (subSubItemRv.isShown) View.GONE else View.VISIBLE
            }
        }
    }

    override fun getItemCount() = subItemModel.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = LayoutRaportSubItemBinding.bind(itemView)
    }
}