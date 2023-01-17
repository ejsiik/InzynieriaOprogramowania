package com.example.ktogdziekiedy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import backendconnection.BackendClient
import backendconnection.Task
import com.example.ktogdziekiedy.adapter.MainItemAdapter
import com.example.ktogdziekiedy.databinding.ActivityRaportKategoriaBinding
import com.example.ktogdziekiedy.model.MainModel
import com.example.ktogdziekiedy.model.SubModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class RaportKategoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRaportKategoriaBinding
    var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        job = GlobalScope.launch {
            val viewData = mutableListOf<MainModel>()
            val tasks = BackendClient.getDoneTasksFromCurrentUserHierarchy()
            tasks.forEach {
                category ->
                val main = MainModel(category.key, mutableListOf<SubModel>())
                category.value.forEach {
                    name ->
                    val sub = SubModel(name.key, mutableListOf<Task>())
                    name.value.tasks.forEach {
                        task ->
                        sub.tasks.add(task)
                    }
                    main.subItemModel.add(sub)
                }
                viewData.add(main)
            }

            runOnUiThread {
                binding = ActivityRaportKategoriaBinding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.kategoriaRv.adapter = MainItemAdapter(this@RaportKategoriaActivity, viewData)
            }
        }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}