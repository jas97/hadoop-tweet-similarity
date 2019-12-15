

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SimilarityMapper extends Mapper<LongWritable, Text, LongWritable, LongWritable> {
	
	private String input;
	private LongWritable one;
	
	public void setup(Context context) throws IOException, InterruptedException  {
		 Configuration c = context.getConfiguration();
	     input = c.get("input");
	     
	     one = new LongWritable(1);
	}
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		context.write(new LongWritable(DLDistance.computeDistance(input,  value.toString())), one);	
	}
	
}
 