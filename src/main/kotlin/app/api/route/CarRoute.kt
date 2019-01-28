package app.api.route

import app.api.dao.CarDao
import app.api.model.CarModel

class CarRoute : BaseRoute<CarDao, CarModel>(CarDao())