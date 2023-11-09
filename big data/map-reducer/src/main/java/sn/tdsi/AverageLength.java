package sn.tdsi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AverageLength {
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
