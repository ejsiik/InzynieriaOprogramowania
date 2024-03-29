package com.example.ktogdziekiedy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import backendconnection.BackendClient
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        val logInButton = findViewById<Button>(R.id.button_log_in)
        val bundle = intent.extras

        val password = findViewById<TextInputEditText>(R.id.pin_input_value)
        logInButton.setOnClickListener {
            GlobalScope.launch {
                val isEmpty = password.getText().toString()
                if (isEmpty.equals("")) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@PinActivity,
                            "Password can not be empty",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    if (bundle != null) {
                        val login = "${bundle.getString("login")}"
                        try {
                            BackendClient.login(login, password.text.toString())
                            val intent = Intent(this@PinActivity, PanelActivity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        } catch (err: Exception) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    this@PinActivity,
                                    "Incorrect username or password",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intent = Intent(this@PinActivity, MainActivity::class.java)
                                startActivity(intent)
                                overridePendingTransition(
                                    R.anim.slide_in_right,
                                    R.anim.slide_out_left
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}