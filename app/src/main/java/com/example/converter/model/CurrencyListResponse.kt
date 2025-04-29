package com.example.converter.model

data class CurrencyListResponse(
    val success: Boolean,
    val currencies: Map<String, String>
)