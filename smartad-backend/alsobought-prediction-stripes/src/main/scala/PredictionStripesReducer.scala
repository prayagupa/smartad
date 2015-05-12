import org.apache.hadoop.io.{MapWritable, DoubleWritable, IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer

import scala.collection.JavaConversions._

/**
 * This class performs the reduce operation, iterating over the key-value pairs
 * produced by our map operation to produce a result. In this case we just
 * calculate a simple total for each word seen.
 * @author prayagupd
 * @date 05-11-2015
 */

class PredictionStripesReducer extends Reducer[IntWritable,CustomMapWritable, IntWritable, CustomMapWritable] {

  val asterick = new IntWritable(-1)
  var sum : Double = 0

  override
  def reduce(key:IntWritable, values:java.lang.Iterable[CustomMapWritable], context:Reducer[IntWritable, CustomMapWritable,IntWritable, CustomMapWritable]#Context) = {

    values.foreach{ map =>
      map.foreach{ case (k : IntWritable, v : IntWritable) =>
        sum+=v.get()
      }
    }

    val each = 1
    val map = new CustomMapWritable()
    map.put(new IntWritable(1), new IntWritable(each))
    context.write(key, map)
  }
}

