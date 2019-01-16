package app.api.database

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

object MongoDb {

    private const val DB_USER = ""
    private const val DB_PASS = ""
    private const val DB_NAME = ""
    private const val DB_URI = "mongodb://$DB_USER:$DB_PASS@ds00000.mlab.com:00000/$DB_NAME"

    private var kmongo: MongoClient? = null
    private var kdatabase: MongoDatabase? = null

    fun getDatabase(): MongoDatabase {
        if (kdatabase == null) {
            connect()
            database()
        }
        return kdatabase!!
    }

    private fun connect() {
        try {
            kmongo = KMongo.createClient(MongoClientURI(DB_URI))
            println("MongoClient connected")

        } catch (e: Exception) {
            kmongo?.close()
            println("MongoClient failed to connect: $e")
        }
    }

    private fun database() {
        kdatabase = kmongo?.getDatabase(DB_NAME)
    }
}