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

/**
 *
 * @author Alex
 */
public class ParserTest {
    
    public ParserTest() {
    }
    
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

    /**
     * Test of parseThis method, of class Parser.
     */
    @Test
    public void testParseThis() {
        System.out.println("parseThis");
        String parseMe = "Brøndby Nord Vej 82 14. 2605 Brøndby";
        Parser instance = new Parser();
        String[] expResult = new String[6];
        expResult[0] = "Brøndby Nord Vej";
        expResult[1] = "82";
        expResult[2] = null;
        expResult[3] = "14.";
        expResult[4] = "2605";
        expResult[5] = "Brøndby";
        String[] result = instance.parseThis(parseMe);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
        /**
     * Test of parseThis method, of class Parser.
     */
    @Test
    public void testParseThis2() {
        System.out.println("parseThis");
        String parseMe = "Brøndby Nord Vej 82 i Brøndby";
        Parser instance = new Parser();
        String[] expResult = new String[6];
        expResult[0] = "Brøndby Nord Vej";
        expResult[1] = "82";
        expResult[2] = null;
        expResult[3] = null;
        expResult[4] = null;
        expResult[5] = "Brøndby";
        String[] result = instance.parseThis(parseMe);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
     /**
     * Test of parseThis method, of class Parser.
     */
    @Test
    public void testParseThis3() {
        System.out.println("parseThis");
        String parseMe = "Brøndby Nord Vej i Brøndby";
        Parser instance = new Parser();
        String[] expResult = new String[6];
        expResult[0] = "Brøndby Nord Vej";
        expResult[1] = null;
        expResult[2] = null;
        expResult[3] = null;
        expResult[4] = null;
        expResult[5] = "Brøndby";
        String[] result = instance.parseThis(parseMe);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
     /**
     * Test of parseThis method, of class Parser.
     */
    @Test
    public void testParseThis4() {
        System.out.println("parseThis");
        String parseMe = "Brøndby Nord Vej";
        Parser instance = new Parser();
        String[] expResult = new String[6];
        expResult[0] = "Brøndby Nord Vej";
        expResult[1] = null;
        expResult[2] = null;
        expResult[3] = null;
        expResult[4] = null;
        expResult[5] = null;
        String[] result = instance.parseThis(parseMe);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
