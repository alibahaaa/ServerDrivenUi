package ali.baha.serverdrivenui.di

import ali.baha.data.local.ServerDrivenUiDao
import ali.baha.data.local.ServerDrivenUiDataBase
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ServerDrivenUiDataBase =
        Room.databaseBuilder(
            context,
            ServerDrivenUiDataBase::class.java,
            "server_driven_ui_db"
        ).build()

    @Provides
    fun provideServerDrivenUiDao(serverDrivenUiDataBase: ServerDrivenUiDataBase): ServerDrivenUiDao {
        return serverDrivenUiDataBase.getServerDrivenUiDao()
    }
}