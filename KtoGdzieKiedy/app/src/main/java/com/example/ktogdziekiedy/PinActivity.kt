package com.example.ktogdziekiedy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import backendconnection.BackendClient
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log

class PinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        val logInButton = findViewById<Button>(R.id.button_log_in)
        val bundle = intent.extras

        val password = findViewById<TextInputEditText>(R.id.pin_input_value)
        logInButton.setOnClickListener {
            if (bundle != null) {
                val login = "${bundle.getString("login")}"
                GlobalScope.launch {
                    BackendClient.login(login, password.text.toString())
                    val intent = Intent(this@PinActivity, PanelActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}