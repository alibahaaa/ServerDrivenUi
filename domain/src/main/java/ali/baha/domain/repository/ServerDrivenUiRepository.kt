package ali.baha.domain.repository

import ali.baha.domain.model.ServerDrivenUiEntity
import ali.baha.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ServerDrivenUiRepository {

    suspend fun getServerDrivenUiData(): Flow<DataState<ServerDrivenUiEntity?>>

}