package DSPPCode.mapreduce.common_pagerank.impl;

import DSPPCode.mapreduce.common_pagerank.question.PageRankJoinReducer;
import DSPPCode.mapreduce.common_pagerank.question.utils.ReduceJoinWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;

public class PageRankJoinReducerImpl extends PageRankJoinReducer {

  @Override
  public void reduce(Text key, Iterable<ReduceJoinWritable> values, Context context)
      throws IOException, InterruptedException {
    String data_ranks = null;
    String data_pages = null;

    for (ReduceJoinWritable value : values)
    {
      //System.out.println(value.getTag());
      if(value.getTag().equals(ReduceJoinWritable.PAGERNAK))
      {
        // System.out.println(value.getTag());
        data_ranks = value.getData();
      }
      if(value.getTag().equals(ReduceJoinWritable.PAGEINFO))
      {
        // System.out.println(value.getTag());
        data_pages = value.getData();
        //System.out.println(data_pages);
      }
    }

    assert data_pages != null;
    String[] pages = data_pages.split(" ");
    String tmp = "";
    for (int i=1; i < pages.length; i++)
    {
      tmp = tmp  + pages[i];
      if(i < pages.length - 1)
      {
        tmp = tmp + " ";
      }
    }


    String output_key;
    if(tmp.equals(""))
    {
      output_key = data_ranks;
    }else {
      output_key = data_ranks + " " + tmp;
    }

    // System.out.println(output_key);

    context.write(new Text(output_key), NullWritable.get());
  }
}
