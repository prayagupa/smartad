import org.apache.hadoop.io.{DoubleWritable, IntWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import scala.util.control.Breaks._

/**
 * This class performs the map operation, translating raw input into the key-value
 * pairs we will feed into our reduce operation.
 * @author prayagupd
 * @date 05-11-2015
 */


class PredictionPairMapper extends Mapper[Object,Text,IntPairWritable,DoubleWritable] {
  val one = new DoubleWritable(1.0)
  val asterick = new IntWritable(-1)

  override
  def map(key:Object, value:Text, context:Mapper[Object,Text,IntPairWritable,DoubleWritable]#Context) = {
    val tokens = value.toString().split("\\s")

    for (token <- 0 until tokens.length - 1) {
      breakable {
        for (nextToken <- token + 1 until tokens.length) {
          if (tokens(token).equals(tokens(nextToken))) {
            break
          }
          val pair         = new IntPairWritable(new IntWritable(tokens(token).toInt), new IntWritable(tokens(nextToken).toInt))
          val pairAsterick = new IntPairWritable(new IntWritable(tokens(token).toInt), asterick)
          context.write(pair, one)
          context.write(pairAsterick, one)
        }
      }
    }
  }
}

