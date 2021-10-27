package ali.baha.domain.model

import java.util.ArrayList

class ServerDrivenUiEntity {
    var data: ArrayList<DataEntity> = ArrayList()
}

data class DataEntity(
    var children: ArrayList<DataEntity> = ArrayList(),
    var topBar: ArrayList<DataEntity> = ArrayList(),
    var typeEntity: TypeEntity = TypeEntity.UNKNOWN,
    var value: String = "",
    var size: Int = 0,
)

enum class TypeEntity {
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