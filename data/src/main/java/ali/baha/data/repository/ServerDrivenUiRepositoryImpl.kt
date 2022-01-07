package ali.baha.data.repository

import ali.baha.data.local.service.ServerDrivenUiDao
import ali.baha.data.local.model.LocalServerDrivenUiModel
import ali.baha.data.remote.service.RemoteHelper
import ali.baha.domain.model.KeyValueEntity
import ali.baha.domain.model.ServerDrivenUiEntity
import ali.baha.domain.repository.ServerDrivenUiRepository
import ali.baha.domain.utils.DataState
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class ServerDrivenUiRepositoryImpl @Inject constructor(
    private val remoteHelper: RemoteHelper,
    private val serverDrivenUiDao: ServerDrivenUiDao
//    private val mapper: ModelMapper
) : ServerDrivenUiRepository {
    override suspend fun getServerDrivenUiData(): Flow<DataState<ServerDrivenUiEntity?>> = flow {
        emit(DataState.Loading)
        try {
            val response = remoteHelper.getServerDrivenUiData()
//            val res = mapper.mapFromEntity(response?.data!!)
            emit(DataState.Success(data = response))
        } catch (e: Exception) {
            emit(DataState.Error(exception = e))
        }
    }

    override suspend fun insertKeyValueInDataBase(keyData: String, valueData: String) {
        serverDrivenUiDao.insertKeyValueData(
            LocalServerDrivenUiModel(
                keyData = keyData,
                valueData = valueData
            )
        )
    }

    override suspend fun getKeyValueInDataBase(keyData: String): KeyValueEntity {
        val res = serverDrivenUiDao.getKeyValueData(keyData = keyData)
        return KeyValueEntity(res.keyData, res.valueData)
    }

    override suspend fun getClickData(
        url: String,
        jsonObject: JsonObject
    ): Flow<DataState<String>> = flow {
        emit(DataState.Loading)
        try {
            val response = remoteHelper.getClickData(url, jsonObject)
            emit(DataState.Success(data = response?.username!!))
        } catch (e: Exception) {
            emit(DataState.Error(exception = e))
        }
    }
}