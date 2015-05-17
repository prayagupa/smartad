
/**
 * Created by prayagupd
 * on 5/16/15.
 */

import org.apache.spark.SparkContext
import SparkContext._

/**
 * A port of http://blog.echen.me/2012/02/09/movie-recommendations-and-more-via-mapreduce-and-scalding/
 * to Spark.
 * Uses movie ratings data from MovieLens 100k dataset found at http://www.grouplens.org/node/73
 */

object MovieSimilarities {

  def main(args: Array[String]) {

    /**
     * Spark programs require a SparkContext to be initialized
     */
    val master = args(0)
    val sc = new SparkContext(master, "MovieSimilarities")
    val recommender = new RecommendationFactory(sc)
    recommender.predict()
  }

}
