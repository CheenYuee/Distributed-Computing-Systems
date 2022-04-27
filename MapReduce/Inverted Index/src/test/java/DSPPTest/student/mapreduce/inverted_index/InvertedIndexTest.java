package DSPPTest.student.mapreduce.inverted_index;

import DSPPCode.mapreduce.inverted_index.question.InvertedIndexRunner;
import DSPPTest.student.TestTemplate;
import DSPPTest.util.Parser.KVListParser;
import DSPPTest.util.Parser.ListParser;
import org.junit.Test;

import static DSPPTest.util.FileOperator.deleteFolder;
import static DSPPTest.util.FileOperator.readFile2String;
import static DSPPTest.util.Verifier.verifyKV;
import static DSPPTest.util.Verifier.verifyList;

public class InvertedIndexTest extends TestTemplate {

    @Test(timeout = 8000)
    public void test() throws Exception {
        //set dir
        String inputFolder = root + "/mapreduce/inverted_index/input";
        String stopwordPath = root + "/mapreduce/inverted_index/stopwords.txt";
        String outputFolder = outputRoot + "/mapreduce/inverted_index";
        String outputFile = outputFolder + "/part-r-00000";
        String answerFile = root + "/mapreduce/inverted_index/answer";

        //delete old dirl
        deleteFolder(outputFolder);

        //do
        InvertedIndexRunner.run(inputFolder, stopwordPath, outputFolder);

        verifyKV(readFile2String(outputFile), readFile2String(answerFile), new KVListParser(";"));
        System.out.println("恭喜通过~");
    }
}
