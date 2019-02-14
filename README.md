# Back your future

## A CRUD REST API with [Heroku](https://www.heroku.com/) and [Spark](http://sparkjava.com/), and a free database with [mLab](https://mlab.com/) and [KMongo](https://litote.org/kmongo/).

This repository is a use-case of the following steps:

### Create a server-app on [Heroku](https://www.heroku.com/):

When logged, click on "Create a new app..."

<img src="https://cdn-images-1.medium.com/max/800/1*lFE7Rr6ajm-vjwWqunLtjg.png"/>

Set the remote repository for the project:

```bash
$ heroku git:remote -a name-of-the-heroku-app
```

### Create a database and a user database on [mLab](https://mlab.com/):

Get the MongoDB URI on database's dashboard, then set the keys in `MongoDb.kt` of the project:  

```java
private const val DB_USER = ""
private const val DB_PASS = ""
private const val DB_NAME = ""

private const val DB_URI = "mongodb://$DB_USER:$DB_PASS@ds00000.mlab.com:00000/$DB_NAME"
```
Mongo will automatically create the custom models (see [PilotModel](https://github.com/gitdefllo/back-your-future/blob/master/src/main/kotlin/app/api/model/PilotModel.kt) and [CarModel](https://github.com/gitdefllo/back-your-future/blob/master/src/main/kotlin/app/api/model/CarModel.kt)).

### Create the CRUD routes using Spark

[BaseRoute](https://github.com/gitdefllo/back-your-future/blob/master/src/main/kotlin/app/api/route/BaseRoute.kt) for generics routes, [PilotRoute](https://github.com/gitdefllo/back-your-future/blob/master/src/main/kotlin/app/api/route/PilotRoute.kt) and [CarRoute](https://github.com/gitdefllo/back-your-future/blob/master/src/main/kotlin/app/api/route/CarRoute.kt) for specific ones.

```java
get("") { req, res ->  }
get("/:id") { req, res ->  }
post("") { req, res ->  }
put("/:id") { req, res ->  }
delete("/:id") { req, res ->  }
```

### Create the DAO with KMongo:

[BaseDAO](https://github.com/gitdefllo/back-your-future/blob/master/src/main/kotlin/app/api/route/BaseRoute.kt) for main requests. Particular requests from [PilotDao](https://github.com/gitdefllo/back-your-future/blob/master/src/main/kotlin/app/api/dao/PilotDao.kt) or [CarDao](https://github.com/gitdefllo/back-your-future/blob/master/src/main/kotlin/app/api/dao/CarDao.kt).

```java
fun getAll(): String = getCollection().find().into(mutableListOf<T>()).toJson()
fun get(id: String): String = getCollection().findOneById(ObjectId(id)).toJson()
```

### Use [Postman](https://www.getpostman.com/) or [Insomnia](https://insomnia.rest/) to test the API

### :boom: 88m/h reached! 

<p align="center">
  <img src="https://media.giphy.com/media/xsF1FSDbjguis/giphy.gif" width="360" />
</p>

------

### Read [the story behind this repo](https://medium.com/@fllo/kotlin-back-your-future-2b5b6816e911)!

> Dr. Emmett Brown and Marty McFly are trapped in time.  
> They should go to the cloud to restart the DeLorean and get back to their timeline.   
> Will they succeed to easily create a server using Heroku, mLab, Spark and KMongo to go back home?  

------

### Dependencies:

```java
dependencies {
    compile 'org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.11'
    compile 'com.sparkjava:spark-kotlin:1.0.0-alpha'
    compile 'org.slf4j:slf4j-simple:1.7.12' // logger for Spark
    compile 'org.litote.kmongo:kmongo:3.9.0'
    compile 'com.google.code.gson:gson:2.2.4'
}
```
