package ali.baha.data.remote

import ali.baha.data.remote.model.RemotePostModel
import ali.baha.domain.model.ServerDrivenUiEntity
import com.google.gson.JsonObject

interface RemoteService {

    suspend fun getServerDrivenUiData(): ServerDrivenUiEntity?

    suspend fun getClickData(
        url: String,
        jsonObject: JsonObject
    ): RemotePostModel?
}