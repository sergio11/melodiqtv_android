package com.sanchezsergio.newsapp.di.persistence.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.sanchezsergio.newsapp.persistence.network.interceptors.AuthorizationInterceptor
import com.sanchezsergio.newsapp.persistence.network.source.TopHeadlinesPageSource
import com.sanchezsergio.newsapp.persistence.network.service.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.ElementsIntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://newsapi.org/v2"

    @Provides
    @ElementsIntoSet
    @Named("interceptors")
    fun provideInterceptors(): Set<Interceptor> = mutableSetOf(AuthorizationInterceptor())

    @Provides
    @ElementsIntoSet
    @Named("network_interceptors")
    fun provideNetworkInterceptors(): Set<Interceptor> =
            mutableSetOf(StethoInterceptor())

    /**
     * Provide OkHttp Client
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
            @Named("interceptors") interceptors: Set<@JvmSuppressWildcards Interceptor>,
            @Named("network_interceptors") networkInterceptors: Set<@JvmSuppressWildcards Interceptor>) =
            OkHttpClient.Builder().apply {
                // Add Interceptors
                interceptors.forEach {
                    addInterceptor(it)
                }
                // Add Network Interceptors
                networkInterceptors.forEach {
                    addNetworkInterceptor(it)
                }
            }.build()

    /**
     * Provide Retrofit
     * @param okHttpClient
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) =  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder().create()))
            .client(okHttpClient)
            .build()

    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService =
            retrofit.create(NewsService::class.java)

    @Provides
    fun provideTopHeadlinesPageSource(newsService: NewsService): TopHeadlinesPageSource =
            TopHeadlinesPageSource(newsService)

}