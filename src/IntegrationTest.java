/**
 * Created by Eric on 10/21/16.
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class IntegrationTest {

    PrintStream old;
    @Before
    public void cacheOutput(){
        old = System.out;
    }

    @After
    public void restoreOutput(){
        System.out.flush();
        System.setOut(old);
    }

    @Test
    public void testSuiteLevel0(){

        for (int i = 0; i < 2; i ++){
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outStream));

            Program2.main(new String[] {"validation/level0/input" + i + ".txt"});

            try {
                assertEquals(new String(Files.readAllBytes(Paths.get("validation/level0/output" + i + ".txt"))), outStream.toString());
            } catch(IOException e){
                //throw new Exception("File not found");
            }
            //assertEquals(new Scanner(new File("filename")).useDelimiter("\\Z").next(), outStream.toString());
        }
    }

    @Test
    public void testSuiteLevel1(){

        for (int i = 0; i < 13; i ++){
            if(i == 6 || i == 7){
                //passed
                continue;
            }

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outStream));

            Program2.main(new String[] {"validation/level1/in" + i + ".txt"});

            try {
                assertEquals(new String(Files.readAllBytes(Paths.get("validation/level1/out" + i + ".txt"))), outStream.toString());
            } catch(IOException e){
                //throw new Exception("File not found");
            }
            //assertEquals(new Scanner(new File("filename")).useDelimiter("\\Z").next(), outStream.toString());
        }
    }

    @Test
    public void testInput0() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Program2.main(new String[] {"input0.txt"});

        assertEquals("4\n" +
                "0 1 1\n" +
                "1 2 2\n" +
                "2 4 3\n" +
                "3 4 4\n", outStream.toString());
    }

    @Test
    public void testInput1() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Program2.main(new String[] {"input1.txt"});

        assertEquals("0\n", outStream.toString());
    }

    @Test
    public void testInput2() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Program2.parseFile(new Scanner("4 6\n" +
                "0 0 0\n" +
                "0 1 0\n" +
                "0 2 0\n" +
                "1 2 0\n" +
                "3 2 0\n" +
                "0 3 0\n" +
                "0 3 0 5\n"));

        assertEquals("1\n" +
                "0 3 0\n", outStream.toString());
    }

    @Test
    public void testInput3() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Program2.parseFile(new Scanner("4 6\n" +
                "0 0 0\n" +
                "0 1 0\n" +
                "0 2 0\n" +
                "1 2 0\n" +
                "3 2 0\n" +
                "0 3 0\n" +
                "0 0 0 5\n"));

        assertEquals("0\n", outStream.toString());
    }

    @Test
    public void testInput4() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Program2.parseFile(new Scanner("5 5\n" +
                "0 1 1\n" +
                "0 3 2\n" +
                "2 4 3\n" +
                "1 2 4\n" +
                "3 4 4\n" +
                "0 3 0 4\n"));

        assertEquals("1\n" +
                "0 3 2\n", outStream.toString());
    }

    @Test
    public void testInput5() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Program2.parseFile(new Scanner("5 4\n" +
                "0 1 0\n" +
                "1 2 1\n" +
                "2 3 1\n" +
                "3 4 2\n" +
                "0 4 0 4\n"));

        assertEquals("4\n" +
                "0 1 0\n" +
                "1 2 1\n" +
                "2 3 1\n" +
                "3 4 2\n", outStream.toString());
    }

    @Test
    public void testInput6() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Program2.parseFile(new Scanner("3 2\n" +
                "0 1 2\n" +
                "1 2 1\n" +
                "0 2 0 2\n"));

        assertEquals("0\n", outStream.toString());
    }

    @Test
    public void testInput7() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Program2.parseFile(new Scanner("5 4\n" +
                "0 1 1\n" +
                "2 4 3\n" +
                "1 2 4\n" +
                "3 4 4\n" +
                "0 3 0 4\n"));

        assertEquals("0\n", outStream.toString());
    }
}
