package ali.baha.serverdrivenui.di

import ali.baha.data.remote.service.RemoteService
import ali.baha.data.remote.service.RemoteHelper
import ali.baha.data.remote.service.RemoteHelperImpl
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
    fun provideRemoteService(retrofit: Retrofit): RemoteService {
        return retrofit.create(RemoteService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteHelper(remoteServiceImpl: RemoteHelperImpl): RemoteHelper = remoteServiceImpl
}