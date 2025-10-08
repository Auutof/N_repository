package com.example.n_project

import java.net.HttpURLConnection
import java.net.URL

class ApiManager {
    fun getDataFromApi(urlString: String): String? {
        return try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val inputStream = connection.inputStream
            val data = inputStream.bufferedReader().use { it.readText() }

            inputStream.close()
            connection.disconnect()
            data
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
