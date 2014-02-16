/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package addressparser;

import ExceptionPackage.InvalidInputException;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private static String[] input = new String[]{"Rued Langgards Vej 7, 2300 København S", 
                             "Rued Langgaards Vej 7, 5. sal. København S",
                             "Rued Langgaards Vej 7A København S",
                             "Rued Langgaards Vej i København",
                             "2300 København S, Rued Langgaards Vej 7",
                             "København S 2300, Rued Langgaards Vej 7",
                             "10. Juli Vej, 6070 Christiansfeld",
                             "Haveforeningen af 10. maj 1918 7, 8260 Viby J",
                             "Ved Mønten 10 st 4, 2300 København S",
                             "H C Andersens Vej",
                             "Aalgade"
                             };
    
    public AddressMatcherTest() {
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
     * Test of main method, of class AddressMatcher.
     * @throws java.lang.Exception
     */
    @Test
    public void testMain() throws Exception {
        AddressMatcher am;
        Addressparser ap = new Addressparser();
        try {
            am = new AddressMatcher();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressMatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        // wait for the user to input text, handle it and wait for the next.
        for (String s : input) {
            
            System.out.print("Input address: " + s);
            System.out.println("");

            String s2 = AddressMatcher.cleanString(s);
            s = s.toLowerCase();
            try {
                String streetName = AddressMatcher.checkAddressExist(s2);
                // Remove the way the user has typed the address...
                if (streetName != null) {
                    for (int i = 0; i < streetName.length(); i++) {
                        s = s.replaceFirst(streetName.substring(i, i + 1), "");
                    }
                }
                // ... and replace it with our version + a ","
                String s3 = streetName + ", " + s;
                System.out.println(ap.parseSingleAdress(s3));
                System.out.println("");

            } catch (InvalidInputException ex) {
                // if no other street names was found it will print the cause.
                ex.printPossibleStreetName();
                System.out.println("");
            }
        }
    }
}
