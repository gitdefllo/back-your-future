package app.api.dao

import app.api.model.PilotModel
import org.litote.kmongo.MongoOperator.lt

class PilotDao : BaseDao<PilotModel>() {

    fun getMinorPilots(): String = getCollection()
            .find(PilotModel::age lt 18)
            .into(mutableListOf<PilotModel>())
            .toJson()
}