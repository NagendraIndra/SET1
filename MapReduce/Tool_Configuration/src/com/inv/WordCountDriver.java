package com.inv;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountDriver extends Configured implements Tool {

	@Override
	public int run(String[] arg0) throws Exception {
		//Configuration conf=new Configuration();
		Configuration conf=this.getConf();
		
		//conf.set("COLOR", "RED");
		//conf.set("mapreduce.job.reduces","5");
		
		
		
		Job job=Job.getInstance(conf, "Word Count Program");
		
		job.setJarByClass(WordCountDriver.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		
		job.setNumReduceTasks(3);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		
		//boolean status=job.waitForCompletion(true);
		//return status?0:1;
		return job.waitForCompletion(true)?0:1;
	}

	public static void main(String[] args) throws Exception {
		
		int status1=ToolRunner.run(new WordCountDriver(), args);
		
		System.exit(status1);
		
		
	}

}



















