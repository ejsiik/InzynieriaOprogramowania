package com.example.ktogdziekiedy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import backendconnection.BackendClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RaportRodzajActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raport_rodzaj)

        val kategorieButton = findViewById<Button>(R.id.button_category)
        val podsumowanieButton = findViewById<Button>(R.id.button_summary)
        val wszystkieButton = findViewById<Button>(R.id.button_all)

        GlobalScope.launch {
            val isadmin = BackendClient.me().isAdmin

            runOnUiThread{
                if (isadmin == true) {
                    podsumowanieButton.visibility  = View.VISIBLE
                }
                else {
                    podsumowanieButton.visibility  = View.INVISIBLE
                }
            }
        }

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