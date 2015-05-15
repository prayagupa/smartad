
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.log4j.Logger

/*
 * https://spark.apache.org/examples.html
 * @author : prayagupd
 */

object SongPlayCountApp {
//  def main(args: Array[String]) {
//    val logFile = "src/main/resources/clientdata.txt" // Should be some file on your system
//
//    val conf           = new SparkConf().setAppName("Smartad Spark SongPlaycount")
//                                        .setMaster("spark://localhost:7077")
//    val sc             = new SparkContext(conf)
//    val logData        = sc.textFile(logFile, 2).cache()
//    val countOfThes    = logData.filter(line => line.contains("Vienna")).count()
//    val countOfPrayags = logData.filter(line => line.contains("The Warm Familiar Smell Of September")).count()
//
//    println ("=======================================================")
//    println("Lines with The: %s, Lines with Prayag: %s".format(countOfThes, countOfPrayags))
//    println ("=======================================================")
//  }

  def main(arg: Array[String]) {

    var logger = Logger.getLogger(this.getClass())

    if (arg.length < 2) {
      logger.error("=> wrong parameters number")
      System.err.println("Usage: SongPlayCountApp <path-to-files> <output-path>")
      System.exit(1)
    }

    val jobName = "SongPlayCountApp"

    val conf = new SparkConf().setAppName(jobName)
    val sc = new SparkContext(conf)

    val pathToFiles = arg(0)
    val outputPath = arg(1)

    logger.info("=> jobName \"" + jobName + "\"")
    logger.info("=> pathToFiles \"" + pathToFiles + "\"")

    val files = sc.textFile(pathToFiles)

    // do your work here
    val rowsWithoutSpaces = files.map(_.replaceAll(" ", ","))

    // and save the result
    rowsWithoutSpaces.saveAsTextFile(outputPath)

  }
}
