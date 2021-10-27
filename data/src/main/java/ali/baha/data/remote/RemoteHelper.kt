package ali.baha.data.remote

import ali.baha.data.model.RemoteModel
import retrofit2.http.GET

interface RemoteHelper {

    @GET("server_driven_ui/type4/")
    suspend fun getServerDrivenUiData(): RemoteModel?

}