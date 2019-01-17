package app.api.route

import app.api.dao.CarDao
import app.api.entity.CarEntity

class CarRoute : BaseRoute<CarEntity>(CarDao())