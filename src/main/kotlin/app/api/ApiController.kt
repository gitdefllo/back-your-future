package app.api

import app.api.route.CarRoute
import app.api.route.PilotRoute
import spark.Spark.*

class ApiController {

    companion object {
        const val RES_TYPE_JSON = "application/json"
    }

    init {
        get("/") { _, _ -> renderWelcome() }

        path("/api") {
            setConfigs()
            setApi()
            setPaths()
            setErrors()
        }
    }

    private fun setConfigs() {
        before("/*") {  _, _ -> checkAuthenticate() }
        after("/*") { _, res -> res.type(RES_TYPE_JSON) }
    }

    private fun checkAuthenticate() {
        val isAuthenticated = true
        if (!isAuthenticated) {
            halt(401, "Authentication failed")
        }
    }

    private fun setApi() {
        get("/") { _, _ -> renderApi() }
    }

    private fun setPaths() {
        path("/pilots") { PilotRoute().getRoutes() }
        path("/cars") { CarRoute().getRoutes() }
    }

    private fun setErrors() {
        notFound(renderNotFound())
        internalServerError(renderServerError())
    }

    private fun renderWelcome() = "<html><body><h1>Welcome travellers!</h1></body></html>"

    private fun renderApi() = "{ \"text\": \"Great Scott! You are authenticated.\" }"

    private fun renderNotFound() = "{ \"error\": 404, \"text\": \"Oops, the DeLoran is not here!\" }"

    private fun renderServerError() = "{ \"error\": 500, \"text\": \"Aouch! You broke the space-time continuum...\" }"
}
