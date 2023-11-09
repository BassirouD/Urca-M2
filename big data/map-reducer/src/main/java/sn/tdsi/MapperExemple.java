package sn.tdsi;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class MapperExemple extends Mapper<Object, Text, Text, IntWritable> {
    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer str = new StringTokenizer(value.toString());
        if (str.hasMoreTokens()) {
            String word = str.nextToken();
            context.write(new Text("length"), new IntWritable(word.length()));
            context.write(new Text("count"), new IntWritable(1));
        }
    }
}
