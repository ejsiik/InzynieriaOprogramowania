package com.example.ktogdziekiedy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onClickListener()
    }

    public fun onClickListener() {
        var imageFirst = findViewById<ImageView>(R.id.ivFirst)
        var imageSecond = findViewById<ImageView>(R.id.ivSecond)

        imageFirst.setOnClickListener {
            openFirstActivity()
        }

        imageSecond.setOnClickListener {
            openSecondActivity()
        }
    }

    public fun openFirstActivity() {
        startActivity(Intent(this@MainActivity, FirstActivity::class.java))
    }

    public fun openSecondActivity() {
        startActivity(Intent(this@MainActivity, SecondActivity::class.java))
    }
}