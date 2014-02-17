/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressparser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ExceptionPackage.InvalidInputException;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author Alex
 */
@RunWith(Parameterized.class)

public class ParserTest {


    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Parameters
    public static Collection<Object[]> data() {
        //Build and return Arrays as lists in format; String[] <Test Input>, String[] <Expected Output>.
        return Arrays.asList(new Object[][]{
            {new String[]{""}, new String[]{null}},
            {new String[]{"Rued Langgaards Vej"}, new String[]{"Rued Langgaards Vej",null,null,null,null,null}},
            {new String[]{"Rued Langgaards Vej, 7 5. sal. København S"}, new String[]{"Rued Langgaards Vej", "7", null, "5 sal", null, "København S"}},
            {new String[]{"Rued Langgaards Vej, 7 2300 København S"}, new String[]{"Rued Langgaards Vej", "7", null, null, "2300", "København S"}},
            {new String[]{"Rued Langgaards Vej, 7 2300 København S"}, new String[]{"Rued Langgaards Vej", "7", null, null, "2300", "København S"}},
            {new String[]{"Rued Langgaards Vej, 7a København S"}, new String[]{"Rued Langgaards Vej", "7", "a", null, null, "København S"}},
            {new String[]{"Rued Langgaards Vej i København"}, new String[]{"Rued Langgaards Vej", null, null, null, null, "København"}},
        });
    }

    private final String[] actual;
    private final String[] expected;

    public ParserTest(String[] actual, String[] expected) {
        this.actual = actual;
        this.expected = expected;
    }

    @Test
    public void testAll() {
        try {
            Parser p = Parser.INSTANCE;
            String[] testOutput = new String[5];
            for (String s : actual) {
                //This is where the parser method is invoked.
                testOutput = p.parseThis(s);
            }
            
            //Check the output with the expected output.
            assertArrayEquals(expected, testOutput);
        } catch (InvalidInputException ex) {
            Logger.getLogger(ParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
