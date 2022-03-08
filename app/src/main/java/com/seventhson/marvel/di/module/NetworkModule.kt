package com.seventhson.marvel.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.seventhson.marvel.BuildConfig
import com.seventhson.marvel.data.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val API_KEY = "caca090162851f03964061a23594be34"
        const val HASH = "50e661e642392d3c97e36bb2ef76dbda"
        const val TIME_STAMP = "1"

        const val API_KEY_HEADER = "apikey"
        const val HASH_HEADER = "hash"
        const val TIME_STAMP_HEADER = "ts"
    }

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    internal fun provideRetrofitClient(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val interceptorLog = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                val url = original.url.newBuilder()
                    .addQueryParameter(TIME_STAMP_HEADER, TIME_STAMP)
                    .addQueryParameter(API_KEY_HEADER, API_KEY)
                    .addQueryParameter(HASH_HEADER, HASH)
                    .build()

                val requestBuilder = original.newBuilder().url(url)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addNetworkInterceptor(interceptorLog)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

}