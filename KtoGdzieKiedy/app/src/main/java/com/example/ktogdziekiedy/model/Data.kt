package com.example.ktogdziekiedy.model

object Data {

    val raports = listOf(
        Raport("Raport")
    )

    private val subCat1 = listOf(
        SubModel("LCD", raports),
        SubModel("Battery", raports),
        SubModel("USB port", raports),
        SubModel("Camera", raports),
        SubModel("Speaker", raports),
        SubModel("Software", raports)
    )

    private val subCat2 = listOf(
        SubModel("CPU", raports),
        SubModel("GPU", raports),
        SubModel("RAM", raports),
        SubModel("PSU", raports),
        SubModel("Motherboard", raports),
        SubModel("Software", raports),
        SubModel("Clean up", raports)
    )

    private val subCat3 = listOf(
        SubModel("Controller", raports),
        SubModel("Disc", raports),
        SubModel("HDMI controller", raports),
        SubModel("PSU", raports),
        SubModel("Clean up", raports),
        SubModel("Software", raports)
    )

    val cat = mutableListOf(
        MainModel("PHONE", subCat1),
        MainModel("COMPUTER", subCat2),
        MainModel("CONSOLE", subCat3)
    )

    //val users = mutableListOf()
}