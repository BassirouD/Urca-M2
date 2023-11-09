package sn.tdsi.bigdata;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class KMerSortMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text kmer = new Text();
    private IntWritable count = new IntWritable();
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] parts = line.split("\t");
        if (parts.length == 2) {
            kmer.set(parts[0]);
            count.set(Integer.parseInt(parts[1]));
            context.write(kmer, count);
        }
    }
}
