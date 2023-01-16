package com.example.ktogdziekiedy

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import backendconnection.BackendClient
import kotlinx.android.synthetic.main.activity_popup_logout.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PanelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel)

        GlobalScope.launch {
            val userName = findViewById<TextView>(R.id.textView2)
            userName.text = "Hello "+BackendClient.me().login
        }
        onClickListener()
    }

    fun onClickListener() {
        val imageFirst = findViewById<ImageView>(R.id.ivFirst)
        val imageSecond = findViewById<ImageView>(R.id.ivSecond)
        val imageStats = findViewById<ImageView>(R.id.ivStats)
        val imageLogout = findViewById<ImageView>(R.id.ivLogout)


        imageFirst.setOnClickListener {
            openFirstActivity()
        }

        imageSecond.setOnClickListener {
            openSecondActivity()
        }

        imageStats.setOnClickListener {
            openStatsActivity()
        }

        imageLogout.setOnClickListener {
            openLogoutActivity()
        }
    }

    fun openFirstActivity() {
        startActivity(Intent(this@PanelActivity, FirstActivity::class.java))
    }

    fun openSecondActivity() {
        startActivity(Intent(this@PanelActivity, SecondActivity::class.java))
    }

    fun openStatsActivity() {
        startActivity(Intent(this@PanelActivity, RaportRodzajActivity::class.java))
    }

    fun openLogoutActivity() {
        val dialogBinding = layoutInflater.inflate(R.layout.activity_popup_logout, null)
        val myDialog = Dialog(this)
        myDialog.setContentView(dialogBinding)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
        myDialog.popupLogout.setOnClickListener {
            startActivity(Intent(this@PanelActivity, MainActivity::class.java))
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onBackPressed() {}
}