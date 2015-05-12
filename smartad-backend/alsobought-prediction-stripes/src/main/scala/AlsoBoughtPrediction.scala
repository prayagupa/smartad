import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{MapWritable, DoubleWritable, IntWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.util.GenericOptionsParser

// This class configures and runs the job with the map and reduce classes we've
// specified above.

/**
 * @author prayagupd
 * @date 05-11-2015
 */

object AlsoBoughtPrediction {

  def main(args:Array[String]):Int = {
    val conf = new Configuration()
    val otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs
    if (otherArgs.length != 2) {
      println("Usage: alsobought <in> <out>")
      return 2
    }
    val job = new Job(conf, "People also bought suggestion")
    job.setJarByClass(classOf[PredictionStripesMapper])

    //mappers/reducers
    job.setMapperClass(classOf[PredictionStripesMapper])
    job.setOutputKeyClass(classOf[IntWritable])   //can throw RTException
    job.setOutputValueClass(classOf[MapWritable]) //can throw RTException

    //reducer
    //job.setCombinerClass(classOf[PredictionPairReducer])
    job.setReducerClass(classOf[PredictionStripesReducer])
    job.setOutputKeyClass(classOf[IntWritable])       //can throw RTException
    job.setOutputValueClass(classOf[MapWritable]) //can throw RTException

    //files
    FileInputFormat.addInputPath(job, new Path(args(0)))
    FileOutputFormat.setOutputPath(job, new Path((args(1))))
    if (job.waitForCompletion(true)) 0 else 1
  }

}
