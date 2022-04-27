package DSPPCode.mapreduce.inverted_index.question;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

abstract public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {
    /**
     * 预置的分隔符
     */
    protected static final String DELIMITER_COLON = ":";
    protected static final String DELIMITER_SEMI = ";";
    abstract public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException;
}
