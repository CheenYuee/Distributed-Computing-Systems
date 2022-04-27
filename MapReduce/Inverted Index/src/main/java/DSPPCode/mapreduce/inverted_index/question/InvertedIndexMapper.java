package DSPPCode.mapreduce.inverted_index.question;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

abstract public class InvertedIndexMapper extends Mapper<Object, Text, Text, Text> {
    /**
     * 用于获取文件名
     */
    protected FileSplit split;
    /**
     * 预置的分隔符
     */
    protected static final String DELIMITER_COLON = ":";
    abstract public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException;
}

