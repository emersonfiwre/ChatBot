package br.com.emersonfiwre.chatbot.infrastructure.service

import br.com.emersonfiwre.chatbot.infrastructure.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitClient {
    private lateinit var retrofit: Retrofit
    private const val CONTENT_TYPE = "Content-Type"
    private const val JSON_CHARSET_UTF8 = "application/json; charset=utf-8"

    private fun getRetrofitInstance(): Retrofit {
        if (!::retrofit.isInitialized) {
            val httpClient = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()

            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            httpClient.addInterceptor { chain ->
                val request =
                    chain.request()
                        .newBuilder()
                        .addHeader(CONTENT_TYPE, JSON_CHARSET_UTF8)
                        .build()
                val response = chain.proceed(request)
                response.newBuilder()
                    .addHeader(CONTENT_TYPE, JSON_CHARSET_UTF8)
                    .build()
            }

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return getRetrofitInstance().create(serviceClass)
    }
}