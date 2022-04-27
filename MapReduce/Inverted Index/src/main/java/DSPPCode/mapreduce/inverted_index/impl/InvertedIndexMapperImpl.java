package DSPPCode.mapreduce.inverted_index.impl;

import DSPPCode.mapreduce.inverted_index.question.InvertedIndexMapper;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;



public class InvertedIndexMapperImpl extends InvertedIndexMapper {

  @Override
  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {

    // 读取停用词
    URI uri = context.getCacheFiles()[0];
    FileSystem fs = FileSystem.get(uri, new Configuration());
    BufferedReader reader = new BufferedReader(new InputStreamReader(fs.open(new Path(uri))));

    List<String> stopwords = new ArrayList<>();
    String content;
    while ((content = reader.readLine()) != null) {
      stopwords.add(content);
    }

    // 读取单词
    String[] words = value.toString().toLowerCase().split(" ");

    // 读取文件名
    //FileSplit split = (FileSplit) context.getInputSplit();
    split = (FileSplit) context.getInputSplit();
    String path_str = split.getPath().toString();
    String[] paths = path_str.split("/");
    String path = paths[paths.length - 1];
    //System.out.println(path_str);

    // 去除停用词
    for (String word : words) {
      int flag = 0;
      for (String str : stopwords) {
        if (word.equals(str)) {
          flag = 1;
          break;
        }
      }

      if (flag == 1) {
        //System.out.println(word);
        continue;
      }
      //System.out.println(path);
      context.write(new Text(word), new Text(path));
    }
  }
}
