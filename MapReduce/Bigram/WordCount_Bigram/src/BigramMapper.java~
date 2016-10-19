import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class BigramMapper extends Mapper<LongWritable, Text,Text,IntWritable> {
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	static int cnt = 0;
	List<String> ls = new ArrayList<String>();
	
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		StringTokenizer dt = new StringTokenizer(value.toString(), " ");
		System.out.println("______________________"+value.toString());
		while (dt.hasMoreTokens()) {
			ls.add(dt.nextToken());
		}
	
	}
	@Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
		int va = Integer.parseInt(context.getConfiguration().get("N-grams"));
	//	System.out.println("++++++++++++++++++++++++++++++"+ls.toString());
		StringBuffer str = new StringBuffer("");
		for (int i = 0; i <= ls.size() - va; i++) {
			int k=i;
			
			for(int j=0;j<va;j++) {
				if(j>0) {
					str = str.append(" ");
					str = str.append(ls.get(k));
				/*	System.out.println("++++++++++++++"+k);
					System.out.println("++++++++++++++"+j);
					System.out.println("++++++++++++++ LS "+str.toString()); */
					
				} else 
				{
					str = str.append(ls.get(k));
					
				}
				k++;
			
			}
			word.set(str.toString());
			str=new StringBuffer("");
			//one.set(ls.size());
			context.write(word, one);
		}
    }
}