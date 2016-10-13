import java.io.IOException;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
 
public class BigramCount {
    public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException {
        if (args.length != 2) {
            System.err.println("Inavlid Command!");
            System.err.println("Usage: BigramCount <input type=\"text\" /> <output>");
            System.exit(0);
        }
 
        Configuration conf = new Configuration();
        conf.set("mapreduce.jobtracker.address", "local");
        conf.set("fs.defaultFS","file:///");
 
        Job job = Job.getInstance(conf, "Bigram WordCount");
 
        job.setJarByClass(BigramCount.class);
        job.setJobName("Word Count");
 
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
 
        job.setMapperClass(BigramMapper.class);
        job.setReducerClass(BigramReducer.class);
 
        job.setMapOutputKeyClass(TextPair.class);
        job.setMapOutputValueClass(IntWritable.class);
 
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
      //Delete the folder if it already exists
      		FileSystem fs = FileSystem.get(conf);
      		/*Check if output path (args[1])exist or not*/
      		if(fs.exists(new Path(args[1]))){
      		   /*If exist delete the output path*/
      		   fs.delete(new Path(args[1]),true);
      		}
 
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}