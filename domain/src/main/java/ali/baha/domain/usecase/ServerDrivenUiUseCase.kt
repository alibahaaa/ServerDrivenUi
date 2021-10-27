package ali.baha.domain.usecase

import ali.baha.domain.model.ServerDrivenUiEntity
import ali.baha.domain.repository.ServerDrivenUiRepository
import ali.baha.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServerDrivenUiUseCase @Inject constructor(
    private val serverDrivenUiRepository: ServerDrivenUiRepository
) {
    suspend fun getServerDrivenUiData(): Flow<DataState<ServerDrivenUiEntity?>> =
        serverDrivenUiRepository.getServerDrivenUiData()
}