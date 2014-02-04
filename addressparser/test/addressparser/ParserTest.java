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
    public void testDataSetC() {
        System.out.println("parseThis");
        String parseMe = "Rued Langgaards Vej 7, 5. sal. København S";
        Parser instance = new Parser();
        String[] expResult = new String[6];
        expResult[0] = "Rued Langgaards Vej";
        expResult[1] = "7";
        expResult[2] = null;
        expResult[3] = "5. sal";
        expResult[4] = null;
        expResult[5] = "København S";
        try{
        String[] result = instance.parseThis(parseMe);
        assertArrayEquals(expResult, result);
        } catch (InvalidInputException ex)  {
            
            System.out.println(ex.getCauseString());
            
        }
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
        /**
     * Test of parseThis method, of class Parser.
     */
    @Test
    public void testDataSetA() {
        System.out.println("parseThis");
        String parseMe = "Rued Langgards Vej";
        Parser instance = new Parser();
        String[] expResult = new String[6];
        expResult[0] = "Rued Langgards Vej";
        expResult[1] = null;
        expResult[2] = null;
        expResult[3] = null;
        expResult[4] = null;
        expResult[5] = null;
        try{
        String[] result = instance.parseThis(parseMe);
        assertArrayEquals(expResult, result);
        } catch (InvalidInputException ex)  {
            
            System.out.println(ex.getCauseString());
            
        }
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
        try{
        String[] result = instance.parseThis(parseMe);
        assertArrayEquals(expResult, result);
        } catch (InvalidInputException ex)  {
            
            System.out.println(ex.getCauseString());
            
        }
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
        try{
        String[] result = instance.parseThis(parseMe);
        assertArrayEquals(expResult, result);
        } catch (InvalidInputException ex)  {
            
            System.out.println(ex.getCauseString());
            
        }
    }
    
    
     /**
     * Test of parseThis method, of class Parser.
     */
    @Test
    public void testParseThis5() {
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
        try{
        String[] result = instance.parseThis(parseMe);
        assertArrayEquals(expResult, result);
        } catch (InvalidInputException ex)  {
            
            System.out.println(ex.getCauseString());
            
        }
    }
    
    
         /**
     * Test of parseThis method, of class Parser.
     */
    @Test
    public void testParseThis6() {
        System.out.println("parseThis");
        String parseMe = "Brøndby Nord Vej 2605 Brøndby";
        Parser instance = new Parser();
        String[] expResult = new String[6];
        expResult[0] = "Brøndby Nord Vej";
        expResult[1] = null;
        expResult[2] = null;
        expResult[3] = null;
        expResult[4] = "2605";
        expResult[5] = "Brøndby";
        try{
        String[] result = instance.parseThis(parseMe);
        assertArrayEquals(expResult, result);
        } catch (InvalidInputException ex)  {
            
            System.out.println(ex.getCauseString());
            
        }
    }
    
}
