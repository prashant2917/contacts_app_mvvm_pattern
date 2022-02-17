package com.pocket.contacts.di

import com.pocket.contacts.network.ApiInterface
import com.pocket.contacts.network.RetrofitBuilder
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun provideApiInterface(): ApiInterface {
        return RetrofitBuilder.buildService(ApiInterface::class.java)
    }
}