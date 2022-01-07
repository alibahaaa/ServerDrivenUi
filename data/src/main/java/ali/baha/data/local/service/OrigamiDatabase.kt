package ali.baha.data.local.service

import ali.baha.data.local.model.LocalServerDrivenUiModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalServerDrivenUiModel::class], version = 1)
abstract class ServerDrivenUiDataBase : RoomDatabase() {
    abstract fun getServerDrivenUiDao(): ServerDrivenUiDao
}