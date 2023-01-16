package com.example.ktogdziekiedy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton = findViewById<Button>(R.id.button_submit)
        val login = findViewById<AutoCompleteTextView>(R.id.autoTextView)
        val bundle = Bundle()


        submitButton.setOnClickListener {
            bundle.putString("login", login.text.toString())
            if (login.text.isEmpty()) {
                Toast.makeText(this@MainActivity, "Username can not be empty", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this@MainActivity, PinActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }
}