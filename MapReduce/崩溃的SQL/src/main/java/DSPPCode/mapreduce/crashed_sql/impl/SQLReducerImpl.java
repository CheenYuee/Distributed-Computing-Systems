package DSPPCode.mapreduce.crashed_sql.impl;

import DSPPCode.mapreduce.crashed_sql.question.SQLReducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SQLReducerImpl extends SQLReducer {

  public List<Integer> pay = new ArrayList<>();
  public List<String> lst = new ArrayList<>();

  @Override
  public void reduce(Text key, Iterable<LongWritable> values, Context context) {
    String str = key.toString();
    String[] data = str.split("\t");
    int total = Integer.parseInt(data[2]);
    lst.add(str);
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

    // 排序
    lst.sort(new Comparator<String>() {
      public int compare(String entry1, String entry2) {
        int x = Integer.parseInt(entry1.split("\t")[2]);
        int y = Integer.parseInt(entry2.split("\t")[2]);
        return y - x;
      }
    });

    for (String str : lst) {
      String[] data = str.split("\t");
      int total = Integer.parseInt(data[2]);
      // System.out.println(total);
      if (total >= x) {
        // System.out.println(str);
        context
            .write(new Text(data[0] + "\t" + data[1]), new LongWritable(Integer.parseInt(data[2])));
      }
    }
  }
}
