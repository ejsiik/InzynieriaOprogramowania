package com.example.ktogdziekiedy.model

class Category(val name: String, vararg item: Item) {

    val listOfItems: List<Item> = item.toList()

}