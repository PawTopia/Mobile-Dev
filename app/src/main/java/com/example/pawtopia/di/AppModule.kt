package com.example.pawtopia.di

import android.content.Context
import com.example.pawtopia.data.remote.ApiService
import com.example.pawtopia.BuildConfig
import com.example.pawtopia.data.repository.AuthRepositoryImpl
import com.example.pawtopia.data.repository.DataStoreRepositoryImpl
import com.example.pawtopia.data.repository.PawtopiaRepositoryImpl
import com.example.pawtopia.domain.repository.AuthRepository
import com.example.pawtopia.domain.repository.DataStoreRepository
import com.example.pawtopia.domain.repository.PawtopiaRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(context = context)


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSymptoms(apiService: ApiService): PawtopiaRepository = PawtopiaRepositoryImpl(apiService)

    @Provides fun provideAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(auth)
}