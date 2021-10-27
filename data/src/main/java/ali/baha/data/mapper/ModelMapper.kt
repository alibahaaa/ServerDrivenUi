package ali.baha.data.mapper

import ali.baha.data.model.Data
import ali.baha.data.model.RemoteModel
import ali.baha.data.model.Type
import ali.baha.domain.model.DataEntity
import ali.baha.domain.model.ServerDrivenUiEntity
import ali.baha.domain.model.TypeEntity
import javax.inject.Inject

class ModelMapper @Inject constructor() : ToEntityMapper {
    override fun mapFromEntity(model: ArrayList<Data>): ServerDrivenUiEntity {
        val serverDrivenUiEntity = ServerDrivenUiEntity()

        val serverDrivenUiDataEntityList : ArrayList<DataEntity> = ArrayList()
        val serverDrivenUiDataEntity  = DataEntity()
        model.forEach { m ->
            serverDrivenUiDataEntity.value = m.value
            serverDrivenUiDataEntity.size = m.size
            serverDrivenUiDataEntity.typeEntity = getType(m.type)
            serverDrivenUiDataEntity.topBar = mapFromEntity(m.topBar).data
            serverDrivenUiDataEntity.children = mapFromEntity(m.children).data

            serverDrivenUiDataEntityList.add(serverDrivenUiDataEntity)
        }

        serverDrivenUiEntity.data = serverDrivenUiDataEntityList
        return serverDrivenUiEntity
    }

    private fun getType(type: Type): TypeEntity {
        return when(type){
            Type.SCAFFOLD -> TypeEntity.SCAFFOLD
            Type.TEXT -> TypeEntity.TEXT
            Type.APP_BAR -> TypeEntity.APP_BAR
            Type.IMAGE -> TypeEntity.IMAGE
            Type.VERTICAL_LIST -> TypeEntity.VERTICAL_LIST
            Type.HORIZONTAL_LIST -> TypeEntity.HORIZONTAL_LIST
            Type.ROW -> TypeEntity.ROW
            Type.COLUMN -> TypeEntity.COLUMN
            Type.UNKNOWN -> TypeEntity.UNKNOWN
        }
    }
}