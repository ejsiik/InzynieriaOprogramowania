package com.example.ktogdziekiedy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ktogdziekiedy.R
import com.example.ktogdziekiedy.model.Category

class CategoriesAdapter2(
    private val context: Context,
    private val listOfCategories: List<Category>
) : RecyclerView.Adapter<CategoriesAdapter2.Category2ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Category2ViewHolder {
        return Category2ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Category2ViewHolder, position: Int) {
        holder.bind(listOfCategories[position])
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }

    class Category2ViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(category: Category) {
            view.findViewById<TextView>(R.id.categoryName).text = category.name
            view.findViewById<RecyclerView>(R.id.recyclerView).adapter =
                ItemsAdapter2(view.context, category.listOfItems)
        }
    }
}