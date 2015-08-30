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

class PredictionPairReducer extends Reducer[IntPairWritable,DoubleWritable, IntPairWritable,MapWritable] {

  val asterick = new IntWritable(-1)
  var sum : Double = _

  override
  def reduce(key:IntPairWritable, values:java.lang.Iterable[DoubleWritable], context:Reducer[IntPairWritable,DoubleWritable,IntPairWritable,MapWritable]#Context) = {

    if (key.getSecond().equals(asterick)) {
      sum = values.foldLeft(0.0) { (state, elem) =>
        state + elem.get()
      }
      val mapWritable = new MapWritable()
      mapWritable.put(key, new DoubleWritable(sum))
      context.write(key, mapWritable)
    } else {
      val each = values.foldLeft(0.0) {(state, elem) => state + elem.get}
      val mapWritable = new MapWritable()
      mapWritable.put(key, new DoubleWritable(each/sum))
      context.write(key, mapWritable)
    }
  }

}

