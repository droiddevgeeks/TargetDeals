package com.target.targetcasestudy.di

import com.target.targetcasestudy.api.ApiConstants
import com.target.targetcasestudy.api.DealsApi
import com.target.targetcasestudy.common.CommonSchedulerProvider
import com.target.targetcasestudy.common.RxScheduler
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModules {

    companion object {
        @Provides
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()
        }

        @Provides
        fun provideApiInterface(retrofit: Retrofit): DealsApi {
            return retrofit.create(DealsApi::class.java)
        }
    }

    @Binds
    abstract fun provideRxScheduler(scheduler: CommonSchedulerProvider): RxScheduler
}