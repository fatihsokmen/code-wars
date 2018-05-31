package com.github.fatihsokmen.codewars.dependency

import android.app.Application
import com.github.fatihsokmen.codewars.datasource.remote.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetModule {

    private const val BASE_URL = "https://www.codewars.com/api/v1/"

    @JvmStatic
    @Provides
    @Singleton
    fun provideHttpCache(app: Application) =
            Cache(app.cacheDir, (10 * 1024 * 1024).toLong())

    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        val okHttp = OkHttpClient.Builder()
        okHttp.addInterceptor(loggingInterceptor)
        okHttp.addInterceptor(AuthenticationInterceptor())
        okHttp.cache(cache)
        return okHttp.build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
}