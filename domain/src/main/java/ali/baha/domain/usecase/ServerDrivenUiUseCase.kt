package ali.baha.domain.usecase

import ali.baha.domain.model.KeyValueEntity
import ali.baha.domain.model.ServerDrivenUiEntity
import ali.baha.domain.repository.ServerDrivenUiRepository
import ali.baha.domain.utils.DataState
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServerDrivenUiUseCase @Inject constructor(
    private val serverDrivenUiRepository: ServerDrivenUiRepository
) {

    suspend fun getServerDrivenUiData(): Flow<DataState<ServerDrivenUiEntity?>> =
        serverDrivenUiRepository.getServerDrivenUiData()

    suspend fun insertKeyValueInDataBase(keyData: String, valueData: String) =
        serverDrivenUiRepository.insertKeyValueInDataBase(keyData, valueData)

    suspend fun getKeyValueInDataBase(keyData: String) : KeyValueEntity =
        serverDrivenUiRepository.getKeyValueInDataBase(keyData)

    suspend fun getClickData(
        url: String,
        jsonObject: JsonObject
    ): Flow<DataState<String>> = serverDrivenUiRepository.getClickData(url, jsonObject)

}