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
 * @author Christian
 */
public class AddressMatcherTest {
    
    private static String[] input;
    
    public AddressMatcherTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        input = new String[]{"Rued  Langgards Vej 7 2300 København S", 
                             "Rued Langgaards Vej 7, 5. sal. København S",
                             "Rued Langgaards Vej 7A København S",
                             "Rued Langgaards Vej i København",
                             "2300 København S, Rued Langgaards Vej 7",
                             "10. Juli Vej",
                             "Haveforningen af 10. maj 1918",
                             };
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
     * Test of matchStreet method, of class AddressMatcher.
     */
    @Test
    public void testMatchStreet() {
        System.out.println("matchStreet");
        String[] address = null;
        AddressMatcher.matchStreet(address);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class AddressMatcher.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        AddressMatcher.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
