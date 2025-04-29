package com.example.converter

import android.app.job.JobParameters
import android.app.job.JobService
import com.example.converter.network.RetrofitInstance
import kotlinx.coroutines.*

class UpdateJobService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getCurrencyList()
                if (response.isSuccessful) {
                    println("Данные валют обновлены: ${response.body()?.currencies?.keys}")
                } else {
                    println("Ошибка при обновлении: ${response.code()}")
                }
            } catch (e: Exception) {
                println("Сетевая ошибка: ${e.message}")
            } finally {
                jobFinished(params, false)
            }
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }
}