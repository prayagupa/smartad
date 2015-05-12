import org.apache.hadoop.io.{MapWritable, DoubleWritable, IntWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import scala.util.control.Breaks._

/**
 * This class performs the map operation, translating raw input into the key-value
 * pairs we will feed into our reduce operation.
 * @author prayagupd
 * @date 05-11-2015
 */


class PredictionStripesMapper extends Mapper[Object,Text,IntWritable,CustomMapWritable] {
  val one = new DoubleWritable(1.0)
  val mutableMap = scala.collection.mutable.Map[Int, scala.collection.mutable.Map[Int, Int]]()

  override
  def map(key:Object, value:Text, context:Mapper[Object,Text,IntWritable,CustomMapWritable]#Context) = {
    val tokens = value.toString().split("\\s")

    for (token <- 0 until tokens.length - 1) {
      val token_ = tokens(token).toInt

      breakable {
        for (nextToken <- token + 1 until tokens.length) {
          val nextToken_ = tokens(nextToken).toInt
          if (tokens(token).equals(tokens(nextToken))) {
            break
          }
          if (mutableMap.contains(token_)) {
            if(!mutableMap.get(token_).isEmpty && mutableMap.get(token_).get.contains(nextToken_)){
              val increment = mutableMap.get(token_).get.getOrElse(nextToken_, 0)+1
              mutableMap.getOrElse(token_, scala.collection.mutable.Map[Int, Int]().empty) += nextToken_ -> increment
            } else {
              val map = mutableMap.getOrElse(token_, scala.collection.mutable.Map[Int, Int]().empty)
              map+=nextToken_ -> 1
            }
          } else {
            val map = scala.collection.mutable.Map(nextToken_ -> 1)
            mutableMap += token_ -> map
          }

          mutableMap.foreach { case (k,v) =>
            val val_ = new CustomMapWritable()
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

