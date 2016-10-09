package com.inv;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class CustomPart extends Partitioner<Text, IntWritable>{

	@Override
	public int getPartition(Text key, IntWritable value, int noOfRed) {
		
		System.out.println("-------------------Inside Partitioner");
		
	char ch=	key.toString().toLowerCase().charAt(0);
		if(noOfRed==0){
			return 0;
		}
		if(ch>=97 && ch<110){
			return 0%noOfRed;
		}else if(ch>=110 && ch<123){
			return 1%noOfRed;
		}else {
			return 2%noOfRed;
		}
		
		
	}

	
	
}
