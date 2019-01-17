package app.api.dao

import app.api.database.MongoDb
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.mongodb.client.MongoCollection
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.util.KMongoUtil
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

open class BaseDao<T: Any> {

    fun getAll(): String = getCollection().find().into(mutableListOf<T>()).toJson()

    fun get(id: String): String = getCollection().findOneById(ObjectId(id)).toJson()

    fun save(body: String): Boolean = try {
        getCollection().save(body.toEntity())
        true
    } catch (e: Exception) {
        println("request save failed: $e")
        false
    }

    fun update(id: String, body: String): String? = try {
        getCollection().updateOneById(ObjectId(id), body.toEntity())
        get(id)
    } catch (e: Exception) {
        println("request update failed: $e")
        null
    }

    fun delete(id: String): Boolean = try {
        getCollection().deleteOneById(ObjectId(id))
        true
    } catch (e: Exception) {
        println("request delete failed: $e")
        false
    }

    private fun Any?.toJson() = toGson(this)

    private fun <R> toGson(r: R): String = GsonBuilder().setPrettyPrinting().create().toJson(r)

    private fun String?.toEntity(): T = Gson().fromJson(this)

    private fun Gson.fromJson(json: String?): T = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

    private fun getCollection(): MongoCollection<T> =
            getDaoEntityClass().let { k ->
                MongoDb.getDatabase().getCollection(
                        KMongoUtil.defaultCollectionName(k), k.java)
            }

    @Suppress("UNCHECKED_CAST")
    private fun getDaoEntityClass(): KClass<T>
            = ((this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>).kotlin
}