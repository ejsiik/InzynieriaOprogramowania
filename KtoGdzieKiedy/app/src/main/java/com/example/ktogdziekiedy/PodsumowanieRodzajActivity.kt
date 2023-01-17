package com.example.ktogdziekiedy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PodsumowanieRodzajActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podsumowanie_rodzaj)

        val zadanieButton = findViewById<Button>(R.id.button_1)
        val pracownikButton = findViewById<Button>(R.id.button_2)

        zadanieButton.setOnClickListener {
            val intent = Intent(this@PodsumowanieRodzajActivity, PodsumowanieZadaniaActivity::class.java)
            startActivity(intent)
        }

        pracownikButton.setOnClickListener {
            val intent = Intent(this@PodsumowanieRodzajActivity, PodsumowaniePracownicyActivity::class.java)
            startActivity(intent)
        }
    }
}