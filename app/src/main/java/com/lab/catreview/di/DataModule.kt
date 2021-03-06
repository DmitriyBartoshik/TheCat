package com.lab.catreview.di


import com.lab.catreview.data.api.RemoteApi
import com.lab.catreview.data.constant.Constants
import com.lab.catreview.data.datasource.RemoteDataSource
import com.lab.catreview.data.repository.RemoteRepositoryImpl
import com.lab.mycats.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

@Module
class DataModule {

    @Provides
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    internal fun provideRemoteApi(retrofit: Retrofit): RemoteApi =
        retrofit.create(RemoteApi::class.java)

    @Provides
//    @Reusable
    internal fun provideRemoteSource(api: RemoteApi): RemoteDataSource = RemoteDataSource(api)

    @Provides
//    @Reusable
    internal fun provideRemoteRepository(source: RemoteDataSource): RemoteRepository =
        RemoteRepositoryImpl(source)


    @Provides
    @Reusable
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    private val requestInterceptor = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader("x-api-key", Constants.API_KEY)
            .build()
        return@Interceptor chain.proceed(request)
    }
}