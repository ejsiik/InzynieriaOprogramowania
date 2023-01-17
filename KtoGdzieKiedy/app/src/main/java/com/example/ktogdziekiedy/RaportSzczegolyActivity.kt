package com.example.ktogdziekiedy

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

        val userNick = findViewById<TextView>(R.id.task_user)
        val userLabel = findViewById<TextView>(R.id.task_6)
        if(data.user != null){
            val nick = data.user!!.login
            userNick.text = nick
        }
        else {
            userNick.visibility = View.GONE
            userLabel.visibility = View.GONE
        }

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