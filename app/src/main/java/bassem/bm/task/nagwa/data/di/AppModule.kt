package bassem.bm.task.nagwa.data.di

import bassem.bm.task.nagwa.data.remote.ApiHelper
import bassem.bm.task.nagwa.data.remote.ApiHelperImpl
import bassem.bm.task.nagwa.data.remote.ApiService
import bassem.bm.task.nagwa.data.repository.Repository
import bassem.bm.task.nagwa.data.repository.RepositoryImpl
import bassem.bm.task.nagwa.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String, moshiConverterFactory: MoshiConverterFactory): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(moshiConverterFactory)
        .build()

    @Singleton
    @Provides
    fun provideConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideRepositoryHelper(repository: RepositoryImpl): Repository = repository

}