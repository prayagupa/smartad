
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

/*
 *
 * @author : prayagupd
 */

object WordCountApp {
  def main(args: Array[String]) {
    val logFile = "src/main/resources/clientdata.txt" // Should be some file on your system
    val sc = new SparkContext("local", "Spark Wordcount", "/usr/local/spark-1.1.1", List("target/scala-2.10/smartad-spark-wordcount_2.10-1.0.jar"))
    println ("=======================================================")
    println ("counting 'the' in lines" + sc)
    println ("=======================================================")
    val bigData     = sc.textFile(logFile, 2).cache()
    val countOfThes = bigData.filter(line => 
		                        line.contains("the")
	              ).count()
    println ("=======================================================")
    println("Lines with the: %s".format(countOfThes))
    println ("=======================================================")
  }
}
