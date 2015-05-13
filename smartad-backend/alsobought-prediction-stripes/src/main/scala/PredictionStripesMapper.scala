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
  var counter : Int = 1

  override
  def map(key:Object, value:Text, context:Mapper[Object,Text,IntWritable,CustomMapWritable]#Context) = {
    val tokens = value.toString().split("\\s")
    println(s"================== for map ${counter} =====================")
    for (tokenIndex <- 0 until tokens.length - 1) {
      val tokenValue = tokens(tokenIndex).toInt

      breakable {
        for (nextTokenIndex <- tokenIndex + 1 until tokens.length) {
          val nextTokenValue = tokens(nextTokenIndex).toInt
          if (tokens(tokenIndex).equals(tokens(nextTokenIndex))) {
            break
          }
          if (mutableMap.contains(tokenValue)) {
            if(mutableMap.get(tokenValue).get.contains(nextTokenValue)){
              val increment = mutableMap.get(tokenValue).get.getOrElse(nextTokenValue, 0)+1
              //println(s" | *============== (${tokenValue} contains (${nextTokenValue}, ${increment}})) =====================")
              mutableMap.getOrElse(tokenValue, scala.collection.mutable.Map[Int, Int]().empty) += nextTokenValue -> increment
            } else {
              //println(s" | #============== add (${tokenValue} , (${nextTokenValue}, 1)) =====================")
              val map = mutableMap.getOrElse(tokenValue, scala.collection.mutable.Map[Int, Int]().empty)
              map+=nextTokenValue -> 1
            }
          } else {
            val map = scala.collection.mutable.Map(nextTokenValue -> 1)
            mutableMap += tokenValue -> map
          }

        }
      }
    }
    //println(s"================== end of map ${counter} =====================")
    counter = counter + 1
  }

  override
  def cleanup(context : Mapper[Object, Text, IntWritable, CustomMapWritable]#Context) = {
    //println(s"## ================== start context ${counter} ===================== ##")
    mutableMap.foreach { case (mainProduct,neighbours) =>
      println(s" | #============== add (${mainProduct} , *, *)) =====================")
      val neighbourMap = new CustomMapWritable()
      neighbours.foreach { case (k1, v1) =>
        println(s" | #============== associate (${mainProduct} , [(${k1}, ${v1})]) =====================")
        neighbourMap.put(new IntWritable(k1), new IntWritable(v1))
      }
      println(s" | #============== associate ${neighbourMap}=====================")
      context.write(new IntWritable(mainProduct), neighbourMap)
    }
  }
}

