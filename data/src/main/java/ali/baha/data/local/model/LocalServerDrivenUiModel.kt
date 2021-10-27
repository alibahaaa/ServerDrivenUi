package ali.baha.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "key_value_table")
data class LocalServerDrivenUiModel(
    @PrimaryKey
    val keyData: String,
    val valueData: String
)
