package com.example.ktogdziekiedy

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ktogdziekiedy.model.Raport
import com.example.ktogdziekiedy.store.ReportStore

class RaportSzczegolyActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raport_szczegoly)

        val data = ReportStore.task!!

        val nazwa = data.name
        val cat = data.category
        val start = data.createdAt
        val end = data.endTime
        val time = data.duration

        val name = findViewById<TextView>(R.id.task_nazwa)
        name.text = nazwa

        val kate = findViewById<TextView>(R.id.task_kategoria)
        kate.text = cat

        val star = findViewById<TextView>(R.id.task_start)
        star.text = start

        val koniec = findViewById<TextView>(R.id.task_stop)
        koniec.text = end

        val dur = findViewById<TextView>(R.id.task_czas)
        dur.text = time
    }

}