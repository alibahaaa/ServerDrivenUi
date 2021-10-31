package ali.baha.data.remote

import ali.baha.data.remote.model.RemotePostModel
import com.google.gson.JsonObject
import javax.inject.Inject


class RemoteServiceImpl @Inject constructor(private val remoteHelper: RemoteHelper) :
    RemoteService {

    override suspend fun getServerDrivenUiData() = remoteHelper.getServerDrivenUiData()

    override suspend fun getClickData(url: String, jsonObject: JsonObject): RemotePostModel? =
        remoteHelper.getClickData(url, jsonObject)

}