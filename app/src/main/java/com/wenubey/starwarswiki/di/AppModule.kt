package com.wenubey.starwarswiki.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.wenubey.starwarswiki.data.local.StarWarsDatabase
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.data.remote.StarWarsApi
import com.wenubey.starwarswiki.data.remote.StarWarsRemoteMediator
import com.wenubey.starwarswiki.data.remote.StarWarsImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideStarWarsDatabase(@ApplicationContext context: Context): StarWarsDatabase {
        return Room.databaseBuilder(
            context,
            StarWarsDatabase::class.java,
            "characters.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideStarWarsPager(db: StarWarsDatabase, api: StarWarsApi, imageApi: StarWarsImageApi): Pager<Int, CharacterEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = StarWarsRemoteMediator(
                db = db,
                api = api,
                imageApi = imageApi
            ),
            pagingSourceFactory = {
                db.dao.pagingSource()
            }
        )
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        return client.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): StarWarsApi {
        return Retrofit.Builder().baseUrl(StarWarsApi.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(StarWarsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitForImage(
        okHttpClient: OkHttpClient
    ): StarWarsImageApi {
        return Retrofit.Builder().baseUrl(StarWarsImageApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}