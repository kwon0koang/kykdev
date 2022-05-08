package com.glorykwon.kykdev.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitTestApiService {
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
//        private const val TOKEN = "SDFJAEOICJXVAUIFHJWENILKVJDVASDUOJFELJFNKLDNVZKLDGJSIDROLGKXFNGJKLFGSRKLSRLEIGN"       //헤더 토큰

        @Volatile
        private var _INSTANCE: RetrofitTestApiService? = null
        fun getInstance() = _INSTANCE ?: synchronized(this) {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
//                .addInterceptor(RetrofitTestInterceptor(TOKEN))
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitTestApiService::class.java)
                .apply { _INSTANCE = this }
        }
    }

    /**
     * 검색
     * https://jsonplaceholder.typicode.com/todos?userId=1
     */
    @GET("todos")
    suspend fun searchByUserId(
        @Query("userId") id: Int
    ): List<RetrofitTestDto>

    /**
     * 검색
     * https://jsonplaceholder.typicode.com/todos?id=1
     */
    @GET("todos")
    suspend fun searchById(
        @Query("id") id: Int
    ): List<RetrofitTestDto>
}