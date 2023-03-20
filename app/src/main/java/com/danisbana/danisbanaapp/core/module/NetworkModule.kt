package com.danisbana.danisbanaapp.core.module

import com.danisbana.danisbanaapp.BuildConfig
import com.danisbana.danisbanaapp.core.api.NotificationApi
import com.google.gson.Gson
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

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor())
        provideHeaders(okHttpClient)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient,gson: Gson): Retrofit {
        val retrofit  = Retrofit.Builder()
            .baseUrl(BuildConfig.FCM_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)

        return retrofit.build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }


    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): NotificationApi {
        return retrofit.create(NotificationApi::class.java)
    }

    fun provideHeaders(okHttpClientBuilder: OkHttpClient.Builder) {
        okHttpClientBuilder.addInterceptor {
            val original = it.request()

            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader("Authorization", "key=${BuildConfig.FCM_SERVER_KEY}")
            requestBuilder.addHeader("Content-Type", "application/json")
            val request = requestBuilder.method(original.method, original.body).build()
            return@addInterceptor it.proceed(request)
        }
    }

    private const val CONNECT_TIMEOUT = 20L
    private const val WRITE_TIMEOUT = 60L
    private const val READ_TIMEOUT = 60L
}