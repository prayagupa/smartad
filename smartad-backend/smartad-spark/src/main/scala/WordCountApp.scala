
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
/*
 *
 * @author : prayagupd
 */

object WordCountApp {
  def main(args: Array[String]) {
    val logFile = "src/main/resources/clientdata.txt" // Should be some file on your system

    val conf           = new SparkConf().setAppName("Smartad Spark Wordcount")
//                                        .setMaster("spark://localhost:7077")
    val sc             = new SparkContext(conf)
    val logData        = sc.textFile(logFile, 2).cache()
    val countOfThes    = logData.filter(line => line.contains("the")).count()
    val countOfPrayags = logData.filter(line => line.contains("prayag")).count()

    println ("=======================================================")
    println("Lines with The: %s, Lines with Prayag: %s".format(countOfThes, countOfPrayags))
    println ("=======================================================")
  }
}
