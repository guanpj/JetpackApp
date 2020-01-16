package com.me.guanpj.jetpackapp.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.me.guanpj.jetpackapp.Constants
import com.me.guanpj.jetpackapp.data.bean.Component
import com.me.guanpj.jetpackapp.data.bean.db.HomeDB
import kotlinx.coroutines.coroutineScope

/**
 * @author Hankkin
 * @date 2019-05-30
 */
class SeedDatabaseWorker (
    context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {

    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    override suspend fun doWork(): Result = coroutineScope {

        try {
            applicationContext.assets.open(Constants.COMPONENT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Component>>() {}.type
                    val plantList: List<Component> = Gson().fromJson(jsonReader,plantType)
                    val database = HomeDB.getInstance(applicationContext)
                    database.homeDao().insertAll(plantList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }
}