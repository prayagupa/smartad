/**
 * http://blog.echen.me/2011/10/24/winning-the-netflix-prize-a-summary/
 * Created by prayagupd
 * on 5/17/15.
 *************************
 * SIMILARITY MEASURES
 *************************
 */

object CorrelationMeasures {

  /**
   * http://en.wikipedia.org/wiki/Correlation_function
   * The correlation between two vectors A, B is
   *   cov(A, B) / (stdDev(A) * stdDev(B))
   *
   * This is equivalent to
   *   [n * dotProduct(A, B) - sum(A) * sum(B)] /
   *     sqrt{ [n * norm(A)^2 - sum(A)^2] [n * norm(B)^2 - sum(B)^2] }
   */
  def correlation(size : Double,
                  dotProduct : Double,
                  rating1Sum : Double,
                  rating2Sum : Double,
                  rating1NormSq : Double,
                  rating2NormSq : Double) = {

    val numerator = size * dotProduct - rating1Sum * rating2Sum
    val denominator = scala.math.sqrt(size * rating1NormSq - rating1Sum * rating1Sum) *
                      scala.math.sqrt(size * rating2NormSq - rating2Sum * rating2Sum)

    numerator / denominator
  }

  /**
   * Regularize correlation by adding virtual pseudocounts over a prior:
   *   RegularizedCorrelation = w * ActualCorrelation + (1 - w) * PriorCorrelation
   * where w = # actualPairs / (# actualPairs + # virtualPairs).
   */
  def regularizedCorrelation(size : Double,
                             dotProduct : Double,
                             ratingSum : Double,
                             rating2Sum : Double,
                             ratingNormSq : Double,
                             rating2NormSq : Double,
                             virtualCount : Double,
                             priorCorrelation : Double) = {

    val coorelationVal = correlation(size, dotProduct, ratingSum, rating2Sum, ratingNormSq, rating2NormSq)
    val w = size / (size + virtualCount)

    w * coorelationVal + (1 - w) * priorCorrelation
  }

  /**
   * The cosine similarity between two vectors A, B is
   *   dotProduct(A, B) / (norm(A) * norm(B))
   */
  def cosineSimilarity(dotProduct : Double,
                       ratingNorm : Double,
                       rating2Norm : Double) = {
    dotProduct / (ratingNorm * rating2Norm)
  }

  /**
   * The Jaccard Similarity between two sets A, B is
   *   |Intersection(A, B)| / |Union(A, B)|
   */
  def jaccardSimilarity(usersInCommon : Double, totalUsers1 : Double, totalUsers2 : Double) = {
    val union = totalUsers1 + totalUsers2 - usersInCommon
    usersInCommon / union
  }

}
