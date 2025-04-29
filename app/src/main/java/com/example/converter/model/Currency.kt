package com.example.converter.model

data class Currency(
    val base: String,
    val rates: Map<String, Double>,
    val date: String
)
