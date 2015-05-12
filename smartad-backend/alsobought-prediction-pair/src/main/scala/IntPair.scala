
/**
 * author prayagupd
 * on 4/30/15.
 */

import java.io._
import java.util.Set
import java.lang.Integer
import org.apache.hadoop.io._

class IntPair (first : IntWritable, second : IntWritable) extends WritableComparable[IntPair] {

//      val first = first_
//      val second = second_

      def this() = this(first = new IntWritable(), second = new IntWritable())
      //def this() =  this(IntPair.init$default$1)

      @throws(classOf[IOException])
      @Override
      def write(out : DataOutput) = {
        first.write(out)
        second.write(out)
      }

      @throws(classOf[IOException])
      @Override
      def readFields(in : DataInput) = {
        first.readFields(in)
        second.readFields(in)
      }

      override
      def hashCode() : Int = {
         val h = first.hashCode() * 163 + second.hashCode()
         h
      }

      override
      def equals(o : Any) : Boolean = {
        if (o.isInstanceOf[IntPair]) {
          val tp = o.asInstanceOf[IntPair]
          return first.equals(tp.getFirst()) && second.equals(tp.getSecond())
        }
        false
      }

      @Override
      def compareTo(tp : IntPair) : Int = {
          val cmp : Int = first.compareTo(tp.getFirst())
          if (cmp != 0) {
            return cmp
          }
          second.compareTo(tp.getSecond())
      }

     override def toString() : String = {
        "( " + first + ", " + second + " ) "
     }

     def getFirst () : IntWritable = {
       first
     }

      def getSecond () : IntWritable = {
        second
      }
}