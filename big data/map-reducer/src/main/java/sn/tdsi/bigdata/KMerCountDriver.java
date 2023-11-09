package sn.tdsi.bigdata;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class KMerCountDriver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "kmer count");

        job.setJarByClass(KMerCountDriver.class);
        job.setMapperClass(KMerMapper.class);
        job.setCombinerClass(KMerReducer.class);
        job.setReducerClass(KMerReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0])); // Chemin du fichier d'entrée
        FileOutputFormat.setOutputPath(job, new Path(args[1])); // Chemin du répertoire de sortie

        job.waitForCompletion(true);

        // Maintenant, vous pouvez trier les résultats en utilisant un autre job MapReduce ou un autre processus.
    }
}