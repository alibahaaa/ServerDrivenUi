package ali.baha.data.remote

import ali.baha.data.remote.model.RemotePostModel
import ali.baha.domain.model.ServerDrivenUiEntity
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface RemoteHelper {
    @GET("server_driven_ui/type5/")
    suspend fun getServerDrivenUiData(): ServerDrivenUiEntity?

    @POST
    suspend fun getClickData(
        @Url url : String,
        @Body jsonObject:JsonObject
    ) : RemotePostModel?
}