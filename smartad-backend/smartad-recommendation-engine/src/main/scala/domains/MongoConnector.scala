package domains

import play.api.libs.iteratee.Iteratee
import reactivemongo.api._
import reactivemongo.api.collections.default.BSONCollection
import reactivemongo.bson._
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.control.Breaks
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
    val listBuffer : ListBuffer[Movie] = ListBuffer.empty[Movie]

    val query = BSONDocument("title" -> BSONString("//^"+name+"//")) //like
    val sortQ = BSONDocument("regularizedCorRelation" -> BSONInteger(1))
    val cursor = collection.find(query).sort(sortQ).options(QueryOpts().batchSize(10)).cursor[BSONDocument]
    val futureList :  Future[List[BSONDocument]] = cursor.collect[List]()

    implicit object MovieReader extends BSONReader[BSONValue, String] {
      def read(v: BSONValue) =
        v match {
          case oid: BSONObjectID => oid.stringify
          case BSONInteger(i) => i.toString
          case BSONLong(l) => l.toString
          case BSONDouble(d) => d.toString
        }
    }

    var counter : Int = 0
    futureList.map { movies =>
      println("====================== list * similar movies =============================")
      Breaks.breakable {
        movies.foreach { movie =>
          if (counter >= 10) {
            Breaks.break()
          }
          val title = movie.get("title").get
          val similarTitle = movie.get("similarTitle").get

          println(s"       | ${similarTitle}")

          //val m = new Movie(id.get.asInstanceOf[Int], similarId.get.asInstanceOf[String])
          //        listBuffer += movie
          counter = counter + 1
        }
      }
    }
    listBuffer.toList
  }
}
