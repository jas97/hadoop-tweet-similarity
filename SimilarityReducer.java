

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class SimilarityReducer extends Reducer<LongWritable, LongWritable, LongWritable, LongWritable> {
	
	private LongWritable result = new LongWritable();

	public void reduce(LongWritable key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
		int sum = 0;
		
		for (LongWritable val : values) {
			sum += val.get();
		} 
		
		result.set(sum);
		
		context.write(key, result);
	}
}
