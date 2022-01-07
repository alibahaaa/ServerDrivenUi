package ali.baha.data.remote.service

import ali.baha.data.remote.model.RemotePostModel
import com.google.gson.JsonObject
import javax.inject.Inject


class RemoteHelperImpl @Inject constructor(private val remoteService: RemoteService) :
    RemoteHelper {

    override suspend fun getServerDrivenUiData() = remoteService.getServerDrivenUiData()

    override suspend fun getClickData(url: String, jsonObject: JsonObject): RemotePostModel? =
        remoteService.getClickData(url, jsonObject)

}