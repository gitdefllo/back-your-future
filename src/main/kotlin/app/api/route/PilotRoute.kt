package app.api.route

import app.api.dao.PilotDao
import app.api.entity.PilotEntity

class PilotRoute : BaseRoute<PilotEntity>(PilotDao())