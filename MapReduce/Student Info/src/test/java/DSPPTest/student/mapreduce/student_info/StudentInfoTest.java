package DSPPTest.student.mapreduce.student_info;

import DSPPCode.mapreduce.student_info.question.StudentInfoRunner;
import DSPPTest.student.TestTemplate;
import DSPPTest.util.Parser.KVParser;
import org.apache.hadoop.util.ToolRunner;
import org.junit.Test;

import static DSPPTest.util.FileOperator.deleteFolder;
import static DSPPTest.util.FileOperator.readFile2String;
import static DSPPTest.util.Verifier.verifyKV;

public class StudentInfoTest extends TestTemplate {

  @Test
  public void test() throws Exception {
    // 设置路径
    String inputPath = root + "/mapreduce/student_info/input";
    String outputPath = outputRoot + "/mapreduce/student_info";
    String outputFile = outputPath + "/part-r-00000";
    String answerFile = root + "/mapreduce/student_info/answer";

    // 删除旧输出
    deleteFolder(outputPath);

    // 执行
    String[] args = {inputPath, outputPath};
    int exitCode = ToolRunner.run(new StudentInfoRunner(), args);

    // 检验结果
    verifyKV(readFile2String(outputFile), readFile2String(answerFile), new KVParser("\t"));
    System.out.println("恭喜通过~");
    System.exit(exitCode);
  }

}
