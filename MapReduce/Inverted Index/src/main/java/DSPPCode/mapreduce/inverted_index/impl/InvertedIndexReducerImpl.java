package DSPPCode.mapreduce.inverted_index.impl;

import DSPPCode.mapreduce.inverted_index.question.InvertedIndexReducer;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class InvertedIndexReducerImpl extends InvertedIndexReducer {


  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {

    // 统计
    Map<String, Integer> map = new HashMap<>();
    for (Text value : values) {
      //System.out.println(value.toString());
      String address = value.toString();
      if (map.containsKey(address)) {
        map.put(address, map.get(address) + 1);
      } else {
        map.put(address, 1);
      }
    }

    Set<String> set = map.keySet();
    Iterator<String> it = set.iterator();
    // 输出
    StringBuilder output_value = new StringBuilder("");
    while (it.hasNext()) {
      String key_it = it.next();
      Integer value_it = map.get(key_it);
      //System.out.println(value_it);
      String tmp = key_it + DELIMITER_COLON + value_it.toString() + DELIMITER_SEMI;
      output_value.append(tmp);
    }
    //System.out.println(output_value.toString());
    //System.out.println(key);
    if(key.toString().length()>0)
    {
      context.write(key, new Text(output_value.toString()));
    }
  }
}
