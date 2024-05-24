package com.jdm.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jdm.network.api.KisService
import com.jdm.network.cost.restUrl
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KIS

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SOCKET

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val httpLogginInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val networkJson: Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @SOCKET
    @Provides
    @Singleton
    fun provideSocketOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .pingInterval(30, TimeUnit.SECONDS)
            .build()

    @KIS
    @Provides
    @Singleton
    fun provideKisOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    @KIS
    @Provides
    @Singleton
    fun provideKisRetrofit(@KIS okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(restUrl)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @KIS
    @Provides
    @Singleton
    fun provideKisService(@KIS retrofit: Retrofit): KisService {
        return retrofit.create(KisService::class.java)
    }


}