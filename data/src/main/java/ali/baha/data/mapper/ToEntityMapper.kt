package ali.baha.data.mapper

import ali.baha.data.model.Data
import ali.baha.domain.model.ServerDrivenUiEntity
import java.util.ArrayList

interface ToEntityMapper {
    fun mapFromEntity(model: ArrayList<Data>): ServerDrivenUiEntity
}