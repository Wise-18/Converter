package com.example.converter.network

import com.example.converter.model.CurrencyListResponse
import com.example.converter.model.ConversionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CurrencyApi {
    @GET("list")
    suspend fun getCurrencyList(
        @Header("apikey") apiKey: String = "qheTqidHrd0cZHgjPos8LLNMPo337Cjz"
    ): Response<CurrencyListResponse>

    @GET("convert")
    suspend fun convertCurrency(
        @Header("apikey") apiKey: String = "qheTqidHrd0cZHgjPos8LLNMPo337Cjz",
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ): Response<ConversionResponse>
}