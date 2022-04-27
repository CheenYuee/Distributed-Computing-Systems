package DSPPCode.mapreduce.crashed_sql.impl;

import DSPPCode.mapreduce.crashed_sql.question.SQLMapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SQLMapperImpl extends SQLMapper {

  public List<Integer> pay = new ArrayList<>();
  public List<String> lst = new ArrayList<>();

  @Override
  public void map(Object key, Text value, Context context) {

    String[] data = value.toString().split("\t");
    String order = data[0];
    String UserName = data[1];
    Integer price = Integer.valueOf(data[3]);
    Integer Number = Integer.valueOf(data[4]);
    int total = price * Number;
    // 构造字符串
    String output = order + "\t" + UserName + "\t" + total;
    lst.add(output);
    pay.add(total);

  }

  @Override
  protected void cleanup(Context context)
      throws IOException, InterruptedException {
    // 排序
    Collections.sort(pay);
    int x = -1;
    if (pay.size() > 5) {
      x = pay.get(pay.size() - 5);
    }
    //System.out.println(pay.get(pay.size() - 5));

    for (String str : lst) {
      String[] data = str.split("\t");
      int total = Integer.parseInt(data[2]);
      if (total >= x) {
        context.write(new Text(str), new LongWritable(total));
      }
    }
  }

}
