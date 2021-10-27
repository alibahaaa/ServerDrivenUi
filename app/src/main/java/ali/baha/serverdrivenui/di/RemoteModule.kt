package ali.baha.serverdrivenui.di

import ali.baha.data.remote.RemoteHelper
import ali.baha.data.remote.RemoteService
import ali.baha.data.remote.RemoteServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun provideApiService(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://kashkool.basalam.com/")
        .build()

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit): RemoteHelper {
        return retrofit.create(RemoteHelper::class.java)
    }

    @Singleton
    @Provides
    fun provideApiHelper(remoteServiceImpl: RemoteServiceImpl): RemoteService = remoteServiceImpl
}