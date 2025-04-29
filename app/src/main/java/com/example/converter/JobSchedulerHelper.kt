package com.example.converter

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build

object JobSchedulerHelper {
    fun scheduleJob(context: Context) {
        val serviceComponent = ComponentName(context, UpdateJobService::class.java)
        val jobInfo = JobInfo.Builder(123, serviceComponent)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setPeriodic(15 * 60 * 1000) // каждые 15 минут
            .build()

        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }
}
