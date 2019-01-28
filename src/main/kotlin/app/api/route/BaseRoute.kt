package app.api.route

import app.api.dao.BaseDao
import spark.Spark.*

open class BaseRoute<T: BaseDao<R>, R: Any>(protected val dao: T) {

    open fun getRoutes() {
        get("") { _, _ -> dao.getAll() }

        get("/:id") { req, _ -> dao.get(req.params("id")) }

        post("") { req, _ -> dao.save(req.body()) }

        put("/:id") { req, _ -> dao.update(req.params("id"), req.body()) }

        delete("/:id") { req, _ -> dao.delete(req.params("id")) }
    }
}