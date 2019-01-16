package app

import app.api.ApiController
import spark.kotlin.port

object Main {

    @JvmStatic fun main(args: Array<String>) {
        setPortApi()
        setControllerApi()
    }

    private fun setPortApi() {
        System.getenv("PORT")?.let {
            port(it.toInt())
        }
    }

    private fun setControllerApi() {
        ApiController()
    }
}