import org.apache.spark.SparkContext
import SparkContext._

/**
 * Created by prayagupd
 * on 5/16/15.
 * Spark port of http://blog.echen.me/2012/02/09/movie-recommendations-and-more-via-mapreduce-and-scalding/
 * Uses movie ratings data from MovieLens 100k dataset found at http://www.grouplens.org/node/73
 */

object RecommendationApiController {

  def main(args: Array[String]) {
    val master = args(0)
    val arg1 = args(1) // "Star Wars (1977)"

    val sc = new SparkContext(master, "Recommendation App")
    val recommender = new RecommendationApiService(sc)
    recommender.getRecommendations(arg1)

    println("============== complete ==================")
  }

}
