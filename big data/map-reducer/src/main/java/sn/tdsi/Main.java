package sn.tdsi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, "word average length");
        job.setJarByClass(AverageLength.class);
        job.setMapperClass(MapperExemple.class);
        job.setReducerClass(ReducerExemple.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
        average(new Path(args[1]));
    }

    private static void average(Path outDir) throws IOException {
        FileSystem fs = FileSystem.get(new Configuration());
        Path reduceFile = new Path(outDir, "part-r-00000");
        if (!fs.exists(reduceFile)) {
            return;
        }

        int count = 0, length = 0;
        BufferedReader in = new BufferedReader(new InputStreamReader(fs.open(reduceFile)));
        while (in != null && in.ready()) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            String key = st.nextToken();
            String value = st.nextToken();
            if (key.equals("count")) {
                count = Integer.parseInt(value);
            } else if (key.equals("length")) {
                length = Integer.parseInt(value);
            }
        }
        double average = (double) length / count;
        System.out.println("The mean is: " + average);
    }

}