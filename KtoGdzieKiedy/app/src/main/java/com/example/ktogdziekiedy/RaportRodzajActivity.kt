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
        val podsumowanieButton = findViewById<Button>(R.id.button_summary)
        val wszystkieButton = findViewById<Button>(R.id.button_all)

        wszystkieButton.setOnClickListener {
            val intent = Intent(this@RaportRodzajActivity, RaportPracownikActivity::class.java)
            startActivity(intent)
        }

        podsumowanieButton.setOnClickListener {
            val intent = Intent(this@RaportRodzajActivity, PodsumowanieRodzajActivity::class.java)
            startActivity(intent)
        }

        kategorieButton.setOnClickListener {
            val intent = Intent(this@RaportRodzajActivity, RaportKategoriaActivity::class.java)
            startActivity(intent)
        }
    }
}