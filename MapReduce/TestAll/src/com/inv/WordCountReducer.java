package com.inv;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
		protected void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			System.out.println("+++++++++INside SETUp Reducer");
		}
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		System.out.println("%%%%%%%%%%%%%INside reduce Reducer");
		
		int sum=0;
		for(IntWritable val:values){
			sum=sum+val.get();			
		}
		
		context.write(key, new IntWritable(sum));
		
	}
	
	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		System.out.println("^^^^^^^^^^^INside cleanup Reducer");
	}
	

}
