package ali.baha.data.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class RemoteModel {
    @SerializedName("data")
    val data: ArrayList<Data> = ArrayList()
}

data class Data(
    @SerializedName("children") val children: ArrayList<Data> = ArrayList(),
    @SerializedName("top_bar") val topBar: ArrayList<Data> = ArrayList(),
    @SerializedName("type") var type: Type = Type.UNKNOWN,
    @SerializedName("value") var value: String = "",
    @SerializedName("size") val size: Int = 0,
)

enum class Type {
    SCAFFOLD,
    TEXT,
    APP_BAR,
    IMAGE,
    VERTICAL_LIST,
    HORIZONTAL_LIST,
    ROW,
    COLUMN,
    UNKNOWN
}