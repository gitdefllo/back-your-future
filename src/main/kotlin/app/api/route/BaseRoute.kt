package app.api.route

import app.api.dao.BaseDao
import spark.Spark

open class BaseRoute<T: BaseDao<R>, R: Any>(protected val dao: T) {

    fun getRoutes() {
        Spark.get("") { _, _ -> dao.getAll() }

        Spark.get("/:id") { req, _ -> dao.get(req.params("id")) }

        Spark.post("") { req, _ -> dao.save(req.body()) }

        Spark.put("/:id") { req, _ -> dao.update(req.params("id"), req.body()) }

        Spark.delete("/:id") { req, _ -> dao.delete(req.params("id")) }
    }
}