import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class BigramCount {

	public static void main(String[] args) throws Exception {
		

		Configuration conf = new Configuration();
		conf.set("N-grams", "2");
		Job job = Job.getInstance(conf, "BigramCount");
		// job.setNumReduceTasks(0);
		job.setJarByClass(BigramCount.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(BigramMapper.class);
		job.setReducerClass(BigramReducer.class);
		
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//Delete the folder if it already exists
		FileSystem fs = FileSystem.get(conf);
		/*Check if output path (args[1])exist or not*/
		if(fs.exists(new Path(args[1]))){
		   /*If exist delete the output path*/
		   fs.delete(new Path(args[1]),true);
		}
		
		System.exit(job.waitForCompletion(true)?0:1);
		System.out.println("*******************************************************Done.");

	}
	
}
