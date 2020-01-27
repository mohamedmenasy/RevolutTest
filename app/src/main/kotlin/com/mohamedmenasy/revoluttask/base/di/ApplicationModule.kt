package com.mohamedmenasy.revoluttask.base.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mohamedmenasy.core.executor.ExecutionScheduler
import com.mohamedmenasy.core.executor.ThreadScheduler
import com.mohamedmenasy.revoluttask.AndroidApplication
import com.mohamedmenasy.revoluttask.BuildConfig
import com.mohamedmenasy.revoluttask.features.rates.data.CurrenciesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Application Module for Dagger
 */
@Module
class ApplicationModule(private val application: AndroidApplication) {

  @Provides
  @Singleton
  fun provideApplicationContext(): Context = application

  @Provides
  @Singleton
  fun provideExecutionScheduler(threadScheduler: ThreadScheduler): ExecutionScheduler =
      threadScheduler

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://revolut.duckdns.org/")
        .client(createClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

  private fun createClient(): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
      val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
      okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
  }

  @Provides
  @Singleton
  fun provideCurrenciesRepository(dataSource: CurrenciesRepository.Network): CurrenciesRepository =
      dataSource
}
