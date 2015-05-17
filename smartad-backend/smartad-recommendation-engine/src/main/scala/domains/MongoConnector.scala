package domains

import reactivemongo.api._
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by prayagupd
 * on 5/17/15.
 */

class MongoConnector extends DatabaseConnector {
  
  def connect() {

    // gets an instance of the driver
    // (creates an actor system)
    val driver = new MongoDriver
    val connection = driver.connection(List("localhost"))

    // Gets a reference to the database "recommendation"
    val db = connection("recommendation")

    // Gets a reference to the collection "movies"
    // By default, you get a BSONCollection.
    val collection = db("movies")
  }
}
