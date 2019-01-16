package app.api

import app.api.route.CarRoute
import app.api.route.PilotRoute
import spark.Spark.*

class ApiController {

    companion object {
        const val RES_TYPE_JSON = "application/json"
    }

    private val pilotRoute = PilotRoute()
    private val carRoute = CarRoute()

    init {
        get("/") { _, _ -> renderWelcomeText() }

        path("/api") {
            setupConfigs()
            setupApi()
            setupPaths()
            setupErrors()
        }
    }

    private fun setupConfigs() {
        before("*") {  _, _ -> checkAuthenticate() }
        after("*") { _, res -> res.type(RES_TYPE_JSON) }
    }

    private fun checkAuthenticate() {
        val isAuthenticated = true
        if (!isAuthenticated) {
            halt(401, "Authentication failed")
        }
    }

    private fun setupApi() {
        get("") { _, _ -> renderApiJson() }
    }

    private fun setupPaths() {
        path("/pilots") { pilotRoute.getRoutes() }
        path("/cars") { carRoute.getRoutes() }
    }

    private fun setupErrors() {
        notFound(renderNotFoundJson())
        internalServerError(renderServerErrorJson())
    }

    private fun renderWelcomeText() = "Welcome to Back Your Future!"

    private fun renderApiJson() = "{ \"text\": \"Welcome to my api! You are authenticated.\" }"

    private fun renderNotFoundJson() = "{ \"error\": 404, \"text\": \"Oops, the DeLoran is not here!\" }"

    private fun renderServerErrorJson() = "{ \"error\": 500, \"text\": \"Aouch! You broke the space-time continuum...\" }"
}
