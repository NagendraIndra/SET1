package com.inv;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	int i=0;
	
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		System.out.println("++++++++++++Inside MAP SETUP=="+i);
		i=i+1;
	}
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
				
		System.out.println("++++++++++++Inside MAP MAP method=="+i);
		i=i+1;
		
		String str=value.toString();
		StringTokenizer values=new StringTokenizer(str);
		
		while(values.hasMoreTokens()){
			String val=values.nextToken();
			
			context.write(new Text(val), new IntWritable(1));
		}
		
	}

	@Override
	protected void cleanup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		System.out.println("++++++++++++Inside MAP Cleanup method=="+i);
	}
	

}
