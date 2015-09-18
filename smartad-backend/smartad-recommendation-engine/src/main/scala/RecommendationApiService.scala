import domains.{MongoConnector, Movie}
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import reactivemongo.bson.BSONDocument

/**
 * Created by prayagupd
 * on 5/16/15.
 */

class RecommendationApiService (@transient sc : SparkContext) extends Serializable {

  @transient val db = new MongoConnector()
  
  def getRecommendations(movieName : String): Unit = {
    val list : List[Movie] = db.list(movieName)
    println(s"similar movies ${list.size}")
    list.foreach { movie =>
      println(s"${movie.movieTitle}, ${movie.similarTitle}")
    }
  }

}
