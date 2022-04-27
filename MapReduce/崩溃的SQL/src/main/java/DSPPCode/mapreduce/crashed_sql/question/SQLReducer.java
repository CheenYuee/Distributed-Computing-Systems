package DSPPCode.mapreduce.crashed_sql.question;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

abstract public class SQLReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
  /**
   * TODO 请完成该抽象方法
   * -
   * 输出：
   * 将订单总额最高的5个订单，按其总金额从高到底输出，输出包含订单Id，用户名以及该订单总额
   * 如：u00001	rookie	900，表示订单号为u00001，用户为rookie,该订单的金额为900，每列之间用制表符分隔
   */
  abstract public void reduce(Text key, Iterable<LongWritable> values, Context context)
      throws IOException, InterruptedException;

}
