package ali.baha.serverdrivenui.viewmodel

import ali.baha.domain.model.KeyValueEntity
import ali.baha.domain.model.ServerDrivenUiEntity
import ali.baha.domain.usecase.ServerDrivenUiUseCase
import ali.baha.domain.utils.DataState
import ali.baha.serverdrivenui.utils.ViewState
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class ServerDrivenUiViewModel @Inject constructor(
    private val serverDrivenUiUseCase: ServerDrivenUiUseCase
) : ViewModel() {
    fun getServerDrivenUiData(): Flow<ViewState<ServerDrivenUiEntity?>> = flow {
        serverDrivenUiUseCase.getServerDrivenUiData()
            .catch {
                emit(ViewState.Error(it.message ?: "Error"))
            }.collect {
                when (it) {
                    is DataState.Error -> emit(ViewState.Error(it.exception.message ?: "Error"))
                    is DataState.Loading -> emit(ViewState.Loading)
                    is DataState.Success -> emit(ViewState.Success(it.data))
                }
            }
    }

    suspend fun insertKeyValueInDataBase(keyData: String, valueData: String) =
        serverDrivenUiUseCase.insertKeyValueInDataBase(keyData, valueData)

    suspend fun getKeyValueInDataBase(keyData: String): KeyValueEntity =
        serverDrivenUiUseCase.getKeyValueInDataBase(keyData)

    suspend fun getClickData(
        url: String,
        jsonObject: JsonObject
    ): Flow<ViewState<String?>> = flow {
        serverDrivenUiUseCase.getClickData(url.replace("http","https"), jsonObject)
            .catch {
                emit(ViewState.Error(it.message ?: "Error"))
            }.collect {
                when (it) {
                    is DataState.Error -> emit(ViewState.Error(it.exception.message ?: "Error"))
                    is DataState.Loading -> emit(ViewState.Loading)
                    is DataState.Success -> emit(ViewState.Success(it.data))
                }
            }
    }
}