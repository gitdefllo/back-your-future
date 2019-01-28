package app.api.route

import app.api.dao.PilotDao
import app.api.model.PilotModel
import spark.Spark.get

class PilotRoute : BaseRoute<PilotDao, PilotModel>(PilotDao()) {

    override fun getRoutes() {
        super.getRoutes()

        get("/minors/") { _, _ -> dao.getMinorPilots() }
    }
}