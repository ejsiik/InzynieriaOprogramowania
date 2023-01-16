package com.example.ktogdziekiedy.model

object Data {

    val workers = listOf(
        Worker("XD", "OK"),
        Worker("ASD", "dsaS")
    )

    val raports = listOf(
        Raport("Raport 1"),
        Raport("Raport 2"),
        Raport("Raport 3"),
        Raport("Raport 4"),
        Raport("Raport 5"),
        Raport("Raport 6"),
        Raport("Raport 7"),
        Raport("Raport 8"),
        Raport("Raport 9"),
        Raport("Raport 10"),
        Raport("Raport 11"),
        Raport("Raport 12"),
        Raport("Raport 13"),
        Raport("Raport 14"),
        Raport("Raport 15"),
        Raport("Raport 16"),
        Raport("Raport 17"),
        Raport("Raport 18"),
        Raport("Raport 19"),
        Raport("Raport 20")
    )

    private val subCat = listOf(
        SubModel("sub_category 1", raports),
        SubModel("sub_category 2", raports),
        SubModel("sub_category 3", raports),
        SubModel("sub_category 4", raports),
        SubModel("sub_category 5", raports),
        SubModel("sub_category 6", raports),
        SubModel("sub_category 7", raports),
        SubModel("sub_category 8", raports),
        SubModel("sub_category 9", raports),
        SubModel("sub_category 10", raports)
    )

    val cat = listOf(
        MainModel("category 1", subCat),
        MainModel("category 2", subCat),
        MainModel("category 3", subCat)
    )
}