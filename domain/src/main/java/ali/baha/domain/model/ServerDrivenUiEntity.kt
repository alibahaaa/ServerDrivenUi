package ali.baha.domain.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class ServerDrivenUiEntity {
    @SerializedName("data")
    val data: ArrayList<Data> = ArrayList()
}

data class Data(
    @SerializedName("children") val children: ArrayList<Data> = ArrayList(),
    @SerializedName("top_bar") val topBar: ArrayList<Data> = ArrayList(),
    @SerializedName("type") var type: Type = Type.UNKNOWN,
    @SerializedName("value") var value: String = "",
    @SerializedName("input") var input: String = "",
    @SerializedName("size") val size: Int = 0,
    @SerializedName("action") val action: Action? = null,
)

data class Action(
    @SerializedName("url") var url: String = "",
    @SerializedName("method") var method: String = "",
    @SerializedName("bodies") val bodies: ArrayList<String> = ArrayList(),
    @SerializedName("queries") val queries: ArrayList<String> = ArrayList(),
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
    EDIT_TEXT,
    UNKNOWN
}