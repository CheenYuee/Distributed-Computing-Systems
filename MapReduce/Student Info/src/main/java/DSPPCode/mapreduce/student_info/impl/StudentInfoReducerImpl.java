package DSPPCode.mapreduce.student_info.impl;

import DSPPCode.mapreduce.student_info.question.StudentInfoReducer;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;

public class StudentInfoReducerImpl extends StudentInfoReducer {

  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {
    int num = 0;
    int sum = 0;
    double avg = 0;
    int max = 0;
    int min = 1000;
    for(IntWritable value : values)
    {
      int tmp = value.get();
      num++;
      sum += tmp;
      if(tmp > max)
      {
        max = tmp;
      }
      if(tmp < min)
      {
        min = tmp;
      }
    }
    avg = (double) sum / (double) num;

    String gender = key.toString();
    if(gender.equals("M"))
    {
      context.write(new Text(gender), new DoubleWritable(max - avg));
    }else{
      context.write(new Text(gender), new DoubleWritable(min - avg));
    }

  }
}
