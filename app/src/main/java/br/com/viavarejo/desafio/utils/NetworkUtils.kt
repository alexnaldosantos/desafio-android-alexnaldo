package br.com.viavarejo.desafio.utils

import br.com.viavarejo.desafio.api.MarvelApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object NetworkUtils {
    fun createApiService() : MarvelApi {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientLog())
            .build()

        return retrofit.create(MarvelApi::class.java)
    }

    fun okHttpClientLog() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }
}