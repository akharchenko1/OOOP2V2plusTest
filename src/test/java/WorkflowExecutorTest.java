import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import workflow.WorkflowExecutor;
import workflow.exceptions.ParsingException;
import workflow.exceptions.WorkflowException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkflowExecutorTest {

    private static Path inFile;
    private static Path outFile;

    // Creating input/output file for tests if there is no such file in root directory.
    @BeforeAll
    static void init() throws IOException {
        try {
            if (!Files.exists(Path.of("./in_file.txt"))) {
                inFile = Files.createFile(Path.of("./in_file.txt"));
            }
            FileWriter fileWriter = new FileWriter("in_file.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Simple text for test Java program" + System.lineSeparator());
            bufferedWriter.write("For testing this were used Junit test" + System.lineSeparator());
            bufferedWriter.write("J stays for Java" + System.lineSeparator());
            bufferedWriter.close();
            fileWriter.close();

            if (!Files.exists(Path.of("./out_file.txt"))) {
                outFile = Files.createFile(Path.of("./out_file.txt"));
            }
        } catch (IOException e) {
            System.out.println("Can not create files in current directory fo tests");
            throw e;
        }
    }

    // Test to show common work of executor
    @Test
    void commonTest() {
        InputStream testStream = new ByteArrayInputStream((
                "desc \n" +
                        "1 = readfile in_file.txt\n" +
                        "2 = writefile out_file.txt\n" +
                        "3 = sort\n" +
                        "10 = replace Java жава\n" +
                        "csed\n" +
                        "1 -> 3 -> 10 -> 3 -> 2\n").getBytes());
        try {
            WorkflowExecutor workflow = new WorkflowExecutor(testStream);
            workflow.execute();
        } catch (WorkflowException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parsingTest1() {
        InputStream testStream = new ByteArrayInputStream((
                "desc").getBytes());
        try {
            WorkflowExecutor workflow = new WorkflowExecutor(testStream);
            workflow.execute();
        } catch (ParsingException e) {
            // okay, it's good
        } catch (WorkflowException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parsingTest2() {
        InputStream testStream = new ByteArrayInputStream((
                "desc \n" +
                        "1 = readfile in_file.txt\n" +
                        "1 = writefile out_file.txt\n" +
                        "1 = sort\n" +
                        "1 = replace Java жава\n" +
                        "csed\n" +
                        "1 -> 3 -> 10 -> 3 -> 2\n").getBytes());
        try {
            WorkflowExecutor workflow = new WorkflowExecutor(testStream);
            workflow.execute();
        } catch (ParsingException e) {
            // okay, it's good
        } catch (WorkflowException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() throws IOException {
        if (outFile != null) {
            Files.delete(outFile);
        }
        if (inFile != null) {
            Files.delete(inFile);
        }
    }
}
