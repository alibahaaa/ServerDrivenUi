package ali.baha.data.repository

import ali.baha.data.mapper.ModelMapper
import ali.baha.data.remote.RemoteHelper
import ali.baha.data.remote.RemoteService
import ali.baha.domain.model.ServerDrivenUiEntity
import ali.baha.domain.repository.ServerDrivenUiRepository
import ali.baha.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class ServerDrivenUiRepositoryImpl @Inject constructor(
    private val remoteService: RemoteService,
    private val mapper: ModelMapper
) : ServerDrivenUiRepository {
    override suspend fun getServerDrivenUiData(): Flow<DataState<ServerDrivenUiEntity?>> = flow {
        emit(DataState.Loading)
        try {
            val response = remoteService.getServerDrivenUiData()
            val res = mapper.mapFromEntity(response?.data!!)
            emit(DataState.Success(res))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}