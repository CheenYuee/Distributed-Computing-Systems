package DSPPCode.mapreduce.student_info.question;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;


public abstract class StudentInfoMapper extends Mapper<Object, Text, Text, IntWritable> {

  /**
   * TODO 请完成该方法
   * <p>
   * 输入:
   * <p>
   * 每个 value 代表一名学生的序号、性别以及身高信息, 行内由 "," 隔开, 第一个代表序号, 第二个代表性别，第三个代表身高信息.
   * <p>
   * 如 1,F,170 代表 序号是1的学生, 性别为女, 身高170.
   * <p>
   */
  @Override
  public abstract void map(Object key, Text value, Context context)
      throws IOException, InterruptedException;

}
