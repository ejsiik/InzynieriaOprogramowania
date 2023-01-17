package com.example.ktogdziekiedy.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import backendconnection.Task
import com.example.ktogdziekiedy.R
import com.example.ktogdziekiedy.RaportPracownikActivity
import com.example.ktogdziekiedy.RaportSzczegolyActivity
import com.example.ktogdziekiedy.databinding.LayoutRaportSubSubItemBinding
import com.example.ktogdziekiedy.model.Raport
import com.example.ktogdziekiedy.store.ReportStore

class SubSubItemAdapter(private val context: Context, private val tasks: List<Task>) : RecyclerView.Adapter<SubSubItemAdapter.ViewHolder>() {

    //lateinit var onItemClick: (Raport) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubSubItemAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_raport_sub_sub_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubSubItemAdapter.ViewHolder, position: Int) {
        holder.binding.apply {
            subSubItemText.text = tasks[position].endTime
            if (tasks[position].user != null){
                subSubItemText.text = "${tasks[position].endTime} by ${tasks[position].user!!.login}"
            }
            subSubItemText.setOnClickListener {
                ReportStore.task = tasks[position]
                val intent = Intent(context, RaportSzczegolyActivity::class.java)
//                intent.putExtra("title", tasks[position].endTime)
                context.startActivity(intent)
            }
        }

    }

    override fun getItemCount() = tasks.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = LayoutRaportSubSubItemBinding.bind(itemView)
        /*
        init {
            itemView.setOnClickListener {
                onItemClick.invoke(raport[adapterPosition])
            }
        }
        */
    }
}