package com.example.deportestr.di

import DeportesRepositoryImpl
import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.deportestr.datasource.DeportesRepository
import com.example.deportestr.datasource.remote.DeportesAPI
import com.example.deportestr.datasource.remote.DeportesRemoteDataSource
import com.example.deportestr.datasource.remote.DeportesRemoteDataSourceImpl
import com.example.deportestr.usecases.AddUserUsecases
import com.example.deportestr.usecases.AddUserUsecasesImpl
import com.example.deportestr.usecases.DeleteUserUsecases
import com.example.deportestr.usecases.DeleteUserUsecasesImpl
import com.example.deportestr.usecases.SearchAllSportsUsecases
import com.example.deportestr.usecases.SearchAllSportsUsecasesImpl
import com.example.deportestr.usecases.SearchUserByEmailUseCases
import com.example.deportestr.usecases.SearchUserByEmailUseCasesImpl
import com.example.deportestr.usecases.SearchUserUsecases
import com.example.deportestr.usecases.SearchUserUsecasesImpl
import com.example.deportestr.util.DispatcherProvider
import com.example.deportestr.util.DispatcherProviderImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun getInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
            Handler(Looper.getMainLooper()).post {
                Log.d("SIGNALCALL", request.toString())
            }
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor {}.apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @SuppressLint("SimpleDateFormat")
    @Provides
    fun provideGson(): Gson {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+02:00")
        dateFormat.timeZone = TimeZone.getTimeZone("Europe/Madrid")
        return GsonBuilder().serializeNulls().setLenient().setDateFormat(dateFormat.toPattern())
            .create()
    }


    @Provides
    @Singleton
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder.protocols(mutableListOf(Protocol.HTTP_1_1)).build()
    }

    //Utiliza gson y el metodo anterior lo llama como parametro

    @Provides
    @Singleton
    fun getRetrofit(
        client: OkHttpClient, gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.20:8080")//ip_casa = 192.168.1.20 / ip_local = 10.0.2.2
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client).build()
    }

    @Provides
    @Singleton
    fun provideDeportesAPI(
        retrofit: Retrofit
    ): DeportesAPI {
        return retrofit.create(DeportesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatchers():DispatcherProvider = DispatcherProviderImpl()

    @Provides
    @Singleton
    fun provideDeportesRemoteDataSource(
        deportesAPI: DeportesAPI, dispatcherProvider: DispatcherProvider
    ): DeportesRemoteDataSource {
        return DeportesRemoteDataSourceImpl(deportesAPI, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideDeportesRepository(
        deportesRemoteDataSource: DeportesRemoteDataSource
    ): DeportesRepository {
        return DeportesRepositoryImpl(deportesRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideSearchUserUsecases(
        deportesRepository: DeportesRepository
    ): SearchUserUsecases{
        return SearchUserUsecasesImpl(deportesRepository)
    }

    @Provides
    @Singleton
    fun provideSearchUserByEmailUsecases(
        deportesRepository: DeportesRepository
    ): SearchUserByEmailUseCases{
        return SearchUserByEmailUseCasesImpl(deportesRepository)
    }

    @Provides
    @Singleton
    fun provideAddUserUsecases(
        deportesRepository: DeportesRepository
    ): AddUserUsecases {
        return AddUserUsecasesImpl(deportesRepository)
    }

    @Provides
    @Singleton
    fun provideAllSportsUsecases(
        deportesRepository: DeportesRepository
    ): SearchAllSportsUsecases {
        return SearchAllSportsUsecasesImpl(deportesRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteUserUsecases(
        deportesRepository: DeportesRepository
    ): DeleteUserUsecases{
        return DeleteUserUsecasesImpl(deportesRepository)
    }

}