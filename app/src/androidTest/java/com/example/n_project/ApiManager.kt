package com.example.n_project

import okhttp3.OkHttpClient
import okhttp3.Request

class ApiManager {

    private val client = OkHttpClient()

    fun getDataFromApi(url: String): String? {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            return if (response.isSuccessful) {
                response.body?.string()
            } else {
                null
            }
        }
    }
}
