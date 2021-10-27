package ali.baha.data.remote

import com.google.gson.JsonObject
import javax.inject.Inject


class RemoteServiceImpl @Inject constructor(private val remoteHelper: RemoteHelper) :
    RemoteService {

    override suspend fun getServerDrivenUiData() = remoteHelper.getServerDrivenUiData()

    override suspend fun getClickData(url: String, jsonObject: JsonObject): String =
        remoteHelper.getClickData(url, jsonObject)

}