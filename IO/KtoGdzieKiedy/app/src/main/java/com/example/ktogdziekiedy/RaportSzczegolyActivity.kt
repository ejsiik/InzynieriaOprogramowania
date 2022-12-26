package com.example.ktogdziekiedy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ktogdziekiedy.model.Raport

class RaportSzczegolyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raport_szczegoly)

        val raportTitle = intent.getParcelableExtra<Raport>("title")

        if (raportTitle != null) {
            val textView : TextView = findViewById(R.id.szczegoly_title)

            textView.text = raportTitle.title
        }
    }
}