package com.inv;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

public class WordCountDriver {
	public static void main(String[] args) throws IOException {
		
		Configuration conf=new Configuration();
		
		Job job=Job.getInstance(conf, "WordCount Program");
		
		job.setJarByClass(WordCountDriver.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOUtpu
		
	}
	
}
