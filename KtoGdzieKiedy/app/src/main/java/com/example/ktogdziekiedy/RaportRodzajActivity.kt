package com.example.ktogdziekiedy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RaportRodzajActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raport_rodzaj)

        val kategorieButton = findViewById<Button>(R.id.button_category)
        val wszystkieButton = findViewById<Button>(R.id.button_all)

        wszystkieButton.setOnClickListener {
            val intent = Intent(this@RaportRodzajActivity, RaportPracownikActivity::class.java)
            startActivity(intent)
        }

        kategorieButton.setOnClickListener {
            val intent = Intent(this@RaportRodzajActivity, RaportKategoriaActivity::class.java)
            startActivity(intent)
        }
    }
}