package com.example.ktogdziekiedy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import backendconnection.BackendClient
import backendconnection.Task
import com.example.ktogdziekiedy.ItemsViewModel
import com.example.ktogdziekiedy.R
import com.example.ktogdziekiedy.SecondActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RunningTasksAdapter (private var mList: List<Task>, private val activity: SecondActivity): RecyclerView.Adapter<RunningTasksAdapter.ViewHolder>() {
    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewContent)
    }

    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_running_tasks, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position].name


        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel//.toString()
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(ItemsViewModel)
            GlobalScope.launch {
                BackendClient.changeTaskStatus(mList[position].id)
            }.invokeOnCompletion {
                mList = mList.filter {
                    it.id != mList[position].id
                }
                activity.runOnUiThread {
                    notifyDataSetChanged()
                    Toast.makeText(holder.itemView.context, "Task "+ mList[position].name+" ended", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}