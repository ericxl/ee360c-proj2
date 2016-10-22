/**
 * Created by Eric on 10/21/16.
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
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
}
