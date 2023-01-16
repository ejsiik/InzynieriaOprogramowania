package com.example.ktogdziekiedy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import backendconnection.Task
import com.example.ktogdziekiedy.ItemsViewModel
import com.example.ktogdziekiedy.R

class RunningTasksAdapter (private val mList: List<Task>): RecyclerView.Adapter<RunningTasksAdapter.ViewHolder>() {
    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewContent)
    }

    var onItemClick: ((Task) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_running_tasks, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]


        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.toString()

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(ItemsViewModel)
            Toast.makeText(holder.itemView.context, "Start task", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
/*class RunningTasksAdapter (private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<RunningTasksAdapter.ViewHolder>() {

    var onItemClick: ((ItemsViewModel) -> Unit)? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_running_tasks, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]


        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(ItemsViewModel)
            Toast.makeText(holder.itemView.context, "Start task", Toast.LENGTH_LONG).show()
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewContent)
    }


}*/