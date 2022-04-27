package DSPPCode.mapreduce.common_pagerank.impl;

import DSPPCode.mapreduce.common_pagerank.question.PageRankReducer;
import DSPPCode.mapreduce.common_pagerank.question.PageRankRunner;
import DSPPCode.mapreduce.common_pagerank.question.utils.ReducePageRankWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import java.io.IOException;

public class PageRankReducerImpl extends PageRankReducer {
  private static final double D = 0.85;

  @Override
  public void reduce(Text key, Iterable<ReducePageRankWritable> values, Context context)
      throws IOException, InterruptedException {

    int totalPage = context.getConfiguration().getInt(PageRankRunner.TOTAL_PAGE, 0);
    int iteration = context.getConfiguration().getInt(PageRankRunner.ITERATION, 0);

    double data_ranks = 0;
    String data_pages = null;

    for (ReducePageRankWritable value : values)
    {
      if(value.getTag().equals(ReducePageRankWritable.PR_L))
      {
        data_ranks += Double.parseDouble(value.getData());
      }
      if(value.getTag().equals(ReducePageRankWritable.PAGE_INFO))
      {
        data_pages = value.getData();
      }
    }
    data_ranks = (1 - D) /totalPage + D * data_ranks;
    assert data_pages != null;
    String[] data = data_pages.split(" ");
    String output_key = key.toString() + " " + data_ranks;
    for(int i = 2; i < data.length; i++)
    {
      output_key += " ";
      output_key += data[i];
    }


    // 判断是否收敛
    double p1 = Double.parseDouble(data_pages.split(" ")[1]);
    double p2 = Double.parseDouble(output_key.split(" ")[1]);
    //System.out.println(p1);
    if(Math.abs(p2 - p1) < PageRankRunner.DELTA)
    {
      Counter counter = context.getCounter(PageRankRunner.GROUP_NAME,PageRankRunner.COUNTER_NAME);
      counter.increment(1);//增加计数
    }
    //System.out.println();
    context.write(new Text(output_key), NullWritable.get());
  }
}
