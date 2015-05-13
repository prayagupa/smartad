import org.apache.hadoop.io._
import org.apache.hadoop.mapreduce.Reducer

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * This class performs the reduce operation, iterating over the key-value pairs
 * produced by our map operation to produce a result. In this case we just
 * calculate a simple total for each word seen.
 * @author prayagupd
 * @date 05-11-2015
 */

class PredictionStripesReducer extends Reducer[IntWritable,CustomMapWritable, IntWritable, CustomMapWritable] {

  var counter : Int = 1

  override
  def reduce(key     : IntWritable,
             values  : java.lang.Iterable[CustomMapWritable],
             context : Reducer[IntWritable, CustomMapWritable,IntWritable, CustomMapWritable]#Context) = {
    println(s"================== start of reduce#${key} ${counter} =====================")

    var sum : Double = 0
    val neighboursGroupByKeys = scala.collection.mutable.Map[Int, Double]().empty
    val listBuffer = ListBuffer[CustomMapWritable]()
    for (value <- values) {
      listBuffer += value
      println("    | ================================")
      println(s"    | ${value}")
      value.foreach { case (k : IntWritable , v : IntWritable) =>
        println(s"    | (${key.get()}, (${k}, ${v}))")
        neighboursGroupByKeys += k.get() -> (neighboursGroupByKeys.getOrElse(k.get(), 0.0) + 1)
        sum += v.get()
      }
      println("    | ================================")
    }

    //val scalaValues = listBuffer.toList

    neighboursGroupByKeys.foreach{ case (k : Int, v : Double) =>
      println(s"    || ============== writing (${key}, (${k}, ${v}/${sum})) =====================")
      val freq = v / sum
      val map = new CustomMapWritable()
      map.put(new IntWritable(k), new DoubleWritable(freq))
//      println(s"    || ============== writing (${key}, (${k}, ${freq})) =====================")
      context.write(new IntWritable(key.get()), map)
    }

    println(s"================== end of reduce#${key} ${counter} =====================")
    counter = counter + 1
  }

  def neighbours(key : Int, scalaValues: List[CustomMapWritable]): mutable.Map[Int, Double] = {
    val neighboursGroupByKeys = scala.collection.mutable.Map[Int, Double]().empty

    scalaValues.foreach { map =>
      println(s"    || 1/${scalaValues.size} iterables => " + map)
      map.foreach{ case (k : IntWritable, v : IntWritable) =>
        val value = neighboursGroupByKeys.getOrElse(k.get(), 0.0) + v.get()
        neighboursGroupByKeys += k.get() -> value
      }
    }
    neighboursGroupByKeys
  }

  def total(writables: List[CustomMapWritable]): Double = {
    var sum : Int = 0
    writables.foreach{writable =>
      writable.foreach{case (k : IntWritable, v : IntWritable) =>
        sum += v.get()
      }
    }
    sum.toDouble
  }
}

