package com.example.ktogdziekiedy.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import backendconnection.BackendClient
import com.example.ktogdziekiedy.PodsumowanieZadaniaActivity
import com.example.ktogdziekiedy.R
import com.example.ktogdziekiedy.RaportRodzajActivity
import com.example.ktogdziekiedy.databinding.LayoutRaportSubItemBinding
import com.example.ktogdziekiedy.model.SubModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SubItemAdapter(
    private val context: Context,
    private val subItemModel: List<SubModel>,
    private val categoryName: String) : RecyclerView.Adapter<SubItemAdapter.ViewHolder>() {

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

            GlobalScope.launch {
                val me = BackendClient.me()
                if (!me.isAdmin || context !is PodsumowanieZadaniaActivity) {
                    return@launch
                }
                val mean = BackendClient.getMeanFromTask(categoryName, subItemM.subCategory)
//                val best = BackendClient.getBestTimeEnded(categoryName, subItemM.subCategory)
                (context as Activity).runOnUiThread {
                    subItemText.text = "${subItemM.subCategory} \n Średni czas: $mean"
                }
            }
        }
    }

    override fun getItemCount() = subItemModel.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = LayoutRaportSubItemBinding.bind(itemView)
    }
}