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

class PredictionStripesReducer extends Reducer[IntWritable,MapWritable, IntWritable, MapWritable] {

  val asterick = new IntWritable(-1)
  var sum : Double = 1

  override
  def reduce(key:IntWritable, values:java.lang.Iterable[MapWritable], context:Reducer[IntWritable, MapWritable,IntWritable, MapWritable]#Context) = {

    if (key.equals(asterick)) {
      sum = 0
      val map = new MapWritable()
      map.put(new IntWritable(1), new IntWritable(1))
      context.write(key, map)
    } else {
      val each = 1
      val map = new MapWritable()
      map.put(new IntWritable(1), new IntWritable(each))
      context.write(key, map)
    }
  }
}

