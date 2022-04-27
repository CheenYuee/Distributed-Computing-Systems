package DSPPCode.mapreduce.common_pagerank.impl;

import DSPPCode.mapreduce.common_pagerank.question.PageRankJoinMapper;
import DSPPCode.mapreduce.common_pagerank.question.utils.ReduceJoinWritable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import java.io.IOException;

public class PageRankJoinMapperImpl extends PageRankJoinMapper {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    // 读取文件
    FileSplit split = (FileSplit) context.getInputSplit();
    String path_str = split.getPath().toString();
    //String[] paths = path_str.split("/");
    //String path = paths[paths.length - 1];

    String[] data = value.toString().split(" ");

    ReduceJoinWritable output_value = new ReduceJoinWritable();
    output_value.setData(value.toString());

    if(path_str.contains("ranks"))
    {
      output_value.setTag(ReduceJoinWritable.PAGERNAK);
    }
    if(path_str.contains("pages"))
    {
      output_value.setTag(ReduceJoinWritable.PAGEINFO);
    }
    context.write(new Text(data[0]), output_value);

  }
}
