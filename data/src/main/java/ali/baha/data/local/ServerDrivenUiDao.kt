package ali.baha.data.local

import ali.baha.data.local.model.LocalServerDrivenUiModel
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ServerDrivenUiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeyValueData(localServerDrivenUiModel: LocalServerDrivenUiModel)

    @Query("SELECT * FROM key_value_table WHERE keyData = :keyData ")
    suspend fun getKeyValueData(keyData: String) : LocalServerDrivenUiModel
}