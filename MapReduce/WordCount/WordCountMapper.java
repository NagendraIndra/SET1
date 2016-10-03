
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		String str=value.toString();
		StringTokenizer tokens=new StringTokenizer(str);
		
		while(tokens.hasMoreTokens()){
			String str1=tokens.nextToken();
			context.write(new Text(str1), new IntWritable(1));
		}
		
	}
	
}









