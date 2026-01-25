package com.example.networkexperience.NetworkModul

import com.example.networkexperience.api.CommentApi
import com.example.networkexperience.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//основной модуль связи клиента с сервером

@Module
@InstallIn(SingletonComponent::class)
object Retrofit {

    // 1️⃣ Один Retrofit на всё приложение
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/") // базовый URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // 2️⃣ API интерфейсы через этот Retrofit
    //для каждого запроса свой метод
    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)


    @Provides
    @Singleton
    fun provideCommentApi(retrofit: Retrofit): CommentApi =
        retrofit.create(CommentApi::class.java)
}


