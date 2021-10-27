package ali.baha.serverdrivenui.di

import ali.baha.data.repository.ServerDrivenUiRepositoryImpl
import ali.baha.domain.repository.ServerDrivenUiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServerDrivenUiRepositoryModule {
    @Singleton
    @Provides
    fun provideServerDrivenUiRepository(
        serverDrivenUiRepositoryImpl: ServerDrivenUiRepositoryImpl
    ): ServerDrivenUiRepository = serverDrivenUiRepositoryImpl
}