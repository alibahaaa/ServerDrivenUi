package ali.baha.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePostModel(
    @SerializedName("username")
    val username: String = ""
)