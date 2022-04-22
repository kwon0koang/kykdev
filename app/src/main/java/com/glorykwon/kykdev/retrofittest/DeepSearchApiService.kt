package com.glorykwon.kykdev.retrofittest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DeepSearchApiService {
    companion object {
        private const val BASE_URL = "https://api.ddi.deepsearch.com/"
        private const val AUTO_TOKEN = "MGQ1NzdiN2U0YmRmNGE2ZWE0ZWMxZGIxNmE0NDM4Y2M6ZTgzYTAzZGVhMmNlZmUzNzA4MjExMTZmMGRjNzkxMzg1NDU5YWNhZTIyNzQzNTUxNWUzNDY0NTFhZTViYjdiMQ=="

        @Volatile
        private var _INSTANCE: DeepSearchApiService? = null
        fun getInstance() = _INSTANCE ?: synchronized(this) {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(DeepSearchAuthenticationInterceptor(AUTO_TOKEN))
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DeepSearchApiService::class.java)
                .apply { _INSTANCE = this }
        }
    }

    /**
     * 뉴스 키워드 검색
     * api.ddi.deepsearch.com/haystack/v1/news/_search?query=삼성전자
     */
    @GET("haystack/v1/news/_search")
    suspend fun searchNewsByKeywordToJson(
        @Query("query") query: String,
        @Query("page") page: Int
    ): NewsResp
}