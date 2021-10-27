package ali.baha.data.remote

import ali.baha.data.model.RemoteModel


interface RemoteService {

    suspend fun getServerDrivenUiData() : RemoteModel?

}