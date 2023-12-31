package com.david.tot.di

import com.david.tot.data.network.consumible.IConsumibleApiClient
import com.david.tot.data.network.authenticable.IAuthenticableApiClient
import com.david.tot.data.network.confirmable.IConfirmableApiClient
import com.david.tot.data.network.reloadable.IReloadableApiClient
import com.david.tot.data.network.spendable.ISpendableApiClient
import com.david.tot.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeApiClient(retrofit: Retrofit): IConsumibleApiClient {
        return retrofit.create(IConsumibleApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthenticableApiClient(retrofit: Retrofit): IAuthenticableApiClient {
        return retrofit.create(IAuthenticableApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideReloadableApiClient(retrofit: Retrofit): IReloadableApiClient {
        return retrofit.create(IReloadableApiClient::class.java)
    }


    @Singleton
    @Provides
    fun provideConfirmableApiClient(retrofit: Retrofit): IConfirmableApiClient {
        return retrofit.create(IConfirmableApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideSpendableApiClient(retrofit: Retrofit): ISpendableApiClient {
        return retrofit.create(ISpendableApiClient::class.java)
    }
}