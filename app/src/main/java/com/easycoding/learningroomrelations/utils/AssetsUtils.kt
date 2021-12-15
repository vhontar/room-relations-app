package com.easycoding.learningroomrelations.utils

import android.content.res.AssetManager
import com.google.gson.Gson
import java.io.InputStream

object AssetsUtils {
    inline fun <reified T> parseJSON(assetManager: AssetManager): T {
        return Gson().fromJson(readJSONFromAsset(assetManager), T::class.java)
    }

    fun readJSONFromAsset(assetManager: AssetManager): String? {
        var json: String? = null
        try {
            val  inputStream: InputStream = assetManager.open("room_task.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}