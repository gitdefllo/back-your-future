# Back your future

## Want a backend for your projects? Try this in Kotlin with [Spark](http://sparkjava.com/) and [KMongo](https://litote.org/kmongo/).

Platforms:

- [Heroku](https://www.heroku.com/)
- [mLab](https://mlab.com/)

Dependencies:

- [Spark](http://sparkjava.com/)
- [KMongo](https://litote.org/kmongo/)
- [SLF4J](https://www.slf4j.org/)
- [Gson](https://github.com/google/gson)

For the database, create one on [mLab](https://mlab.com/) and set the keys into `MongoDb.kt`:  

```java
private const val DB_USER = ""
private const val DB_PASS = ""
private const val DB_NAME = ""
private const val DB_URI = "mongodb://$DB_USER:$DB_PASS@ds00000.mlab.com:00000/$DB_NAME"
```
