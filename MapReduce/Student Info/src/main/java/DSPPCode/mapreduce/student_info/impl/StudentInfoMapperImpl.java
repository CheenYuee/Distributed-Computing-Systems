package DSPPCode.mapreduce.student_info.impl;

import DSPPCode.mapreduce.student_info.question.StudentInfoMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;

public class StudentInfoMapperImpl extends StudentInfoMapper {

  @Override
  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {

    String[] info = value.toString().split(",");
    String gender = info[1];
    int height = Integer.parseInt(info[2]);
    // System.out.println(gender);
    // System.out.println(height);
    context.write(new Text(gender), new IntWritable(height));
  }
}
