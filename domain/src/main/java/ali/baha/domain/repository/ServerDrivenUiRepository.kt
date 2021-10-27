package ali.baha.domain.repository

import ali.baha.domain.model.KeyValueEntity
import ali.baha.domain.model.ServerDrivenUiEntity
import ali.baha.domain.utils.DataState
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

interface ServerDrivenUiRepository {

    suspend fun getServerDrivenUiData(): Flow<DataState<ServerDrivenUiEntity?>>

    suspend fun insertKeyValueInDataBase(keyData: String, valueData: String)

    suspend fun getKeyValueInDataBase(keyData: String) : KeyValueEntity

    suspend fun getClickData(url: String, jsonObject: JsonObject): Flow<DataState<String>>

}