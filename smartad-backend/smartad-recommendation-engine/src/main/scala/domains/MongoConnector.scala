package domains

import play.api.libs.iteratee.Iteratee
import reactivemongo.api._
import reactivemongo.api.collections.default.BSONCollection
import reactivemongo.bson._
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Failure}

/**
 * Created by prayagupd
 * on 5/17/15.
 */

class MongoConnector extends DatabaseConnector {

  // gets an instance of the driver
  // (creates an actor system)
  val driver = new MongoDriver
  val connection = driver.connection(List("localhost"))

  // Gets a reference to the database "recommendation"
  val db = connection("recommendation")

  // Gets a reference to the collection "movies"
  // By default, you get a BSONCollection.
  val collection : BSONCollection = db("movies")

  override def connect(): Unit = {
    println(s"=============================================")
    println(s"connecting... ${db.name}/${collection.name}")
    println(s"=============================================")
  }

  override def insert(movie : BSONDocument ): Unit = {
    println(s"===================================================")
    println(s"inserting...${collection.name}")
    println(s"===================================================")

    val future = collection.insert(movie)

    future.onComplete {
      case Failure(e) => throw e
      case Success(lastError) => {
        println(s"successfully inserted document ${movie.get("id")} with lastError = ${lastError}")
      }
    }
  }

  override def list(name : String): List[Movie] = {
    val list : ListBuffer[Movie] = ListBuffer()

    val query = BSONDocument("id" -> BSONInteger(name.toInt))
    val cursor = collection.find(query).cursor[BSONDocument]
    val futureList :  Future[List[BSONDocument]] = cursor.collect[List]()
    futureList.map { list =>
      println("====================== list values =============================")
      list.foreach{ document =>
        println(" | " + BSONDocument.pretty(document))
      }
      //val m = new Movie()
      //list +=
    }
    list.toList
  }
}
