
/**
 * author prayagupd
 * on 4/30/15.
 */

import java.io._
import java.util.Set
import java.lang.Integer
import org.apache.hadoop.io._

class IntPairWritable (first : IntWritable, second : IntWritable) extends WritableComparable[IntPairWritable] {

//      val first = first_
//      val second = second_

      def this() = {
        this(first = new IntWritable(), second = new IntWritable())
      }

      @throws(classOf[IOException])
      @Override
      def write(dataOutput : DataOutput) = {
        first.write(dataOutput)
        second.write(dataOutput)
      }

      @throws(classOf[IOException])
      @Override
      def readFields(dataInput : DataInput) = {
        first.readFields(dataInput)
        second.readFields(dataInput)
      }

      override
      def hashCode() : Int = {
         val h = first.hashCode() * 163 + second.hashCode()
         h
      }

      override
      def equals(intPairWritableObject : Any) : Boolean = {
        if (intPairWritableObject.isInstanceOf[IntPairWritable]) {
          val intPair = intPairWritableObject.asInstanceOf[IntPairWritable]
          return first.equals(intPair.getFirst()) && second.equals(intPair.getSecond())
        }
        false
      }

      @Override
      def compareTo(intPair : IntPairWritable) : Int = {
          val compareToResult : Int = first.compareTo(intPair.getFirst())
          if (compareToResult != 0) {
            return compareToResult
          }
          second.compareTo(intPair.getSecond())
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