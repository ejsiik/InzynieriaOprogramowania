package com.example.ktogdziekiedy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ktogdziekiedy.R
import com.example.ktogdziekiedy.model.Worker
import kotlinx.android.synthetic.main.layout_pracownik_item.view.*
import kotlinx.android.synthetic.main.layout_raport_item.view.*
import kotlinx.coroutines.NonDisposableHandle.parent

class PodsumowaniePracownikAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Worker> = ArrayList()
    lateinit var onItemClick: (Worker) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PracownicyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_pracownik_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is PodsumowaniePracownikAdapter.PracownicyViewHolder -> {
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

    fun submitList(workerList: List<Worker>) {
        items = workerList
    }

    inner class PracownicyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imie = itemView.raport_imie
        val nazwisko = itemView.raport_nazwisko

        fun bind(worker: Worker) {
            imie.setText(worker.imie)
            nazwisko.setText(worker.nazwisko)
        }
    }
}