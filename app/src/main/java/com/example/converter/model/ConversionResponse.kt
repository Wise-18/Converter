package com.example.converter.model

data class ConversionResponse(
    val success: Boolean,
    val result: Double?,
    val error: String?
)