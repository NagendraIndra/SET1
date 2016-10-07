package com.inv;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	Configuration conf;
	
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		 conf=context.getConfiguration();
		 
		 System.out.println("++++++++No of Reducers is="+ conf.get("mapreduce.job.reduces")); 
		 
		 System.out.println("++++++++COLOR value is="+ conf.get("COLOR")  );
		//System.out.println("++++++++COLOR1 value is="+ conf.get("COLOR1")  );
	//	System.out.println("++++++++COLOR1 value is="+ conf.get("COLOR1","NOCOLOR")  );
		
		//System.out.println("++++++++Namenode address value is="+ conf.get("fs.defaultFS")  );
			
		 
	}
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		
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
		context.write(new Text("zzzzzzzzzzzzzzzzzzzzzzzz"), new IntWritable(1));
	}


}
