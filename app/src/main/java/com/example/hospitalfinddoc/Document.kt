package com.example.hospitalfinddoc

data class Document(

    val id: Int = 0,

    val documentName: String,

    val ward: String,

    val storageLocation: String,

    val category: String,

    val description: String,

    val lastSeen: String
)