package com.example.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.local.AppDataBase
import com.example.local.HeroesDao
import com.example.remote.DotaApi
import com.example.repository.ProfileRepositoryImpl
import com.example.utils.BASE_URL
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

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @Singleton
    @Provides
    fun provideClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideDotaApi(okHttpClient: OkHttpClient): DotaApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DotaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDataBase {
        return Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            "AppDb"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideHeroesDao(dataBase: AppDataBase): HeroesDao {
        return dataBase.heroesDao()
    }

    @Singleton
    @Provides
    fun provideProfileRepositoryImpl(@ApplicationContext context: Context): ProfileRepositoryImpl =
        ProfileRepositoryImpl(context)
}