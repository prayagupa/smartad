import org.apache.hadoop.io.{MapWritable, DoubleWritable, IntWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import scala.util.control.Breaks._

/**
 * This class performs the map operation, translating raw input into the key-value
 * pairs we will feed into our reduce operation.
 * @author prayagupd
 * @date 05-11-2015
 */


class PredictionStripesMapper extends Mapper[Object,Text,IntWritable,MapWritable] {
  val one = new DoubleWritable(1.0)
  val mutableMap = scala.collection.mutable.Map[Int, scala.collection.mutable.Map[Int, Int]]()

  override
  def map(key:Object, value:Text, context:Mapper[Object,Text,IntWritable,MapWritable]#Context) = {
    val tokens = value.toString().split("\\s")

    for (token <- 0 until tokens.length - 1) {
      breakable {
        for (nextToken <- token + 1 until tokens.length) {
          if (tokens(token).equals(tokens(nextToken))) {
            break
          }
          if (mutableMap.contains(token)) {
            if(!mutableMap.get(token).isEmpty && mutableMap.get(token).get.contains(nextToken)){
              val increment = mutableMap.get(token).get.getOrElse(nextToken, 0)+1
              mutableMap.getOrElse(token, scala.collection.mutable.Map[Int, Int]().empty) += nextToken -> increment
            } else {
              val map = mutableMap.getOrElse(token, scala.collection.mutable.Map[Int, Int]().empty)
              map+=nextToken -> 1
            }
          } else {
            val map = scala.collection.mutable.Map(nextToken -> 1)
            mutableMap += token -> map
          }

          mutableMap.foreach { case (k,v) =>
            val val_ = new MapWritable()
            v.foreach{ case (k1, v1) =>
                val_.put(new IntWritable(k1), new IntWritable(v1))
            }
            context.write(new IntWritable(k), val_)
          }
        }
      }
    }
  }
}

