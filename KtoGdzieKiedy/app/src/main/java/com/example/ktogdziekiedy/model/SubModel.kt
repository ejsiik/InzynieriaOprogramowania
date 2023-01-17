package com.example.ktogdziekiedy.model

import backendconnection.Task

data class SubModel(val subCategory: String, val tasks: MutableList<Task>)
