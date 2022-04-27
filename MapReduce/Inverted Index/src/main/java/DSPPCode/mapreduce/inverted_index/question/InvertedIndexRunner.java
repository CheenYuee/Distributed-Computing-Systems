package DSPPCode.mapreduce.inverted_index.question;

import DSPPCode.mapreduce.inverted_index.impl.InvertedIndexMapperImpl;
import DSPPCode.mapreduce.inverted_index.impl.InvertedIndexReducerImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class InvertedIndexRunner {

  public static void run(String inputFile, String stopwordPath, String outputFolder) throws Exception {
    Job job = Job.getInstance(new Configuration());
    job.setJarByClass(InvertedIndexRunner.class);

    job.setMapperClass(InvertedIndexMapperImpl.class);
    job.setReducerClass(InvertedIndexReducerImpl.class);
    job.addCacheFile(new Path(stopwordPath).toUri());
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(inputFile));
    FileOutputFormat.setOutputPath(job, new Path(outputFolder));
    job.waitForCompletion(false);
  }
}
