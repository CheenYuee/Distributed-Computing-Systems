package DSPPCode.mapreduce.crashed_sql.question;

import java.io.*;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


abstract public class SQLMapper extends Mapper<Object, Text, Text, LongWritable> {

  /**
   * TODO 请完成该抽象方法
   * -
   * 输入：
   * 每行为一条订单记录，第一列到最后一列分别表示订单Id，用户名，购买产品，购买数量，订单日期，每列之间用制表符分隔
   * 如：u00001 rookie	文化衫  45   20    2021-6-11 表示订单号为u00001，其中记录了rookie用户在2021-6-11购买了20件单价为45元的文化衫
   */
  abstract public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException;

}

