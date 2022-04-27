package DSPPTest.student.mapreduce.crashed_sql;

import DSPPCode.mapreduce.crashed_sql.question.SQLRunner;
import DSPPTest.student.TestTemplate;
import DSPPTest.util.Parser.ListParser;
import org.apache.hadoop.util.ToolRunner;
import org.junit.Test;

import static DSPPTest.util.FileOperator.deleteFolder;
import static DSPPTest.util.FileOperator.readFile2String;
import static DSPPTest.util.Verifier.verifyList;

public class SQLTest extends TestTemplate {

  @Test
  public void test() throws Exception {
    // 设置路径
    String inputPath = root + "/mapreduce/crashed_sql/input";
    String outputPath = outputRoot + "/mapreduce/crashed_sql";
    String outputFile = outputPath + "/part-r-00000";
    String answerFile = root + "/mapreduce/crashed_sql/answer";

    // 删除旧输出
    deleteFolder(outputPath);

    // 执行
    String[] args = {inputPath, outputPath};
    int exitCode = ToolRunner.run(new SQLRunner(), args);

    // 检验结果
    verifyList(readFile2String(outputFile), readFile2String(answerFile), new ListParser());

    System.out.println("恭喜通过~");
    System.exit(exitCode);
  }

}