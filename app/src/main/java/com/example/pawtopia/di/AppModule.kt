package com.example.pawtopia.di

import android.content.Context
import com.example.pawtopia.data.remote.SymptomApi
import com.example.pawtopia.BuildConfig
import com.example.pawtopia.data.remote.SuspectApi
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
import java.util.concurrent.TimeUnit
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
    fun provideSymptomService(): SymptomApi {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SYMPTOM_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(SymptomApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSuspectService(): SuspectApi {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client = OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SUSPECT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(SuspectApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSymptoms(symptomApi: SymptomApi, suspectApi: SuspectApi): PawtopiaRepository = PawtopiaRepositoryImpl(symptomApi,suspectApi)

    @Provides fun provideAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(auth)
}