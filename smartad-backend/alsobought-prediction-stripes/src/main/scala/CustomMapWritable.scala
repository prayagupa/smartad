import org.apache.hadoop.io.{MapWritable, Writable, WritableComparable, IntWritable}
import scala.collection.JavaConversions._

/**
 * Created by prayagupd
 * on 5/12/15.
 */

class CustomMapWritable extends MapWritable {

  //def this() = this()

  override
  def toString() : String = {
    val keys = this.keySet().toList
    var string = ""
    for(writable <- keys) {
      string+=s"(${writable}, ${this.get(writable)}) ,"
    }
    string = string.substring(0, string.length-1)

    string
  }
}
