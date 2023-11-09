package sn.tdsi.bigdata;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class KMerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text kmer = new Text();

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        int k = 9; // Taille du 9-mer
        for (int i = 0; i <= line.length() - k; i++) {
            String kmerString = line.substring(i, i + k);
            kmer.set(kmerString);
            context.write(kmer, one);
        }
    }
}
